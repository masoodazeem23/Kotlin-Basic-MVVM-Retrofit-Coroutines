package com.example.employeemanagementmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.employeemanagementmvvm.model.Data
import com.example.employeemanagementmvvm.model.EmployeeData

@Database(
    entities = [EmployeeData::class , Data::class],
    version = 2
)

abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDAO
    abstract fun getEmployeDataDao(): DataDao

    companion object{
        @Volatile
        private var instance:EmployeeDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EmployeeDatabase::class.java,
                "employee_db.db"
            ).build()
    }
}