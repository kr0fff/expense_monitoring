package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemonitoring.CategoryAdapter
import com.example.expensemonitoring.CategoryOnClickActions
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.FragmentCategoriesListBinding
import com.example.expensemonitoring.fragments.viewModels.CategoriesViewModel
import com.example.expensemonitoring.fragments.viewModels.ViewModelFactory


class FragmentCategoriesList : Fragment(), CategoryOnClickActions {
    private val vm: CategoriesViewModel by viewModels { ViewModelFactory(Repositories) }
    private lateinit var binding: FragmentCategoriesListBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoriesListBinding.inflate(inflater)
        adapter = CategoryAdapter(resources, requireContext(), this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.categories.observe(viewLifecycleOwner, Observer {
            vm.refreshCategories()
            adapter.submitList(it)
            Log.d("LIST_CATEGORIES_LIVEDATA", it.toString())
        })


        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && binding.addButton.visibility == View.VISIBLE) {
                binding.addButton.hide()
            } else if (scrollY < oldScrollY && binding.addButton.visibility != View.VISIBLE) {
                binding.addButton.show()
            }
        }

        binding.addButton.setOnClickListener {

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FragmentNewExpenseDetails())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDeleteCategory(month: String, year: String) {
        vm.deleteCategories(month, year)
    }

}