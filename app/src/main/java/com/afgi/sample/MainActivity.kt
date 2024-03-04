package com.afgi.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.afgi.mg.lib.BuildConfig
import com.afgi.mg.lib.LOADED_AD
import com.afgi.mg.lib.initialize
import com.afgi.mg.lib.requestLargeBanner
import com.afgi.mg.lib.requestNative
import com.afgi.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    var adsNativeBanner :LinearLayout ?=null
    var adsContainer : LinearLayout ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        initialize()

        adsNativeBanner = findViewById(R.id.adsNativeBanner)
        adsContainer = findViewById(R.id.adsContainer)


        bannerLoading(adsNativeBanner!!)

        nativeAdsLoadingNative(
            binding.cardContainer,
            binding.adsContainer,
            binding.adsContainerAppLovin,
            binding.adsContainerBanner
        )
*/

    }

    fun bannerLoading(adsContainer: LinearLayout) {
        requestLargeBanner(
            "/6499/example/banner"
        ) { linearLayout: LinearLayout?, s: String ->
            if (s == LOADED_AD) {
                Log.e("TAG", "bannerLoading loaded: ", )
                adsContainer.removeAllViews()
                adsContainer.addView(linearLayout)
            } else {
                Log.e("TAG", "bannerLoading failed: ", )
            }
        }
    }

    val listColor = intArrayOf(com.afgi.mg.lib.R.color.white, com.afgi.mg.lib.R.color.black, com.afgi.mg.lib.R.color.black, com.afgi.mg.lib.R.color.black)

    fun nativeAdsLoadingNative(
        cardView: CardView,
        adsContainer: LinearLayout,
        adsContainerAppLovin: LinearLayout,
        adsContainerBanner: LinearLayout,

    ) {
                    requestNative(
                        color = listColor,
                        if (BuildConfig.DEBUG) "/6499/example/native" else "/6499/example/native"
                    ) { linearLayout: LinearLayout?, status: String ->
                        if (status == LOADED_AD) {
                            Log.e("TAG", "native loaded: ", )
                            adsContainer.visibility = View.VISIBLE
                            adsContainer.removeAllViews()
                            adsContainer.addView(linearLayout)
                        } else {
                            Log.e("TAG", "native failed: -----"+status )
                        }
                    }
                  //  nativeCustomAds(adsContainer) }}

    }




}