package id.co.booksapp.repositories.remote

import android.util.Log
import id.co.booksapp.datastore.UserDataStore
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.request.ApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiRequest,
    private val userDataStore: UserDataStore
) {

    fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.loginUser(email, password)
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "loginUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "loginUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun registerUser(name: String, email: String, telp: String,  password: String): Flow<ResponseState<Users>> {
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.registerUser(name, email, telp,password)
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "registerUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "registerUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getBookPopular(): Flow<ResponseState<List<Book>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getPopularBook(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getBookByCategory(id: String): Flow<ResponseState<List<Book>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getBookByCategory(token = "Bearer $it", id)
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getAllBook(): Flow<ResponseState<List<Book>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getAllBook(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getCategory(): Flow<ResponseState<List<Category>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getAllCategory(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getAddTransaction(bookId: String, dateReturn: String, days: String, total: String): Flow<ResponseState<Transaction>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {token->
                    userDataStore.getIdUserFlow.collect { userId->
                        val response = apiService.addTransaction(token = "Bearer $token", userId, bookId,dateReturn, days, total)
                        val data = response.data
                        if(response.meta!!.code == 200){
                            if(data != null){
                                emit(ResponseState.Success(data))
                            }else{
                                emit(ResponseState.Empty)
                            }
                        }else{
                            emit(ResponseState.Error(response.meta!!.message))
                        }
                    }

                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getContact(): Flow<ResponseState<Contact>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getContact(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getSearch(search: String): Flow<ResponseState<List<Book>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getBookSearch(token = "Bearer $it", search)
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getTransactionByUser(): Flow<ResponseState<List<Transaction>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {token->
                    userDataStore.getIdUserFlow.collect { userId->
                        val response = apiService.getTransactionByUser(token = "Bearer $token", userId)
                        val data = response.data
                        if(response.meta!!.code == 200){
                            if(data != null){
                                emit(ResponseState.Success(data))
                            }else{
                                emit(ResponseState.Empty)
                            }
                        }else{
                            emit(ResponseState.Error(response.meta!!.message))
                        }
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getUsersById(): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getUsersById(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}