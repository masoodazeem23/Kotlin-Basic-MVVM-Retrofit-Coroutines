package com.example.employeemanagementmvvm.model

data class EmployeeResponse(
    val data: MutableList<Data>,
    val message: String,
    val status: String
)
