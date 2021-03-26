package com.example.viveretest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        val databaseName: String = "app_database"
    }
}