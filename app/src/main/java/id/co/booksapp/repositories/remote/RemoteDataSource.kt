package id.co.booksapp.repositories.remote

import android.util.Log
import id.co.booksapp.datastore.UserDataStore
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.request.ApiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

}