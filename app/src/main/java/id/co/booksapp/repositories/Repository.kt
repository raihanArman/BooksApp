package id.co.booksapp.repositories

import id.co.booksapp.model.*
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

    override fun registerUser(
        name: String,
        email: String,
        telp: String,
        password: String
    ): Flow<ResponseState<Users>> {
        return remote.registerUser(name, email, telp,password)
    }

    override fun getBookCategory(id: String): Flow<ResponseState<List<Book>>> {
        return remote.getBookByCategory(id)
    }

    override fun getAllBook(): Flow<ResponseState<List<Book>>> {
        return remote.getAllBook()
    }

    override fun getPopularBook(): Flow<ResponseState<List<Book>>> {
        return remote.getBookPopular()
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return remote.getCategory()
    }

    override fun addTransaction(bookId: String, dateReturn: String, days: String, total: String): Flow<ResponseState<Transaction>> {
        return remote.getAddTransaction(bookId, dateReturn, days, total)
    }

    override fun getContact(): Flow<ResponseState<Contact>> {
        return remote.getContact()
    }

    override fun getTransactionByUser(): Flow<ResponseState<List<Transaction>>> {
        return remote.getTransactionByUser()
    }

    override fun getSearch(search: String): Flow<ResponseState<List<Book>>> {
        return remote.getSearch(search)
    }

    override fun getUser(): Flow<ResponseState<Users>> {
        return remote.getUsersById()
    }
}