package com.example.employeemanagementmvvm.db

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData

@Dao
interface EmployeeDAO {


    @Insert
    suspend fun insertEmp(users: EmployeeData) : Long

    @Update
    suspend fun updateEmp(users: EmployeeData)

    @Delete
    suspend fun deleteEmp(users: EmployeeData)

    @Nullable
    @Query("DELETE FROM employee_data")
    suspend fun deleteAllEmp(): Unit

    @Query("SELECT * FROM employee_data")
    fun allEmp():LiveData<List<EmployeeData>>

}