package com.example.employeemanagementmvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeemanagementmvvm.repository.EmployeeRepository
import java.lang.IllegalArgumentException

class EmployeeViewModelProviderFactory(private val employeeRepository: EmployeeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown error")
    }
}