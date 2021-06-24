package com.renbin.sunnyweather.logic

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.renbin.sunnyweather.logic.dao.PlaceDao
import com.renbin.sunnyweather.logic.model.Place
import com.renbin.sunnyweather.logic.model.Weather
import com.renbin.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 *data:2021/6/22
 *Author:renbin
 */
object Repository {
    fun searchPlaces(query : String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status =="ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        }catch (e :Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        Log.e("----->"," $lng   +  $lat")
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }

            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status} daily response status is ${dailyResponse.status}"))
            }
        }

    }

    private fun <T> fire(context: CoroutineContext ,block : suspend() -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            }catch (e : Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavePlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSave()
}