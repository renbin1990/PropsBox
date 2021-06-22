package com.renbin.propsbox

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.renbin.baselibrary.BaseApplication

/**
 *data:2021/6/18
 *Author:renbin
 */
class MyApplication : BaseApplication() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}