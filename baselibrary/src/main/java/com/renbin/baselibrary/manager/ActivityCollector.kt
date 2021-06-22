package com.xiaobin.androidview.manager

import android.app.Activity

/**
 *data:2021/5/19
 *Author:renbin
 * activity管理器
 */
object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity( activity: Activity){
        activities.add(activity)
    }

    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    fun finishAll(){
        for (activcity in activities){
            if (!activcity.isFinishing){
                activcity.finish()
            }
        }
        activities.clear()
    }
}