package com.example.weatherapp.model.apis

import com.example.weatherapp.data.Params
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/forecast.json")
    fun getParams(@Path("location") location: String,
                  @Query("days") days: Int,
                  @Query("api_key") apiKey : String
    ) : Call<Params>

    companion object {

        var BASE_URL = "https://api.weatherapi.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}