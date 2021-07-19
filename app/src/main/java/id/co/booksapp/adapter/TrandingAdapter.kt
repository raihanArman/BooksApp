package id.co.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.booksapp.R
import id.co.booksapp.databinding.ItemTrandingBinding
import id.co.booksapp.model.Book

class TrandingAdapter: RecyclerView.Adapter<TrandingAdapter.ViewHolder>() {

    val listBook = ArrayList<Book>()

    fun setListBook(listBook: List<Book>){
        this.listBook.clear()
        this.listBook.addAll(listBook)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrandingAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTrandingBinding = DataBindingUtil.inflate(inflater, R.layout.item_tranding, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrandingAdapter.ViewHolder, position: Int) {
        val book = listBook[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = listBook.size

    inner class ViewHolder(val binding: ItemTrandingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            with(binding){
                tvAuthor.text = book.author
                tvTitle.text = book.title
                Glide.with(itemView.context)
                    .load(book.imageUrl)
                    .into(ivBook)
            }
        }
    }

}