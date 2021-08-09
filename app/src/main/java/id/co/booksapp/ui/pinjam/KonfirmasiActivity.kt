package id.co.booksapp.ui.pinjam

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.util.Constant
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityKonfirmasiBinding
import id.co.booksapp.model.Transaction
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.MainActivity
import id.co.booksapp.ui.base.BaseActivity
import id.co.booksapp.viewmodel.HomeViewModel
import java.lang.StringBuilder


@AndroidEntryPoint
class KonfirmasiActivity : BaseActivity() {
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: ActivityKonfirmasiBinding
    var number: String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_konfirmasi)

        val history: Transaction = intent.getParcelableExtra("history")!!
        setData(history)

        binding.ivBack.setOnClickListener {
            gotoMainActivity()
        }

        binding.btnKonfirmasi.setOnClickListener {
            openWhatsApp(number!!)
        }

        getNumberContact()

    }

    private fun getNumberContact() {
        viewModel.getContact().observe(this){response ->
            when(response){
                is ResponseState.Loading ->{
                    progressDialog!!.show()
                }
                is ResponseState.Success ->{
                    progressDialog!!.dismiss()
                    number = response.data.number
                }
                is ResponseState.Error ->{
                    progressDialog!!.dismiss()
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun openWhatsApp(contact: String){
        try{
            val sb = StringBuilder(contact)
            sb.deleteCharAt(0)

            val url = "https://api.whatsapp.com/send?phone=+62 $sb"
            val pm: PackageManager = packageManager
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }catch (e: PackageManager.NameNotFoundException){
            Toast.makeText(this, "Aplikasi belum terinstall", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun gotoMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setData(history: Transaction) {
        with(binding){
            tvBook.text = history.book.title
            tvAuthor.text = history.book.author
            tvDays.text = history.days
            tvTotal.text = Constant.rupiahFormat(history.total!!.toInt())
            tvDateReturn.text = DateFormat.format("EEEE, dd MMM yyyy", history.dateReturn).toString()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}