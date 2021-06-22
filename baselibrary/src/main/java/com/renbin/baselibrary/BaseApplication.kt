package com.renbin.baselibrary

import android.app.Application

open class BaseApplication : Application() {

    companion object {
        const val TOKEN = "WMOcTi4jWxxy5j9l" // 填入你申请到的令牌值
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}