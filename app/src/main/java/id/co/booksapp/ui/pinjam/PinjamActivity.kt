package id.co.booksapp.ui.pinjam

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.util.Constant
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityPinjamBinding
import id.co.booksapp.model.Book
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.ui.base.BaseActivity
import id.co.booksapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_pinjam.*
import java.util.*


@AndroidEntryPoint
class PinjamActivity : BaseActivity() {

    private lateinit var binding: ActivityPinjamBinding
    private val viewModel: HomeViewModel by viewModels()
    lateinit var book: Book
    var total: String ?= ""
    var dateAdd: Int ?= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pinjam)

        book = intent.getParcelableExtra<Book>("book")!!
        setData(book)

        binding.btnPickUp.setOnClickListener {
            transactionProses()
        }

    }

    private fun transactionProses() {
        if(binding.rb3Days.isChecked){
            total = book.price3Days
            dateAdd = 3
        }else if(binding.rb5Days.isChecked){
            dateAdd = 5
            total = book.price5Days
        }else if(binding.rb7Days.isChecked){
            dateAdd = 7
            total = book.price7Days
        }else{
            dateAdd = 0
            total = "0"
        }
        if(dateAdd != 0){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, dateAdd!!)

            val dateReturn = DateFormat.format("yyyy-MM-dd HH:mm:ss", calendar.time).toString()

            viewModel.addTransaction(book.id!!, dateReturn, dateAdd.toString(), total!!).observe(this){response ->
                when(response){
                    is ResponseState.Success ->{
                        progressDialog!!.dismiss()
                        val intent = Intent(this, KonfirmasiActivity::class.java)
                        intent.putExtra("history", response.data)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    is ResponseState.Error ->{
                        progressDialog!!.dismiss()
                        Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    is ResponseState.Loading ->{
                        progressDialog!!.show()
                    }
                }
            }
        }else{
            Toast.makeText(this, "Pilih hari terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }


    private fun setData(book: Book) {
        with(binding){
            tvTitle.text = book.title
            tvAuthor.text = book.author
            rb3Days.text = Constant.rupiahFormat(book.price3Days!!.toInt())+"/3 hari"
            rb5Days.text = Constant.rupiahFormat(book.price5Days!!.toInt())+"/5 hari"
            rb7Days.text = Constant.rupiahFormat(book.price7Days!!.toInt())+"/7 hari"

            Glide.with(this@PinjamActivity)
                .load(Constant.BASE_URL_IMAGE+book.imageUrl)
                .into(ivBook)

        }
    }
}