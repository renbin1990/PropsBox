package com.renbin.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 *data:2021/6/22
 *Author:renbin
 */
data class PlaceResponse(val status:String ,val places : List<Place>)

class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

class Location(val lng: String, val lat: String)