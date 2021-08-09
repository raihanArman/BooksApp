package id.co.booksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.repositories.Repository
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getCategory(): LiveData<ResponseState<List<Category>>> {
        return repository.getCategory().asLiveData()
    }
    fun getBookByCategory(id: String): LiveData<ResponseState<List<Book>>> {
        return repository.getBookCategory(id).asLiveData()
    }
    fun getPopularBook(): LiveData<ResponseState<List<Book>>> {
        return repository.getPopularBook().asLiveData()
    }
    fun getAllBook(): LiveData<ResponseState<List<Book>>> {
        return repository.getAllBook().asLiveData()
    }

    fun addTransaction(bookId: String, dateReturn: String, days: String, total: String): LiveData<ResponseState<Transaction>> {
        return repository.addTransaction(bookId, dateReturn, days, total).asLiveData()
    }

    fun getContact(): LiveData<ResponseState<Contact>> {
        return repository.getContact().asLiveData()
    }

    fun getTransactionByUser(): LiveData<ResponseState<List<Transaction>>> {
        return repository.getTransactionByUser().asLiveData()
    }

    fun getSearch(search: String): LiveData<ResponseState<List<Book>>> {
        return repository.getSearch(search).asLiveData()
    }

    fun getUser(): LiveData<ResponseState<Users>> = repository.getUser().asLiveData()

}