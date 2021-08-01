package id.co.booksapp.repositories

import id.co.booksapp.model.Users
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.repositories.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remote: RemoteDataSource
): IRepository {
    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return remote.loginUser(email, password)
    }
}