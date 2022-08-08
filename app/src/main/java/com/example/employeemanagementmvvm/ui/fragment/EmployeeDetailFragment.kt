package com.example.employeemanagementmvvm.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import com.example.employeemanagementmvvm.ui.EmployeeActivity
import com.example.employeemanagementmvvm.ui.EmployeeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_employee_detail.*


class EmployeeDetailFragment : Fragment(R.layout.fragment_employee_detail) {

    lateinit var viewModel: EmployeeViewModel
    val args: EmployeeDetailFragmentArgs by navArgs()
    lateinit var employee: Data


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as EmployeeActivity).employeeViewModel
        employee = args.employee
        profileName.text = employee.employee_name
        profileAge.text = employee.employee_age.toString() + " Years"
        profileSalary.text = employee.employee_salary.toString() + "$"

        fab.setOnClickListener {

            viewModel.insertEmployeeData(employee)
            Snackbar.make(view,"Employee Saved Successfully!!", Snackbar.LENGTH_SHORT).show()
        }

    }



}