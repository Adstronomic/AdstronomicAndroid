package com.adstronomic.sdk.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this, findViewById(R.id.constraintLayout))

        (findViewById(R.id.bannerAdButton) as Button).setOnClickListener {
            Adstronomic.loadBanner(findViewById(R.id.bannerAdImage))
        }

        (findViewById(R.id.interstitialAdButton) as Button).setOnClickListener {
            Adstronomic.loadInterstitial()
        }

        (findViewById(R.id.rewardedAdButton) as Button).setOnClickListener {
            Adstronomic.loadRewarded()
        }
    }
}