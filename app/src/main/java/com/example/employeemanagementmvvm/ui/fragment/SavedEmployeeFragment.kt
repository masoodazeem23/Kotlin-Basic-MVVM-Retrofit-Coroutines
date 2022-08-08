package com.example.employeemanagementmvvm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.adapter.EmployeeAdapter
import com.example.employeemanagementmvvm.ui.EmployeeActivity
import com.example.employeemanagementmvvm.ui.EmployeeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_employee.*

class SavedEmployeeFragment : Fragment(R.layout.fragment_saved_employee) {

    lateinit var viewModel: EmployeeViewModel
    lateinit var employeeAdapter: EmployeeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EmployeeActivity).employeeViewModel
        setupRecyclerView()

        employeeAdapter.setOnItemCLickListener {
            val bundle =  Bundle().apply {
                putSerializable("employee",it)
            }
            findNavController().navigate(
                R.id.action_savedEmployeeFragment2_to_updateEmployeeFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val employee = employeeAdapter.differ.currentList[position]
                viewModel.deleteEmployeeData(employee)
                Snackbar.make(view,"Article Delete Successfully!!", Snackbar.LENGTH_LONG).apply{
                    setAction("Undo"){
                        viewModel.insertEmployeeData(employee)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedEmp)
        }

        viewModel.getSavedEmployeeData().observe(viewLifecycleOwner, Observer { article->
            employeeAdapter.differ.submitList(article)
        })


    }

    private fun setupRecyclerView() {
        employeeAdapter = EmployeeAdapter()
        rvSavedEmp.apply {
            adapter = employeeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}