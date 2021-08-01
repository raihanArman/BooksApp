package id.co.booksapp.repositories

import id.co.booksapp.model.Users
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
}