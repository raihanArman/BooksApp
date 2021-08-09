package id.co.booksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.booksapp.model.Users
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.repositories.Repository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    fun getLogin(email: String, password: String): LiveData<ResponseState<Users>> {
        return repository.loginUser(email, password).asLiveData()
    }

    fun getRegister(name: String, email: String, telp: String, password: String): LiveData<ResponseState<Users>> {
        return repository.registerUser(name, email, telp, password).asLiveData()
    }

}
