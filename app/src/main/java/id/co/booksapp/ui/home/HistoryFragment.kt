package id.co.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.adapter.HistoryAdapter
import id.co.booksapp.databinding.FragmentHistoryBinding
import id.co.booksapp.model.Transaction
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.viewmodel.HomeViewModel

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            rvHistory.layoutManager = LinearLayoutManager(requireContext())
            rvHistory.adapter = historyAdapter
        }

        setupObserve()

    }

    private fun setupObserve() {
        viewModel.getTransactionByUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setupData(response.data)
                    progressInvisble()
                }
                is ResponseState.Error ->{
                    progressInvisble()
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{
                    progressVisible()
                }
            }
        }
    }

    private fun setupData(data: List<Transaction>) {
        historyAdapter.setHistory(data)
    }

    private fun progressVisible(){
        binding.progressHistory.visibility = View.VISIBLE
        binding.rvHistory.visibility = View.INVISIBLE
    }

    private fun progressInvisble(){
        binding.progressHistory.visibility = View.INVISIBLE
        binding.rvHistory.visibility = View.VISIBLE
    }
}