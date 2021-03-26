package com.example.viveretest.ui.edit_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.viveretest.R
import com.example.viveretest.databinding.ActivityEditBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit.view.*
import timber.log.Timber

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {

    private val viewModel: EditViewModel by viewModels()
    private var _viewDataBinding: ActivityEditBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        viewDataBinding.apply {
            lifecycleOwner = this@EditActivity
            viewmodel = viewModel
        }

        var nameEdit = ""
        var nikEdit = ""
        var genreEdit = ""
        val nik = intent.getLongExtra("nik", 0)
        viewModel.getEmployee(nik).observe(this) {

        }

        viewModel.currentEmployee.observe(this) {
            val binding = viewDataBinding.root

            binding.bt_save.setOnClickListener {
                nameEdit = binding.et_name.text.toString() ?: ""
                nikEdit = binding.et_nik.text.toString() ?: ""
                genreEdit = binding.et_name.text.toString() ?: ""
                if (nameEdit.isNotEmpty() && nikEdit.isNotEmpty() && genreEdit.isNotEmpty()) {
                    viewModel.saveEmployee(nikEdit.toLong(), nameEdit, genreEdit, nik)
                    finish()
                }
            }
        }
    }


    /*=====================================================================================================================================
   on destroy*/
    override fun onDestroy() {
        _viewDataBinding = null
        super.onDestroy()
    }

}