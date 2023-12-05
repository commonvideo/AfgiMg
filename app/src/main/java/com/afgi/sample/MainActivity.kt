package com.afgi.sample

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.afgi.mg.lib.LOADED_AD
import com.afgi.mg.lib.requestAppLovinReward
import com.afgi.mg.lib.requestGoogleInterstitialReward
import com.afgi.mg.lib.requestGoogleReward
import com.afgi.mg.lib.requestLargeBanner

class MainActivity : AppCompatActivity() {

    var adsNativeBanner: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adsNativeBanner = findViewById(R.id.adsNativeBanner)

        /*bannerLoading(adsNativeBanner!!)
          requestGoogleInterstitialReward("ca-app-pub-3940256099942544/5354046379") { isLoaded, error ->
              Log.e("TAG", "requestGoogleInterstitialReward loaded: $isLoaded $error")
              requestGoogleReward("ca-app-pub-3940256099942544/5224354917") { isLoaded, error ->
                  Log.e("TAG", "requestGoogleReward loaded: $isLoaded $error")
                  requestAppLovinReward("039959318e8e9303") { isLoaded, error ->
                      Log.e("TAG", "requestAppLovinReward loaded: $isLoaded $error")
                  }
              }
          }*/
    }

    fun bannerLoading(adsContainer: LinearLayout) {
        requestLargeBanner(
            "/6499/example/banner"
        ) { linearLayout: LinearLayout?, s: String ->
            if (s == LOADED_AD) {
                Log.e("TAG", "bannerLoading loaded: ")
                adsContainer.removeAllViews()
                adsContainer.addView(linearLayout)
            } else {
                Log.e("TAG", "bannerLoading failed: ")
            }
        }
    }
}