package com.example.employeemanagementmvvm.ui

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import com.example.employeemanagementmvvm.model.EmployeeDataResponse
import com.example.employeemanagementmvvm.model.EmployeeResponse
import com.example.employeemanagementmvvm.repository.EmployeeRepository
import com.example.employeemanagementmvvm.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.*

class EmployeeViewModel(val employeeRepository: EmployeeRepository): ViewModel() {
    val allEmployee : MutableLiveData<Resource<EmployeeResponse>?> = MutableLiveData()
    val createEmployee : MutableLiveData<Resource<EmployeeDataResponse>?> = MutableLiveData()
    val updateEmployee : MutableLiveData<Resource<EmployeeDataResponse>?> = MutableLiveData()
    val TAG= "EmployeeViewModel"
    val allEmployeePage = 1


    init {
        getAllEmployee()
    }

    fun getAllEmployee() = viewModelScope.launch {
        allEmployee.postValue(Resource.Loading())
        val response = employeeRepository.getAllEmployee()
        allEmployee.postValue(handleAllEmployeeResponse(response))
    }

    private fun handleAllEmployeeResponse(response: Response<EmployeeResponse>): Resource<EmployeeResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResp->
                return Resource.Success(resultResp)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleCreateEmployeeResponse(response: Response<EmployeeDataResponse>): Resource<EmployeeDataResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultResp->
                return Resource.Success(resultResp)
            }
        }
        return Resource.Error(response.message())
    }


    fun createEmployee(employee:EmployeeData) = viewModelScope.launch {
        createEmployee.postValue(Resource.Loading())
        val response1 = employeeRepository.createEmployee(employee)
        createEmployee.postValue(handleCreateEmployeeResponse(response1))

    }

    fun insert(employee: EmployeeData): Job = viewModelScope.launch {
        employeeRepository.insertEmployee(employee)
    }

    fun insertEmployeeData(employee: Data): Job = viewModelScope.launch {
        employeeRepository.insertEmployeeData(employee)
    }

    fun deleteEmployeeData(employee: Data): Job = viewModelScope.launch {
        employeeRepository.deleteEmployeeData(employee)
    }

    fun updateEmployeeData(id:Int,employee: EmployeeData): Job = viewModelScope.launch {
        employeeRepository.updateEmployeeData(id,employee)
    }

    fun getSavedEmployeeData() = employeeRepository.getAllSavedEmployeeData()





}