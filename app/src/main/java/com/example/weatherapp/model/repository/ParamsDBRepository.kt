package com.example.weatherapp.model.repository

import com.example.weatherapp.data.Params
import retrofit2.Call

interface ParamsDBRepository {
    fun getParams(): Call<Params>
}