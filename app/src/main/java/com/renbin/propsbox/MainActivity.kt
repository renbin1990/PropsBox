package com.renbin.propsbox

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.renbin.baselibrary.BaseActivity
import com.renbin.sunnyweather.MainSunnyWeatherActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initView() {
        val screenWidth = getScreenWidth(MyApplication.context)
        val ll_item = findViewById<LinearLayout>(R.id.ll_item)
        val linearParams  = ll_item.layoutParams
        linearParams.height = screenWidth/2
        ll_item.layoutParams = linearParams

        findViewById<ImageView>(R.id.sunnyWeather).setOnClickListener {
            startActivity(Intent(this@MainActivity, MainSunnyWeatherActivity::class.java))
        }

        findViewById<ImageView>(R.id.myLove).setOnClickListener {
            startActivity(Intent(this@MainActivity, MyLoveMainActivity::class.java))
        }
    }

    override fun initData() {

    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }
}