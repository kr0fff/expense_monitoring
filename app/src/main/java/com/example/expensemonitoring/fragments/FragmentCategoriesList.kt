package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemonitoring.CategoryAdapter
import com.example.expensemonitoring.CategoryOnClickActions
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.FragmentCategoriesListBinding
import com.example.expensemonitoring.fragments.viewModels.CategoriesViewModel
import com.example.expensemonitoring.fragments.viewModels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentCategoriesList : Fragment(), CategoryOnClickActions {
    private val vm: CategoriesViewModel by viewModels { ViewModelFactory(Repositories) }
    private lateinit var binding: FragmentCategoriesListBinding
    private lateinit var adapter: CategoryAdapter

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoriesListBinding.inflate(inflater)
        adapter = CategoryAdapter(resources, requireContext(), this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        vm.refreshCategories()
        Log.d("ON_CREATE_VIEW_LIST", "called")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.categories.observe(viewLifecycleOwner) {
            //  vm.refreshCategories()
            Log.d("PROGRESS_DELETION", "From Fragment, liveData : ${vm.categories.value}")
            adapter.submitList(it)
        }


        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && binding.addButton.visibility == View.VISIBLE) {
                binding.addButton.hide()
            } else if (scrollY < oldScrollY && binding.addButton.visibility != View.VISIBLE) {
                binding.addButton.show()
            }
        }
        binding.sortByDate.setOnClickListener {
            vm.sortListByDateAndMonth()
            Log.d("SORTED_BY_DATE_MONTH", "${vm.categories.value}")
        }
        binding.sortByValue.setOnClickListener {

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
        Log.d("AFTER_DELETE", vm.categories.value.toString())

    }

    fun sortByDate(view: View) {

    }
}