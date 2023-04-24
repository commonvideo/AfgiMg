package com.afgi.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.afgi.mg.lib.LOADED_AD
import com.afgi.mg.lib.requestBanner
import com.afgi.mg.lib.requestLargeBanner

class MainActivity : AppCompatActivity() {

    var adsNativeBanner :LinearLayout ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adsNativeBanner = findViewById(R.id.adsNativeBanner)

        bannerLoading(adsNativeBanner!!)
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
}