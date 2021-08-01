package id.co.booksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.booksapp.R
import id.co.booksapp.adapter.CategoryAdapter
import id.co.booksapp.adapter.MoreBookAdapter
import id.co.booksapp.databinding.FragmentMoreBookBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.Category


class MoreBookFragment : Fragment() {

    private lateinit var binding: FragmentMoreBookBinding
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    private val moreBookAdapter: MoreBookAdapter by lazy {
        MoreBookAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more_book, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            rvCategory.layoutManager = horiz
            rvCategory.adapter = categoryAdapter

            rvBook.layoutManager = GridLayoutManager(requireContext(), 2)
            rvBook.adapter = moreBookAdapter
        }

        setupCategory()
        setBook()

    }

    private fun setupCategory() {
        val listCategory = mutableListOf<Category>()
        listCategory.add(
            Category(
                "1",
                "Novel"
            )
        )
        listCategory.add(
            Category(
                "1",
                "Fiksi"
            )
        )
        listCategory.add(
            Category(
                "1",
                "Non fiksi"
            )
        )
        categoryAdapter.setListCategory(listCategory)
    }

    private fun setBook(){
        val listBook = mutableListOf<Book>()
        listBook.add(
            Book(
            "1",
            "Generasi kembali ke aka",
            "by Dr. Muhammad faisal",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1579332582l/50049513._SX318_.jpg"
        )
        )
        listBook.add(
            Book(
            "1",
            "Sebuah seni untuk bersikap bodo amat",
            "by Mark Manson",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://lh3.googleusercontent.com/proxy/ulvqwBuFWOk9IupP9YpKw2lIgA5IOtD__po3i5sGqGTEWH13Y6e60sK1-rZM--sxY7qoEueJ3Tcf_TQhe7Mhs11avTSwMq6gg2-zfIFI7PPPlRkhhrZ8UmZZn-7InD6XEnu14yzJDTdeaXPSsFNQzKWbBM7rXCViELqOuNs_hj86bpFfwhDJzBfcQg"
        )
        )
        listBook.add(
            Book(
            "1",
            "Pulang",
            "by Tere Lye",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1441194791l/26211806._SY475_.jpg"
        )
        )
        moreBookAdapter.setListBook(listBook)
    }

}