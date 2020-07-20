package com.mebank.activities

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.mebank.R
import com.mebank.adapters.EmployeeAdapter
import com.mebank.retrofit.RetrofitInstance
import com.mebank.retrofit.enqueueKt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var employeeAdapter: EmployeeAdapter
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "In onCreate")

        initViews()

        getEmployees()
    }

    /*Initialize views*/
    private fun initViews() {
        rvEmployees.layoutManager = GridLayoutManager(this, 2)
        employeeAdapter = EmployeeAdapter(baseContext)
        rvEmployees.adapter = employeeAdapter
    }

    /*Get employees from API*/
    private fun getEmployees() {
        if (isNetworkAvailable()) {
            showProgress()

            //Getting data from API
            RetrofitInstance.service.getEmployees().enqueueKt {
                onResponse = {
                    hideProgress()

                    if (it.isSuccessful && it.body() != null) {
                        //Add Employees to adapter
                        val employees = it.body()!!
                        employeeAdapter.addEmployees(employees)
                    } else {
                        Log.e(
                            TAG,
                            "Error occurred while fetching data from in getEmployees : ${it.message()}"
                        )
                        showToast(it.message())
                    }
                }

                onFailure = {
                    Log.e(
                        TAG,
                        "Error occurred while fetching data from in getEmployees : ${it?.message
                        }"
                    )
                    hideProgress()
                    showToast(it?.message)
                }
            }
        }
    }
}