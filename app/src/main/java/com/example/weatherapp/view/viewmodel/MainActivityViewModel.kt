package com.example.weatherapp.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.Forecastday
import com.example.weatherapp.data.Params
import com.example.weatherapp.model.repository.ParamsDBRepository
import com.example.weatherapp.model.repository.ParamsDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private val _params = MutableLiveData<Params>()
    val params : LiveData<Params> = _params


    private val mParamsRepository: ParamsDBRepository = ParamsDBRepositoryImpl()

    fun getParams() {
        val response = mParamsRepository.getParams()
        response.enqueue(object : Callback<Params> {
            override fun onResponse(call: Call<Params>?, response: Response<Params>?) {
                Log.d("testLogs", "OnResponse Success ${call?.request()}")
                _params.postValue(response?.body())
            }

            override fun onFailure(call: Call<Params>, t: Throwable?) {
                Log.d("testLogs", "onFailure :  ${t?.message}")
            }
        })
    }
}