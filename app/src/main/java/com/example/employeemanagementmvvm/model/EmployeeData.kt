package com.example.employeemanagementmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "employee_data")
data class EmployeeData(

    @SerializedName("age")
    val age: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("salary")
    val salary: Int,
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
): Serializable
