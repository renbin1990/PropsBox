package com.renbin.baselibrary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaobin.androidview.manager.ActivityCollector

/**
 *data:2021/6/21
 *Author:renbin
 */
abstract class BaseActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        initView()
        initData()
        ActivityCollector.addActivity(this)
    }

    abstract fun getLayoutResource(): Int

    abstract fun initView()

    abstract fun initData()


    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    companion object{
        inline fun <reified T> startActivity(context: Context) {
            val intent = Intent(context, T::class.java)
            context.startActivity(intent)
        }

        inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
            val intent = Intent(context, T::class.java)
            intent.block()
            context.startActivity(intent)
        }
    }
}