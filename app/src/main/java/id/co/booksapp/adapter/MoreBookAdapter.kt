package id.co.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.booksapp.R
import id.co.booksapp.databinding.ItemMoreBookBinding
import id.co.booksapp.databinding.ItemTrandingBinding
import id.co.booksapp.model.Book
import id.co.booksapp.util.Constant

class MoreBookAdapter: RecyclerView.Adapter<MoreBookAdapter.ViewHolder>() {

    val listBook = ArrayList<Book>()

    fun setListBook(listBook: List<Book>){
        this.listBook.clear()
        this.listBook.addAll(listBook)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreBookAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMoreBookBinding = DataBindingUtil.inflate(inflater, R.layout.item_more_book, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoreBookAdapter.ViewHolder, position: Int) {
        val book = listBook[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = listBook.size

    inner class ViewHolder(val binding : ItemMoreBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            with(binding) {
                tvAuthor.text = book.author
                tvTitle.text = book.title
                Glide.with(itemView.context)
                    .load(Constant.BASE_URL_IMAGE+book.imageUrl)
                    .into(ivBook)
            }
        }
    }

}