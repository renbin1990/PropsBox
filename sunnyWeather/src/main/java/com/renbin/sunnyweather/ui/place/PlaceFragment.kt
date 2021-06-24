package com.renbin.sunnyweather.ui.place

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renbin.baselibrary.BaseFragment
import com.renbin.baselibrary.utils.showToast
import com.renbin.sunnyweather.MainSunnyWeatherActivity
import com.renbin.sunnyweather.R
import com.renbin.sunnyweather.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.fragment_place.*

/**
 *data:2021/6/21
 *Author:renbin
 */
class PlaceFragment : BaseFragment() {
    //使用了lazy函数这种懒加载技术来获取PlaceViewModel的实例，这是一种非常棒 的写法，
    // 允许我们在整个类中随时使用viewModel这个变量，而完全不用关心它何时初始化、 是否为空等前提条件。
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter: PlaceAdapter
    override fun getContentViewLayoutId(): Int = R.layout.fragment_place

    override fun initView(savedInstanceState: Bundle?) {
        if (activity is MainSunnyWeatherActivity && viewModel.isPlaceSaved()){
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView?.adapter = adapter

        searchPlaceEdit.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(this, Observer {
            val places = it.getOrNull()
            if (places != null){
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                "未能查询到任何地点".showToast()
                it.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}