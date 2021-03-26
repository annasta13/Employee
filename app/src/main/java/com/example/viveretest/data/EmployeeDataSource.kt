package com.example.viveretest.data

import androidx.lifecycle.LiveData
import com.example.viveretest.data.local.Employee
import com.example.viveretest.utils.Result
import kotlinx.coroutines.flow.Flow

interface EmployeeDataSource {

    fun fetchEmployeeData(): Flow<Result<List<Employee>>>
    fun getEmployeeFromLocalDatabase(): LiveData<Result<List<Employee>>>
    suspend fun deleteEmployeeDataInLocalDatabase(employee: Employee)
    suspend fun deleteAllEmployeeDataInLocalDatabase()
    suspend fun updateEmployeeDataInLocalDatabase(nik: Long, name: String, genre: String, currentNik: Long)
    suspend fun insertEmployee(employee: List<Employee>)
    fun getEmployeeByNik(nik: Long): LiveData<Result<Employee>>
}