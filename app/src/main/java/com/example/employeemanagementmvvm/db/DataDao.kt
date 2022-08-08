package com.example.employeemanagementmvvm.db

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employeemanagementmvvm.model.Data


@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmpData(users: Data?) : Long

    @Update
    suspend fun updateEmpData(users: Data)

    @Delete
    suspend fun deleteEmpData(users: Data)

    @Nullable
    @Query("DELETE FROM employee")
    suspend fun deleteAllEmpData(): Unit

    @Query("SELECT * FROM employee")
    fun allEmpData(): LiveData<List<Data>>


}