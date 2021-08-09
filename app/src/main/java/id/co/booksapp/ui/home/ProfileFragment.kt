package id.co.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.databinding.FragmentProfileBinding
import id.co.booksapp.model.Users
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.base.BaseFragment
import id.co.booksapp.viewmodel.HomeViewModel

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserve()

    }


    private fun setupObserve() {
        viewModel.getUser().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setupData(response.data)
//                    progressInvisble()
                }
                is ResponseState.Error ->{
//                    progressInvisble()
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{
//                    progressVisible()
                }
            }
        }
    }

    private fun setupData(data: Users) {
        with(binding){
            tvEmail.text = data.email
            tvTelp.text = data.telp
            tvUser.text = data.name
        }
    }
}