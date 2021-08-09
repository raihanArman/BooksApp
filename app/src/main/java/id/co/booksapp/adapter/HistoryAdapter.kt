package id.co.booksapp.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.booksapp.util.Constant
import id.co.booksapp.R
import id.co.booksapp.databinding.ItemHistoryBinding
import id.co.booksapp.model.Transaction

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val listHistory = ArrayList<Transaction>()

    fun setHistory(listHistory: List<Transaction>){
        this.listHistory.clear()
        this.listHistory.addAll(listHistory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_history, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        val history = listHistory[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int = listHistory.size

    inner class ViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(history: Transaction){
            with(binding){
                val date = DateFormat.format("dd-MM-yyyy", history.date).toString()
                val dateReturn = DateFormat.format("dd-MM-yyyy", history.dateReturn).toString()
                tvDate.text = date
                tvTitle.text = history.book.title
                tvAuthor.text = history.book.author
                tvHarga.text = Constant.rupiahFormat(history.total!!.toInt())
                tvDateReturn.text = dateReturn
            }
        }
    }

}