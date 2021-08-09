package id.co.cobadrawer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.adapter.ExploreAdapter
import id.co.booksapp.adapter.TrandingAdapter
import id.co.booksapp.databinding.FragmentHomeBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.DetailActivity
import id.co.booksapp.ui.MoreBookActivity
import id.co.booksapp.ui.SearchActivity
import id.co.booksapp.ui.base.BaseFragment
import id.co.booksapp.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var dataBinding : FragmentHomeBinding
    private val trandingAdapter: TrandingAdapter by lazy {
        TrandingAdapter{
            showDetail(it)
        }
    }

    private val exploreAdapter: ExploreAdapter by lazy {
        ExploreAdapter{
          showDetail(it)
        }
    }

    private val viewModel: HomeViewModel by viewModels()

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
        setupObserve()

        dataBinding.ivMoreExplore.setOnClickListener {
            val intent = Intent(requireContext(), MoreBookActivity::class.java)
            startActivity(intent)
        }

        dataBinding.ivMoreTranding.setOnClickListener {
            val intent = Intent(requireContext(), MoreBookActivity::class.java)
            startActivity(intent)
        }

        dataBinding.etSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupObserve() {
        viewModel.getPopularBook().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setupDataPopular(response.data)
                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{
                    progressVisible()
                }
            }
        }
        viewModel.getAllBook().observe(viewLifecycleOwner){response ->
            when(response){
                is ResponseState.Success ->{
                    setupDataAll(response.data)
                    progressInvisble()
                }
                is ResponseState.Error ->{
                    Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{
                    progressVisible()
                }
            }
        }
    }

    private fun progressVisible(){
        dataBinding.progressHome.visibility = View.VISIBLE
        dataBinding.layoutHomeContent.visibility = View.INVISIBLE
    }

    private fun progressInvisble(){
        dataBinding.progressHome.visibility = View.INVISIBLE
        dataBinding.layoutHomeContent.visibility = View.VISIBLE
    }

    private fun setupDataAll(data: List<Book>) {
        exploreAdapter.setListBook(data)
    }

    private fun setupDataPopular(data: List<Book>) {
        trandingAdapter.setListBook(data)
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
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("book", book)
        startActivity(intent)
    }

}