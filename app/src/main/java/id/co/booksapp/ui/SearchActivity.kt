package id.co.booksapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.adapter.ExploreAdapter
import id.co.booksapp.databinding.ActivitySearchBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.base.BaseActivity
import id.co.booksapp.viewmodel.HomeViewModel

@AndroidEntryPoint
class SearchActivity : BaseActivity() {
    lateinit var binding: ActivitySearchBinding
    private val viewModel: HomeViewModel by viewModels()
    private val exploreAdapter: ExploreAdapter by lazy {
        ExploreAdapter{
            showDetail(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        setupAdapter()
        setupObserve("")

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                setupObserve(s.toString())
            }

        })


    }

    private fun setupObserve(search: String) {
        viewModel.getSearch(search).observe(this){response ->
            when(response){
                is ResponseState.Success ->{
                    setupData(response.data)
                    progressInvisble()
                }
                is ResponseState.Error ->{
                    progressInvisble()
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{
                    progressVisible()
                }
            }
        }
    }

    private fun progressVisible(){
        binding.progressSearch.visibility = View.VISIBLE
        binding.rvSearch.visibility = View.INVISIBLE
    }

    private fun progressInvisble(){
        binding.progressSearch.visibility = View.INVISIBLE
        binding.rvSearch.visibility = View.VISIBLE
    }

    private fun setupData(data: List<Book>) {
        exploreAdapter.setListBook(data)
    }

    private fun setupAdapter() {
        with(binding){
            rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity)
            rvSearch.adapter = exploreAdapter
        }
    }


    fun showDetail(book: Book){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("book", book)
        startActivity(intent)
    }

}