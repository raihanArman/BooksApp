package id.co.booksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.booksapp.R
import id.co.booksapp.adapter.HistoryAdapter
import id.co.booksapp.databinding.FragmentHistoryBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.History
import java.util.*

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

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

        setupData()

    }

    private fun setupData() {
        val listHistory = mutableListOf<History>()
        listHistory.add(
            History(
                "1",
                Date(),
                Book(
                    "1",
                    "Generasi kembali ke aka",
                    "by Dr. Muhammad faisal",
                    "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
                    3.0f,
                    "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1579332582l/50049513._SX318_.jpg"
                )
            )
        )

        historyAdapter.setHistory(listHistory)
    }
}