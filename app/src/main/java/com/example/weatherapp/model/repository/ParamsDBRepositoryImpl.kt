package com.example.weatherapp.model.repository

import com.example.weatherapp.Constants
import com.example.weatherapp.data.Params
import com.example.weatherapp.model.apis.ApiInterface
import retrofit2.Call

class ParamsDBRepositoryImpl : ParamsDBRepository {
    private val apiInterface = ApiInterface.create()

    override fun getParams(): Call<Params> {
        return apiInterface.getParams("Moscow", 5, Constants.APY_KEY)
    }
}