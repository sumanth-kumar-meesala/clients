package com.mebank.retrofit

import com.mebank.models.Employee
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employees/{id}")
    fun getEmployee(@Path("id") id: Number): Call<Employee>
}