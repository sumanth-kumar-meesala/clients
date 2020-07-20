package com.mebank.adapters

import android.content.Context
import android.content.Intent
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mebank.R
import com.mebank.activities.EmployeeActivity
import com.mebank.models.Employee
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeAdapter(val context: Context) : RecyclerView.Adapter<EmployeeAdapter.EmployeeVH>() {

    private val TAG = EmployeeAdapter::class.java.simpleName
    var employees: MutableList<Employee> = ArrayList()

    /*Inflating the view for employees*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeVH {
        Log.i(TAG, "In onCreateViewHolder")

        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeVH(root)
    }

    /*Method to append employees to list
    * newEmployeesData : list of employees
    * */
    fun addEmployees(newEmployeesData: List<Employee>) {
        employees.addAll(newEmployeesData)
        notifyDataSetChanged()
    }

    /*Method to return size of employees*/
    override fun getItemCount(): Int {
        return employees.size
    }

    /*Binding the view to data*/
    override fun onBindViewHolder(employeeVH: EmployeeVH, position: Int) {
        Log.i(TAG, "In onBindViewHolder")
        employeeVH.bindData(position)
    }

    /*View holder class of the employee*/
    inner class EmployeeVH(private val view: View) : RecyclerView.ViewHolder(view) {

        /*Binding employee details
        * position : position of the item
        * */
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

            view.setOnClickListener {
                context.startActivity(Intent(context, EmployeeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra(EmployeeActivity.EMPLOYEE_ID, employee.id)
                })
            }
        }

    }
}
