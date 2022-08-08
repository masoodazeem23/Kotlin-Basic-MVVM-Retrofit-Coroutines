package com.example.employeemanagementmvvm.repository

import com.example.employeemanagementmvvm.api.RetrofitInstance
import com.example.employeemanagementmvvm.db.EmployeeDAO
import com.example.employeemanagementmvvm.db.EmployeeDatabase
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import okhttp3.RequestBody

class EmployeeRepository(
   val db: EmployeeDatabase
) {

    suspend fun getAllEmployee() =
        RetrofitInstance.api.getAllEmployee()

    suspend fun createEmployee(employeeData: EmployeeData) =
        RetrofitInstance.api.createEmployee(employeeData)

    suspend fun insertEmployee(employee : EmployeeData){
        db.getEmployeeDao().insertEmp(employee)
    }

    suspend fun insertEmployeeData(employee : Data){
        db.getEmployeDataDao().insertEmpData(employee)
    }
    suspend fun deleteEmployeeData(employee : Data){
        db.getEmployeDataDao().deleteEmpData(employee)
    }

    suspend fun updateEmployeeData(id : Int ,employee : EmployeeData){
        RetrofitInstance.api.updateEmployee(id,employee)
    }

    fun getAllSavedEmployeeData() =
        db.getEmployeDataDao().allEmpData()


}