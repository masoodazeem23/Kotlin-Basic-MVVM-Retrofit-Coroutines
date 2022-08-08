package com.example.employeemanagementmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.employeemanagementmvvm.R
import com.example.employeemanagementmvvm.db.EmployeeDatabase
import com.example.employeemanagementmvvm.repository.EmployeeRepository
import kotlinx.android.synthetic.main.activity_employee.*

class EmployeeActivity : AppCompatActivity() {

    lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)


        val employeeRepository = EmployeeRepository(EmployeeDatabase(this))
        val viewModelProviderFactory = EmployeeViewModelProviderFactory(employeeRepository)
        employeeViewModel =ViewModelProvider(this,viewModelProviderFactory).get(EmployeeViewModel::class.java)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController= navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}