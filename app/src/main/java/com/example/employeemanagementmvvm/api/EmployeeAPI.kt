package com.example.employeemanagementmvvm.api

import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData
import com.example.employeemanagementmvvm.model.EmployeeDataResponse
import com.example.employeemanagementmvvm.model.EmployeeResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmployeeAPI {

    @GET("employees")
    suspend fun getAllEmployee() : Response<EmployeeResponse>

    @POST("create")
    suspend fun createEmployee(@Body employeeData: EmployeeData) : Response<EmployeeDataResponse>

    @POST("update/{id}")
    suspend fun updateEmployee(@Path("id") id: Int, @Body employeeData: EmployeeData) : Response<EmployeeDataResponse>

}