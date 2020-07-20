package com.mebank.activities

import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mebank.R
import com.mebank.retrofit.RetrofitInstance
import com.mebank.retrofit.enqueueKt
import kotlinx.android.synthetic.main.activity_employee.*


class EmployeeActivity : BaseActivity() {

    companion object {
        const val EMPLOYEE_ID = "EMPLOYEE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val employeeId = intent.getIntExtra(EMPLOYEE_ID, 0)

        if (isNetworkAvailable()) {
            showProgress()

            RetrofitInstance.service.getEmployee(employeeId).enqueueKt {
                onResponse = {
                    hideProgress()

                    val employee = it.body()!!

                    tvName.text = "${employee.first_name} ${employee.last_name}"
                    tvDateOfBirth.text = employee.birth_date
                    tvGender.text = employee.gender

                    employee.thumbImage?.let {
                        val imageByteArray: ByteArray = Base64.decode(it, Base64.DEFAULT)
                        Glide.with(this@EmployeeActivity).load(imageByteArray)
                            .apply(RequestOptions().placeholder(R.drawable.ic_account_circle))
                            .into(ivEmployee)
                    }

                    onFailure = {
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}