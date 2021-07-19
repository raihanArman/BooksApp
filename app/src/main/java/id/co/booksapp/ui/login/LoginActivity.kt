package id.co.booksapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        setFragment(SignUpFragment())
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(binding.frameLogin.id, fragment).commit()
    }

}