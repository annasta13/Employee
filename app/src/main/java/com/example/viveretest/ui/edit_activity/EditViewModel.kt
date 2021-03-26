package com.example.viveretest.ui.edit_activity

import androidx.lifecycle.*
import com.example.viveretest.data.EmployeeRepositoryFunction
import com.example.viveretest.data.local.Employee
import com.example.viveretest.utils.Event
import com.example.viveretest.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val employeeRepository: EmployeeRepositoryFunction) :
    ViewModel() {

    var _currentEmployee = MutableLiveData<Employee>()
    var _onSaveClicked = MutableLiveData<Event<Unit>>()
    val saveClicked = _onSaveClicked
    val currentEmployee: LiveData<Employee> = _currentEmployee

    fun getEmployee(nik: Long): LiveData<Employee> =
        employeeRepository.getEmployeeByNik(nik).switchMap {
            val result = MutableLiveData<Employee>()
            if (it is Result.Success) it.data.let { _currentEmployee.value = it }
            result
        }

    fun saveEmployee(nik: Long, name: String, genre: String, currentNik: Long) =
        viewModelScope.launch {
            employeeRepository.updateEmployeeDataInLocalDatabase(nik, name, genre, currentNik)
        }


}