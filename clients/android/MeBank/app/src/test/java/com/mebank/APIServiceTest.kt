package com.mebank

import com.mebank.models.Employee
import com.mebank.retrofit.RetrofitInstance
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class APIServiceTest {

    @Test
    @Throws(IOException::class)
    fun getEmployees() {
        RetrofitInstance.initialize()
        val employeesCall: Call<List<Employee>> = RetrofitInstance.service.getEmployees()
        val employeeResponse: Response<List<Employee>> = employeesCall.execute()
        assertEquals(employeeResponse.code(), 200)
        assertNotNull(employeeResponse.body())
        assertEquals(true, employeeResponse.isSuccessful)
    }

    @Test
    @Throws(IOException::class)
    fun getEmployee() {
        RetrofitInstance.initialize()
        val employeeCall: Call<Employee> = RetrofitInstance.service.getEmployee(1)
        val employeeResponse: Response<Employee> = employeeCall.execute()
        assertEquals(employeeResponse.code(), 200)
        assertNotNull(employeeResponse.body())
        assertEquals(true, employeeResponse.isSuccessful)
    }
}