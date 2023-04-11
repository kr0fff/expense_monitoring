package com.example.expensemonitoring

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding
import java.time.Month

class CategoryAdapter(
    private val resources: Resources,
    private val activityContext: Context?,
    private val onDeleteCategoryListener: OnClickDeleteCategory
) : ListAdapter<CategoriesTuple, CategoryAdapter.CategoryViewHolder>(ItemCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpenseCategoryItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        Log.d("CATEGORY_ITEM" , "$category")
        holder.itemView.tag = category
        holder.deleteButton.tag = category
        with(holder.binding) {
            if (category != null) {
                val month = Month.of(category.categoryMonth.toInt())
                val totalSpent = kotlin.math.round(category.categorySum * 100) / 100
                categoryLabel.text =
                    resources.getString(R.string.category_description, month, category.categoryYear)
                categoryDescriptionTotal.text =
                    resources.getString(R.string.category_total, totalSpent.toString())
            }
        }

    }

    override fun onClick(view: View) {
        val context = view.context ?: activityContext
        val itemContent = view.tag as? CategoriesTuple
        Log.d("ITEM_CONTENT", "$itemContent")

        when (view.id) {
            R.id.delete_button -> {
                if (itemContent != null){
                    onDeleteCategoryListener.onDeleteCategory(
                        itemContent.categoryMonth,
                        itemContent.categoryYear
                    )
                }
                Log.d("DELETE_CLICK", "${itemContent?.categoryMonth} - ${itemContent?.categoryYear}")
            }

            else -> {
                Log.d("CONTEXT_CLICKER", "$activityContext --")
                if (activityContext != null) {

                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("CONTEXT_CLICKER", "activityContext is null")
                }
            }
        }
    }


    object ItemCallback : DiffUtil.ItemCallback<CategoriesTuple>() {
        override fun areItemsTheSame(oldItem: CategoriesTuple, newItem: CategoriesTuple): Boolean {
            return oldItem.categoryMonth == newItem.categoryMonth
                    && oldItem.categoryYear == newItem.categoryYear
                    && oldItem.categorySum == newItem.categorySum
        }

        override fun areContentsTheSame(oldItem: CategoriesTuple, newItem: CategoriesTuple): Boolean {
            return oldItem == newItem
        }
    }
    class CategoryViewHolder(
        val binding: ExpenseCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val deleteButton = binding.deleteButton
    }


}