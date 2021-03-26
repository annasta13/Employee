package com.example.viveretest.data.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: List<Employee>)

    @Query("SELECT * FROM employee")
    fun getEmployee(): LiveData<List<Employee>>

    @Delete
    suspend fun deleteEmployeeByNIK(employee: Employee)

    @Query("UPDATE employee SET nik=:nik, name=:name, genre=:genre WHERE nik=:currentNik")
    suspend fun updateEmployee(nik: Long, name: String, genre: String, currentNik: Long)

    @Query("DELETE FROM employee")
    suspend fun deleteAllEmployee()

    @Query("SELECT * FROM employee WHERE nik= :nik")
    fun getEmployeeByNik(nik: Long): LiveData<Employee>
}