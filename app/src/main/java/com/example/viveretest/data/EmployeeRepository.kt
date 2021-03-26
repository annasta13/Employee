package com.example.viveretest.data

import androidx.lifecycle.LiveData
import com.example.viveretest.data.local.Employee
import com.example.viveretest.data.local.EmployeeLocalDataSource
import com.example.viveretest.data.remote.ApiService
import com.example.viveretest.utils.Result

interface EmployeeRepositoryFunction {
    suspend fun fetchEmployeeData(): Result<List<Employee>>
    fun getEmployeeFromLocalDatabase(): LiveData<Result<List<Employee>>>
    suspend fun deleteEmployeeDataInLocalDatabase(employee: Employee)
    suspend fun deleteAllEmployeeDataInLocalDatabase()
    suspend fun updateEmployeeDataInLocalDatabase(nik: Long, name: String, genre: String, currentNik: Long)
    suspend fun insertEmployee(employeeData: List<Employee>)
    fun getEmployeeByNik(nik: Long): LiveData<Result<Employee>>
}

class EmployeeRepository(
    private val localDataSource: EmployeeLocalDataSource,
    private val apiService: ApiService
) : EmployeeRepositoryFunction {
    override suspend fun fetchEmployeeData(): Result<List<Employee>> {
        return try {
            val employee = apiService.getEmployee().employeeResponse.map { it.asDatabaseEntity() }
            localDataSource.insertEmployee(employee)
            Result.Success(employee)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    override fun getEmployeeFromLocalDatabase(): LiveData<Result<List<Employee>>> {
        return localDataSource.getEmployeeFromLocalDatabase()
    }

    override suspend fun deleteEmployeeDataInLocalDatabase(employee: Employee) {
        return localDataSource.deleteEmployeeDataInLocalDatabase(employee)
    }

    override suspend fun deleteAllEmployeeDataInLocalDatabase() {
        return localDataSource.deleteAllEmployeeDataInLocalDatabase()
    }

    override suspend fun updateEmployeeDataInLocalDatabase(nik: Long, name: String, genre: String, currentNik: Long) {
        localDataSource.updateEmployeeDataInLocalDatabase(nik, name, genre, currentNik)
    }

    override suspend fun insertEmployee(employeeData: List<Employee>) {
        localDataSource.insertEmployee(employeeData)
    }

    override fun getEmployeeByNik(nik: Long): LiveData<Result<Employee>> {
        return localDataSource.getEmployeeByNik(nik)
    }

}