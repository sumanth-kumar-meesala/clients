package com.mebank.activities

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.mebank.R
import com.mebank.adapters.EmployeeAdapter
import com.mebank.retrofit.RetrofitInstance
import com.mebank.retrofit.enqueueKt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvEmployees.layoutManager = GridLayoutManager(this, 2)
        val employeeAdapter = EmployeeAdapter(baseContext)
        rvEmployees.adapter = employeeAdapter

        if (isNetworkAvailable()) {
            showProgress()

            RetrofitInstance.service.getEmployees().enqueueKt {
                onResponse = {
                    hideProgress()
                    employeeAdapter.addEmployees(it.body()!!)
                }

                onFailure = {
                    val ss = ""
                }
            }
        }
    }
}