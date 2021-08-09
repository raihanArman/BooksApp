package id.co.booksapp.repositories

import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
    fun registerUser(name: String, email: String, telp: String, password: String): Flow<ResponseState<Users>>
    fun getBookCategory(id:String): Flow<ResponseState<List<Book>>>
    fun getAllBook(): Flow<ResponseState<List<Book>>>
    fun getPopularBook(): Flow<ResponseState<List<Book>>>
    fun getCategory(): Flow<ResponseState<List<Category>>>
    fun addTransaction(bookId: String, dateReturn: String, days: String, total: String): Flow<ResponseState<Transaction>>
    fun getContact(): Flow<ResponseState<Contact>>
    fun getTransactionByUser(): Flow<ResponseState<List<Transaction>>>
    fun getSearch(search: String): Flow<ResponseState<List<Book>>>
    fun getUser(): Flow<ResponseState<Users>>
}