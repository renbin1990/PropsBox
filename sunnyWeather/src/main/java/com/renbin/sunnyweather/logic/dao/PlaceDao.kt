package com.renbin.sunnyweather.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.renbin.baselibrary.BaseApplication
import com.renbin.sunnyweather.logic.model.Place

/**
 *data:2021/6/23
 *Author:renbin
 */
object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit {
            putString("place",Gson().toJson(place))
        }
    }

    fun getSavePlace() : Place{
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSave() = sharedPreferences().contains("place")

    private fun sharedPreferences() = BaseApplication.INSTANCE.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}