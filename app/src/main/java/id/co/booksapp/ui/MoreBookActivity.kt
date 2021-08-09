package id.co.booksapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.R
import id.co.booksapp.adapter.CategoryAdapter
import id.co.booksapp.adapter.MoreBookAdapter
import id.co.booksapp.databinding.ActivityMoreBookBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.Category
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.base.BaseActivity
import id.co.booksapp.viewmodel.HomeViewModel


@AndroidEntryPoint
class MoreBookActivity : BaseActivity() {

    private lateinit var binding: ActivityMoreBookBinding
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter{
            showBookByCategory(it)
        }
    }

    private val moreBookAdapter: MoreBookAdapter by lazy {
        MoreBookAdapter()
    }


    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more_book)

        setupAdapter()
        setupObserveCategory()
        setupObserveAllBook()

    }

    private fun setupObserveCategory() {
        viewModel.getCategory().observe(this){response ->
            when(response){
                is ResponseState.Success ->{
                    setupDataCategory(response.data)
                }
                is ResponseState.Error ->{
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{

                }
            }
        }
    }

    private fun setupObserveAllBook(){
        viewModel.getAllBook().observe(this){response ->
            when(response){
                is ResponseState.Success ->{
                    setupDataBook(response.data)
                }
                is ResponseState.Error ->{
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{

                }
            }
        }
    }

    private fun setupObserveBookByCategory(id: String){
        viewModel.getBookByCategory(id).observe(this){response ->
            when(response){
                is ResponseState.Success ->{
                    setupDataBook(response.data)
                }
                is ResponseState.Error ->{
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResponseState.Loading ->{

                }
            }
        }
    }

    private fun setupDataBook(data: List<Book>) {
        moreBookAdapter.setListBook(data)
    }

    private fun setupDataCategory(data: List<Category>) {
        val dataCategory = mutableListOf<Category>()
        dataCategory.add(
            Category(
            "0",
            "Semua",
        )
        )
        dataCategory.addAll(data)
        categoryAdapter.setListCategory(dataCategory)
    }

    private fun setupAdapter() {
        with(binding){
            val horiz = LinearLayoutManager(this@MoreBookActivity)
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            rvCategory.layoutManager = horiz
            rvCategory.adapter = categoryAdapter

            rvBook.layoutManager = GridLayoutManager(this@MoreBookActivity, 2)
            rvBook.adapter = moreBookAdapter
        }
    }

    private fun showBookByCategory(category: Category){
        if(category.id == "0"){
            setupObserveAllBook()
        }else{
            setupObserveBookByCategory(category.id!!)
        }
    }

}