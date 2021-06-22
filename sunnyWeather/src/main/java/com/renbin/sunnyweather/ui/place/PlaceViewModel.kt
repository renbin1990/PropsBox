package com.renbin.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.renbin.sunnyweather.logic.Repository
import com.renbin.sunnyweather.logic.model.Place
import retrofit2.http.Query

/**
 *data:2021/6/22
 *Author:renbin
 */
class PlaceViewModel :ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){
        Repository.searchPlaces(it)
    }

    fun searchPlaces(query: String){
        searchLiveData.value = query
    }
}