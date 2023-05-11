package com.example.expensemonitoring.RecyclerViewAdapters

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.expensemonitoring.CategoryOnClickActions
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding
import java.time.Month

class CategoryAdapter(
    private val resources: Resources,
    private val fragmentListener: CategoryOnClickActions
) : ListAdapter<CategoriesTuple, CategoryAdapter.CategoryViewHolder>(ItemCallback),
    View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpenseCategoryItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        Log.d("CATEGORY_ITEM", "$category")
        holder.itemView.tag = Pair<CategoriesTuple, Int>(category, position)
        holder.deleteButton.tag = Pair<CategoriesTuple, Int>(category, position)
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
        val itemContent = view.tag as? Pair<*, *>
        var tuple = itemContent?.first
        val position = itemContent?.second
        Log.d("ITEM_CONTENT", "$itemContent")
        when (view.id) {
            R.id.delete_button -> {
                if (tuple != null && position != null) {
                    tuple = tuple as CategoriesTuple
                    fragmentListener.onDeleteCategory(
                        tuple.categoryMonth,
                        tuple.categoryYear
                    )
                    notifyItemRemoved(position as Int)
                }
            }

            else -> {
               /* if (itemContent != null) {

                    FragmentCategoryExpensesList.newInstance(
                        itemContent.categoryMonth,
                        itemContent.categoryYear
                    )
                }*/
            }
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<CategoriesTuple>() {
        override fun areItemsTheSame(oldItem: CategoriesTuple, newItem: CategoriesTuple): Boolean {
            return oldItem.categoryMonth == newItem.categoryMonth
                    && oldItem.categoryYear == newItem.categoryYear
                    && oldItem.categorySum == newItem.categorySum
        }

        override fun areContentsTheSame(
            oldItem: CategoriesTuple,
            newItem: CategoriesTuple
        ): Boolean {
            return oldItem == newItem
        }
    }

    class CategoryViewHolder(
        val binding: ExpenseCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val deleteButton = binding.deleteButton
    }


}