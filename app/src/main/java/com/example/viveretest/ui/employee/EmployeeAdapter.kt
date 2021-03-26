package com.example.viveretest.ui.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viveretest.data.local.Employee
import com.example.viveretest.databinding.ItemEmployeeBinding
import com.example.viveretest.ui.employee.EmployeeAdapter.EmployeeViewHolder

class EmployeeAdapter() : ListAdapter<Employee, EmployeeViewHolder>(EmployeeDiffCallback()) {
    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onEditClicked(employee: Employee)
        fun onDeleteClicked(employee: Employee)
    }

    fun setClick(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Employee?) {
            with(binding) {
                employee = item
                binding.btDelete.setOnClickListener { mListener!!.onDeleteClicked(item!!) }
                binding.btEdit.setOnClickListener { mListener!!.onEditClicked(item!!) }
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(layoutInflater, parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

@BindingAdapter("app:employeeList")
fun setEmployeeList(recyclerView: RecyclerView, employeeList: List<Employee>?) {
    employeeList?.let { (recyclerView.adapter as EmployeeAdapter).submitList(it) }
}

internal class EmployeeDiffCallback : ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.nik == newItem.nik
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}

