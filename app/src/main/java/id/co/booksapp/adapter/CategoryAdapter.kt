package id.co.booksapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.booksapp.R
import id.co.booksapp.databinding.ItemCategoryBinding
import id.co.booksapp.model.Category

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val listCategory = ArrayList<Category>()

    fun setListCategory(listCategory: List<Category>){
        this.listCategory.clear()
        this.listCategory.addAll(listCategory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = listCategory[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = listCategory.size

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(category: Category){
            with(binding){
                tvCategory.text = category.categoryName

                if(adapterPosition == 0){
                    tvCategory.setBackgroundResource(R.drawable.bg_select)
                    tvCategory.setTextColor(R.color.white)
                }
            }
        }
    }

}