package id.co.booksapp.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import id.co.booksapp.util.Constant
import id.co.booksapp.R
import id.co.booksapp.databinding.ActivityDetailBinding
import id.co.booksapp.model.Book
import id.co.booksapp.ui.base.BaseActivity
import id.co.booksapp.util.sheetBehavior
import id.co.booksapp.ui.pinjam.PinjamActivity
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private lateinit var behavior: BottomSheetBehavior<*>
    private lateinit var binding: ActivityDetailBinding

    lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        book = intent.getParcelableExtra<Book>("book")!!

        setupBottomsheet()
        setData()

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnPinjam.setOnClickListener {
            checkStock(book!!)
//            prosesTransasction()
        }

    }

    private fun checkStock(book: Book) {
        if(book.stock!!.toInt() != 0){
            val intent = Intent(this, PinjamActivity::class.java)
            intent.putExtra("book", book)
            startActivity(intent)
        }else{
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("Stok kosong")
            alertDialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }

            })
            alertDialog.show()
        }
    }


    private fun setData() {
        with(binding){
            tvTitle.text = book.title
            tvAuthor.text = book.author
            tvDesc.text = book.desc
            tv3Days.text = Constant.rupiahFormat(book.price3Days!!.toInt())
            tv5Days.text = Constant.rupiahFormat(book.price5Days!!.toInt())
            tv7Days.text = Constant.rupiahFormat(book.price7Days!!.toInt())
            tvStock.text = book.stock


            Glide.with(this@DetailActivity)
                .load(Constant.BASE_URL_IMAGE+book.imageUrl)
                .into(ivBook)
        }
    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}