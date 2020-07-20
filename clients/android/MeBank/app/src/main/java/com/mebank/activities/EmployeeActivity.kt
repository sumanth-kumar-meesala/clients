package com.mebank.activities

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mebank.R
import com.mebank.models.Employee
import com.mebank.retrofit.RetrofitInstance
import com.mebank.retrofit.enqueueKt
import kotlinx.android.synthetic.main.activity_employee.*


class EmployeeActivity : BaseActivity() {

    companion object {
        //Key to get employee id from intent
        const val EMPLOYEE_ID = "EMPLOYEE_ID"
    }

    private val TAG = EmployeeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        Log.i(TAG, "In onCreate")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        getEmployeeDetails()
    }

    /*Function to get employee details from API*/
    private fun getEmployeeDetails() {
        val employeeId = intent.getIntExtra(EMPLOYEE_ID, 0)

        Log.d(TAG, "Employee Id : $employeeId")

        if (isNetworkAvailable()) {
            showProgress()

            //Getting single employee details from API
            RetrofitInstance.service.getEmployee(employeeId).enqueueKt {
                onResponse = { it ->
                    hideProgress()

                    if (it.isSuccessful && it.body() != null) {
                        Log.d(TAG, "Employee details fetched successfully")
                        displayEmployeeDetails(it.body()!!)
                    } else {
                        Log.e(
                            TAG,
                            "Error occurred while fetching data from in getEmployee : ${it.message()}"
                        )
                        showToast(it.message())
                    }
                }
                onFailure = {
                    Log.e(
                        TAG,
                        "Error occurred while fetching data from in getEmployee : ${it?.message}"
                    )

                    hideProgress()
                    showToast(it?.message)
                }
            }
        }
    }

    /*Function to display employee details fetched from API*/
    private fun displayEmployeeDetails(employee: Employee) {
        tvName.text = "${employee.first_name} ${employee.last_name}"
        tvDateOfBirth.text = employee.birth_date
        tvGender.text = employee.gender

        employee.thumbImage?.let {
            val imageByteArray: ByteArray = Base64.decode(it, Base64.DEFAULT)
            Glide.with(this@EmployeeActivity).load(imageByteArray)
                .apply(RequestOptions().placeholder(R.drawable.ic_account_circle))
                .into(ivEmployee)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //Handling back press in the toolbar
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}