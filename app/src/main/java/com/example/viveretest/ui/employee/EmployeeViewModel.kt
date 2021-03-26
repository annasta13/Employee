package com.example.viveretest.ui.employee

import androidx.lifecycle.*
import com.example.viveretest.data.EmployeeRepositoryFunction
import com.example.viveretest.data.local.Employee
import com.example.viveretest.utils.Event
import com.example.viveretest.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeRepository: EmployeeRepositoryFunction) :
    ViewModel() {

    var _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading
    var error = MutableLiveData<Event<Unit>>()

    val employeeData =
        employeeRepository.getEmployeeFromLocalDatabase().switchMap {
            val result = MutableLiveData<List<Employee>>(emptyList())
            if (it is Result.Success) result.value = it.data
            result
        }

    var _onEditClicked = MutableLiveData<Event<Unit>>()
    val onEditClicked = _onEditClicked

    fun fetchData() {
        viewModelScope.launch {
            _loading.value = true
            val data = employeeRepository.fetchEmployeeData()
            if (data is Result.Success) setSuccess(data.data)
            else setError()
        }
    }

    private fun setSuccess(employeeData: List<Employee>) = viewModelScope.launch {
        _loading.value = false
        employeeRepository.insertEmployee(employeeData)
    }

    private fun setError() {
        _loading.value = false
        error.value = Event(Unit)
    }

    fun deleteEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.deleteEmployeeDataInLocalDatabase(employee)
    }
}