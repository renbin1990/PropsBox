package com.renbin.baselibrary.utils

import android.content.Context
import android.widget.Toast
import com.renbin.baselibrary.BaseApplication

/**
 *data:2021/6/9
 *Author:renbin
 */

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.INSTANCE, this, duration).show()
}
fun Int.showToast( duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.INSTANCE, this, duration).show()
}