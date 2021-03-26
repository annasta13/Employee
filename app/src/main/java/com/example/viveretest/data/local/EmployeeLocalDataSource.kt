package com.example.viveretest.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.viveretest.data.EmployeeDataSource
import com.example.viveretest.utils.Result
import kotlinx.coroutines.flow.Flow

class EmployeeLocalDataSource internal constructor(private val employeeDao: EmployeeDao) :
    EmployeeDataSource {
    override fun fetchEmployeeData(): Flow<Result<List<Employee>>> {
        throw Exception("no op")
    }

    override fun getEmployeeFromLocalDatabase(): LiveData<Result<List<Employee>>> {
        return try {
            return employeeDao.getEmployee().map { it -> Result.Success(it.map { it }) }
        } catch (e: Exception) {
            MutableLiveData(Result.Error(e))
        }
    }

    override suspend fun deleteEmployeeDataInLocalDatabase(employee: Employee) {
        employeeDao.deleteEmployeeByNIK(employee)
    }

    override suspend fun deleteAllEmployeeDataInLocalDatabase() {
        employeeDao.deleteAllEmployee()
    }

    override suspend fun updateEmployeeDataInLocalDatabase(nik: Long, name: String, genre: String, currentNik: Long) {
        employeeDao.updateEmployee(nik, name, genre, currentNik)
    }

    override suspend fun insertEmployee(employee: List<Employee>) {
        employeeDao.insertEmployee(employee)
    }

    override fun getEmployeeByNik(nik: Long): LiveData<Result<Employee>> {
        return try {
            return employeeDao.getEmployeeByNik(nik).map { Result.Success(it) }
        } catch (e: Exception) {
            MutableLiveData(Result.Error(e))
        }
    }
}