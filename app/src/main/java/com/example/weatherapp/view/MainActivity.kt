package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.Location
import com.example.weatherapp.data.Params
import com.example.weatherapp.view.adapters.CustomAdapter
import com.example.weatherapp.view.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainActivityViewModel = MainActivityViewModel()

    private lateinit var mParamsRecycler: RecyclerView
    private lateinit var mParamsAdapter: CustomAdapter

    private lateinit var tvLocation: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvCondition: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var ivCondition: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()
        mViewModel.getParams()
    }

    private fun initObservers() {
        mViewModel.apply {
            params.observe(this@MainActivity) {
                mParamsAdapter = CustomAdapter(it)
                mParamsRecycler.adapter = mParamsAdapter
                setParams(it,null)
            }
        }
    }

    private fun setParams(paramsList: Params, location: Location?) {
        val params = paramsList?.forecast?.forecastday?.get(0)
        tvLocation.text = location?.name
        tvDate.text = params?.date
        tvTemp.text = params?.day?.avgtemp_c.toString()
        tvCondition.text = params?.day?.condition?.text
        tvHumidity.text = params?.day?.avghumidity.toString()
        tvWind.text = params?.day?.maxwind_kph.toString()

        Picasso.get().load("https://cdn.weatherapi.com/weather/64x64/" + params?.day?.condition?.icon).into(ivCondition)
    }

    private fun initViews() {
        mParamsRecycler = findViewById<RecyclerView>(R.id.recyclerview)
        mParamsRecycler.layoutManager = GridLayoutManager(this, 1)
        tvLocation = findViewById(R.id.tv_location)
        tvDate = findViewById(R.id.tv_date)
        tvTemp = findViewById(R.id.tv_temp)
        tvCondition = findViewById(R.id.tv_condition)
        tvHumidity = findViewById(R.id.tv_humidity)
        tvWind = findViewById(R.id.tv_wind)
        ivCondition = findViewById(R.id.iv_condition)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
}