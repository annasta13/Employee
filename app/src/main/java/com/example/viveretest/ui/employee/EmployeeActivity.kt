package com.example.viveretest.ui.employee

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.viveretest.R
import com.example.viveretest.data.local.Employee
import com.example.viveretest.databinding.ActivityEmployeeBinding
import com.example.viveretest.ui.edit_activity.EditActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeActivity : AppCompatActivity(), EmployeeAdapter.OnItemClickListener {
    private val viewModel: EmployeeViewModel by viewModels()
    private var _viewDataBinding: ActivityEmployeeBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee)
        viewDataBinding.apply {
            lifecycleOwner = this@EmployeeActivity
            viewmodel = viewModel
            employeeAdapter = EmployeeAdapter().also { employeeList.adapter = it }
        }

        employeeAdapter.setClick(this)
        viewModel.employeeData.observe(this){
            if (it.isEmpty()) viewModel.fetchData()
        }

    }

    override fun onEditClicked(employee: Employee) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("nik", employee.nik)
        startActivity(intent)
    }

    override fun onDeleteClicked(employee: Employee) {
        viewModel.deleteEmployee(employee)
    }

    /*=====================================================================================================================================
   on destroy*/
    override fun onDestroy() {
        with(viewDataBinding) {
            employeeList.adapter = null
        }
        _viewDataBinding = null
        super.onDestroy()
    }


}