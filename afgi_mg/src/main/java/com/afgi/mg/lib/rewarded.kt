package com.afgi.mg.lib

import android.app.Activity
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener
import com.applovin.mediation.ads.MaxRewardedAd
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

private var isRewarded = false

fun Activity.requestAppLovinReward(
    id: String,
    callback: (isLoaded: Boolean, error: String) -> Unit
) {
    val rewardedAd = MaxRewardedAd.getInstance(
        id, this
    )
    rewardedAd.setListener(object : MaxRewardedAdListener {
        override fun onAdLoaded(maxAd: MaxAd?) {
            if (rewardedAd.isReady) {
                rewardedAd.showAd()
            }
        }

        override fun onAdDisplayed(maxAd: MaxAd?) {

        }

        override fun onAdHidden(maxAd: MaxAd?) {
            callback.invoke(isRewarded, "")
            isRewarded = false
        }

        override fun onAdClicked(maxAd: MaxAd?) {

        }

        override fun onAdLoadFailed(p0: String?, adError: MaxError?) {
            callback.invoke(isRewarded, adError.toString())
        }

        override fun onAdDisplayFailed(maxAd: MaxAd?, adError: MaxError?) {
            callback.invoke(isRewarded, adError.toString())
        }

        override fun onUserRewarded(maxAd: MaxAd?, p1: MaxReward?) {
            isRewarded = true
        }

        override fun onRewardedVideoStarted(p0: MaxAd?) {

        }

        override fun onRewardedVideoCompleted(p0: MaxAd?) {

        }
    })
    rewardedAd.loadAd()
}

fun Activity.requestGoogleReward(id: String, callback: (isLoaded: Boolean, error: String) -> Unit) {
    RewardedAd.load(
        this,
        id,
        AdRequest.Builder().build(), object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                callback.invoke(isRewarded, adError.toString())
            }

            override fun onAdLoaded(ad: RewardedAd) {
                ad.let {
                    it.show(this@requestGoogleReward) {
                        isRewarded = true
                    }
                }
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        callback.invoke(isRewarded, "")
                        isRewarded = false
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        super.onAdFailedToShowFullScreenContent(adError)
                        callback.invoke(isRewarded, adError.toString())
                    }
                }
            }
        })
}


fun Activity.requestGoogleInterstitialReward(
    id: String,
    callback: (isRewarded: Boolean, error: String) -> Unit
) {
    RewardedInterstitialAd.load(this, id,
        AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                callback.invoke(isRewarded, adError.toString())
            }

            override fun onAdLoaded(ad: RewardedInterstitialAd) {
                ad.let {
                    it.show(this@requestGoogleInterstitialReward) {
                        isRewarded = true
                    }
                }
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        callback.invoke(isRewarded, "")
                        isRewarded = false
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        super.onAdFailedToShowFullScreenContent(adError)
                        callback.invoke(isRewarded, adError.toString())
                    }
                }

            }
        })
}
