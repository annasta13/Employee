package com.example.viveretest.di

import android.content.Context
import androidx.room.Room
import com.example.viveretest.data.EmployeeRepository
import com.example.viveretest.data.EmployeeRepositoryFunction
import com.example.viveretest.data.local.AppDatabase
import com.example.viveretest.data.local.EmployeeLocalDataSource
import com.example.viveretest.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.databaseName)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEmployeeLocalDataSource(appDatabase: AppDatabase): EmployeeLocalDataSource {
        return EmployeeLocalDataSource(appDatabase.employeeDao())
    }

    @Singleton
    @Provides
    fun provideEmployeeRepository(
        localDataSource: EmployeeLocalDataSource,
        apiService: ApiService
    ): EmployeeRepositoryFunction {
        return EmployeeRepository(localDataSource, apiService)
    }
}