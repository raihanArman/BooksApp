package id.co.booksapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.booksapp.R
import id.co.booksapp.databinding.ItemExploreBinding
import id.co.booksapp.databinding.ItemTrandingBinding
import id.co.booksapp.model.Book
import id.co.booksapp.util.Constant

class ExploreAdapter(val showDetail: (Book) -> Unit): RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    val listBook = ArrayList<Book>()

    fun setListBook(listBook: List<Book>){
        this.listBook.clear()
        this.listBook.addAll(listBook)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemExploreBinding = DataBindingUtil.inflate(inflater, R.layout.item_explore, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = listBook[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = listBook.size

    inner class ViewHolder(val binding: ItemExploreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            with(binding){
                Glide.with(itemView.context)
                    .load(Constant.BASE_URL_IMAGE+book.imageUrl)
                    .into(ivBook)
                tvTitle.text = book.title
                tvAuthor.text = book.author
                tvDesc.text = book.desc
                tvRate.text = book.rate.toString()
                ratingBar.rating = book.rate!!
            }

            itemView.setOnClickListener {
                showDetail(book)
            }
        }
    }


}