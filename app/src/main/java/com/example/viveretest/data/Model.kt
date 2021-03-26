package com.example.viveretest.data

import com.example.viveretest.data.local.Employee
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("data") val employeeResponse: List<EmployeeResponse>
)

data class EmployeeResponse(
    @SerializedName("NIK")
    val nik: String,
    @SerializedName("NAMA")
    val name: String,
    @SerializedName("JENIS_KELAMIN")
    val genre: String
)

fun EmployeeResponse.asDatabaseEntity(): Employee {
    return Employee(nik = nik.toLong(), name = name, genre = genre)
}