package id.co.cobadrawer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.co.booksapp.R
import id.co.booksapp.adapter.ExploreAdapter
import id.co.booksapp.adapter.TrandingAdapter
import id.co.booksapp.databinding.FragmentHomeBinding
import id.co.booksapp.model.Book

class HomeFragment : Fragment() {

    private lateinit var dataBinding : FragmentHomeBinding
    private val trandingAdapter: TrandingAdapter by lazy {
        TrandingAdapter()
    }

    private val exploreAdapter: ExploreAdapter by lazy {
        ExploreAdapter{
          showDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupData()

    }

    private fun setupData() {
        val listBook = mutableListOf<Book>()
        listBook.add(Book(
            "1",
            "Generasi kembali ke aka",
            "by Dr. Muhammad faisal",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1579332582l/50049513._SX318_.jpg"
        ))
        listBook.add(Book(
            "1",
            "Sebuah seni untuk bersikap bodo amat",
            "by Mark Manson",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://lh3.googleusercontent.com/proxy/ulvqwBuFWOk9IupP9YpKw2lIgA5IOtD__po3i5sGqGTEWH13Y6e60sK1-rZM--sxY7qoEueJ3Tcf_TQhe7Mhs11avTSwMq6gg2-zfIFI7PPPlRkhhrZ8UmZZn-7InD6XEnu14yzJDTdeaXPSsFNQzKWbBM7rXCViELqOuNs_hj86bpFfwhDJzBfcQg"
        ))
        listBook.add(Book(
            "1",
            "Pulang",
            "by Tere Lye",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1441194791l/26211806._SY475_.jpg"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))
        listBook.add(Book(
            "1",
            "Mantap Djiwa",
            "author",
            "Diskusi mengenai teori generasi tengah berkembang menjadi “bola salju” yang bergulir liar. Salah satu narasi yang berkembang di berbagai media adalah penafsiran tentang generasi yang bak membaca ramalan horoskop. Sebagai contoh, seseorang yang lahir pada 1981 hingga awal 2000 dianggap sebagai Generasi Milenial—yang memiliki . . .",
            3.0f,
            "https://akcdn.detik.net.id/community/media/visual/2021/03/23/jesse-lingard.jpeg?w=700&q=90"
        ))

        exploreAdapter.setListBook(listBook)
        trandingAdapter.setListBook(listBook)

    }

    private fun setupAdapter() {
        with(dataBinding){
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            rvTranding.layoutManager = horiz
            rvTranding.adapter = trandingAdapter

            rvExplore.layoutManager = LinearLayoutManager(requireContext())
            rvExplore.adapter = exploreAdapter
        }
    }

    fun showDetail(book: Book){
        findNavController().navigate(R.id.action_nav_home_to_detailFragment)
    }

}