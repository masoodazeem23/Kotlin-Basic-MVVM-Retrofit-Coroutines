package com.example.employeemanagementmvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.adapter.EmployeeAdapter
import com.example.employeemanagementmvvm.ui.EmployeeActivity
import com.example.employeemanagementmvvm.ui.EmployeeViewModel
import com.example.employeemanagementmvvm.util.Resource
import kotlinx.android.synthetic.main.fragment_all_employee.*
import kotlinx.android.synthetic.main.fragment_search_employee.*



class AllEmployeeFragment : Fragment(R.layout.fragment_all_employee) {

    lateinit var viewModel: EmployeeViewModel
    lateinit var empAdapter: EmployeeAdapter
    val TAG = "AllEmployeeFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EmployeeActivity).employeeViewModel
        setupRecyclerView()


        // click on recyclerview item
        empAdapter.setOnItemCLickListener {
            Log.d(TAG,"Clicked on item $it")
            val bundle = Bundle().apply {
                putSerializable("employee",it)
            }
            findNavController().navigate(
                R.id.action_allEmployeeFragment_to_employeeDetailFragment,
                bundle)
        }

        viewModel.allEmployee.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        empAdapter.differ.submitList(newsResponse.data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "error in api calling: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {}
            }

        })

    }



    private fun hideProgressBar() {
        paginationProgressBarAll.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarAll.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        empAdapter = EmployeeAdapter()
        rvBreakingNews.apply {
            adapter = empAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }





}