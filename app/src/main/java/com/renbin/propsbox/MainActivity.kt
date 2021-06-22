package com.renbin.propsbox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.renbin.baselibrary.BaseActivity
import com.renbin.sunnyweather.MainSunnyWeatherActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initView() {
        findViewById<Button>(R.id.sunnyWeather).setOnClickListener {
            startActivity(Intent(this@MainActivity, MainSunnyWeatherActivity::class.java))
        }
    }

    override fun initData() {

    }
}