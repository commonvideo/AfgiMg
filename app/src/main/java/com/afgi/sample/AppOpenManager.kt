package com.afgi.sample

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.afgi.*
import com.afgi.mg.lib.isLoadedAppOpen
import com.afgi.mg.lib.requestAppOpen
import com.afgi.mg.lib.requestApplovinAppOpen
import com.afgi.mg.lib.showAppOpen

class AppOpenManager(val app: App) : LifecycleObserver,
    Application.ActivityLifecycleCallbacks {
    init {
        Log.e("TAG", "111111111111111")

        app.registerActivityLifecycleCallbacks(this)
        Log.e("TAG", "2222222222222222222")

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    private var isShowingAd = false
    private val TAG = "AppOpenManager"
    private var currentActivity: Activity? = null
    private var loadTime: Long = 0

   @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onStart() {
       Log.e(TAG, "onStart----------------hii")

       showAdIfAvailable()
        Log.e(TAG, "onStart")
    }




    /** Request an ad  */
    fun fetchAd() {
        if (isLoadedAppOpen()) {
            return
        }
        appOpenRequest()

    }

    private fun appOpenRequest() {
        app.requestApplovinAppOpen("id") {

        }
    }


    private fun showAdIfAvailable() {
        if (!isShowingAd && isLoadedAppOpen()) {
            currentActivity?.showAppOpen {
                fetchAd()
            }
        } else {
            Log.e(TAG, "Can not show ad.")
            fetchAd()
        }
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

}