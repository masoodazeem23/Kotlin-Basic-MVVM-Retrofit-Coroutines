package com.example.employeemanagementmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "employee")
data class Data(

    val employee_age: Int,
    val employee_name: String,
    val employee_salary: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val profile_image: String?
): Serializable