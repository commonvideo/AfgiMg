package com.afgi.sample

import android.app.Application
import com.afgi.mg.lib.initInMobi
import com.afgi.mg.lib.initialize
import com.afgi.mg.lib.testDeviceIds
import com.afgi.mg.lib.testDeviceIdsFacebook
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initialize()

        initInMobi("1670153853003")

        AppOpenManager(this)
        testDeviceIdsFacebook("02c9dacf-875d-4e66-98ad-7a73b4280d7d")
        testDeviceIdsFacebook("39b39339-d312-4031-bf14-c97a82f1c4a3")
        testDeviceIdsFacebook("3aaa49c4-f36c-4704-81c1-beb99ec0c88f")
        testDeviceIds(
            listOf(
                "2B1C398D39E69EF758154530678FA16F"
            )
        )


    }
}