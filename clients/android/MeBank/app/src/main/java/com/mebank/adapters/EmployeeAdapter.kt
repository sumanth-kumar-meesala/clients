package com.mebank.adapters

import android.content.Context
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mebank.R
import com.mebank.models.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeAdapter(val context: Context) : RecyclerView.Adapter<EmployeeAdapter.EmployeeVH>() {

    var employees: MutableList<Employee> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeVH {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeVH(root)
    }

    fun addEmployees(newEmployeesData: List<Employee>) {
        employees.addAll(newEmployeesData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(employeeVH: EmployeeVH, position: Int) {
        employeeVH.bindData(position)
    }

    inner class EmployeeVH(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(position: Int) {
            val employee = employees[position]
            view.tvName.text = "${employee.first_name} ${employee.last_name}"
            view.tvDateOfBirth.text = employee.birth_date
            view.tvGender.text = employee.gender

            employee.thumbImage?.let {
                val imageByteArray: ByteArray = Base64.decode(it, Base64.DEFAULT)
                Glide.with(context).load(imageByteArray)
                    .apply(RequestOptions().placeholder(R.drawable.ic_account_circle))
                    .into(itemView.ivEmployee)
            }
        }

    }
}
