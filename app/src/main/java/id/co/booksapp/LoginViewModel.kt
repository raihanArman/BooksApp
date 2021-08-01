package id.co.booksapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val movieRepository: Repository
): ViewModel() {

    fun getLogin(email: String, password: String){
        return
    }

}
