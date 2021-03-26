package com.example.viveretest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey
    @ColumnInfo(name = "nik") val nik: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "genre") val genre: String
)
