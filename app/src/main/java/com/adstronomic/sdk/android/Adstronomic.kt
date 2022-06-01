package com.adstronomic.sdk.android

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

public class Adstronomic {

    companion object {

        private var api: String = ""
        private var campaignId: String = ""
        private var deviceId: String = ""
        private var context: Context? = null
        private var layout: ConstraintLayout? = null
        private var videoDelay: Long? = null

        private var bannerAdObject: AdObject = AdObject.empty()
        private var bannerAdImage: RequestCreator? = null

        private var interstitialAdObject: AdObject = AdObject.empty()
        private var interstitialAdVideo: VideoView? = null
        private var interstitialAdVideoBuffer: VideoView? = null

        private var rewardedAdObject: AdObject = AdObject.empty()
        private var rewardedAdVideo: VideoView? = null
        private var rewardedAdVideoBuffer: VideoView? = null

        private var bannerAdBuffer: AdObject = AdObject.empty()
        private var interstitialAdBuffer: AdObject = AdObject.empty()
        private var rewardedAdBuffer: AdObject = AdObject.empty()

        fun initialize(campaignId: String, context: Context, layout: ConstraintLayout? = null) {
            if (Config.version == "1.0.0") {
                this.api = Config.link
                this.campaignId = campaignId
                this.deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
                this.context = context
                this.layout = layout
                this.videoDelay = Config.videoDelay

                this.fetchBanner()
                this.fetchInterstitial()
                this.fetchRewarded()

                Log.i("Adstronomic", "Adstronomic is Ready !")
            } else Log.i("Adstronomic", "Initialization Failed !")
        }

        fun loadBanner(imageView: ImageView) {
            if (this.bannerAdObject.advertiserCampaignId != "") {
                this.adVizualized(AdEnum.Banner, this.bannerAdObject.advertiserCampaignId)

                this.bannerAdImage?.into(imageView)

                imageView.setOnClickListener {
                    this.redirect(AdEnum.Banner, this.bannerAdBuffer.advertiserCampaignId, this.bannerAdBuffer.redirection)
                }

                this.fetchBanner()
            } else Log.i("Adstronomic", "No Banner Ad")
        }

        fun fetchBanner() {
            var link = this.api + "getAd?adType=banner&publisherCampaignId=" + this.campaignId + "&deviceID=" + this.deviceId

            var request = JsonObjectRequest(Request.Method.GET, link, null, { response ->
                if (response.has("advertiserCampaignId") && response.has("url") && response.has("type") && response.has("redirection")) {
                    if(Config.bannerAdTypes.contains(response.getString("type"))) {
                        this.bannerAdBuffer = this.bannerAdObject
                        this.bannerAdObject = AdObject(
                            response.getString("advertiserCampaignId"),
                            response.getString("url"),
                            response.getString("type"),
                            response.getString("redirection")
                        )

                        this.bannerAdImage = Picasso.get().load(this.bannerAdObject.url)
                    } else Log.i("Adstronomic", "Incorrect Type")
                } else Log.i("Adstronomic", "Incorrect JSON")
            }, { error ->
                Log.i("Adstronomic", "Incorrect URL")
            })

            Volley.newRequestQueue(this.context).add(request)
        }

        fun loadInterstitial() {
            if (this.interstitialAdObject.advertiserCampaignId != "") {
                this.adVizualized(AdEnum.Interstitial, this.interstitialAdObject.advertiserCampaignId)

                this.interstitialAdVideo = this.interstitialAdVideoBuffer
                this.interstitialAdVideo?.start()

                this.layout?.addView(this.interstitialAdVideo)

                var close = Button(this.context)
                var canClose = false

                close.setOnClickListener {
                    if (canClose) {
                        this.interstitialAdVideo?.pause()
                        this.interstitialAdVideo?.visibility = View.INVISIBLE
                        close.visibility = View.INVISIBLE
                    }
                }

                object: CountDownTimer(this.videoDelay!!, 1000) {
                    override fun onTick(p0: Long) {
                        close.text = (p0 / 1000 + 1).toString()
                    }

                    override fun onFinish() {
                        canClose = true
                        close.text = "X"
                    }
                }.start()

                this.interstitialAdVideo?.setOnTouchListener(
                    object: View.OnTouchListener {
                        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                            redirect(AdEnum.Interstitial, interstitialAdBuffer.advertiserCampaignId, interstitialAdBuffer.redirection)

                            interstitialAdVideo?.visibility = View.INVISIBLE
                            close.visibility = View.INVISIBLE

                            return view?.onTouchEvent(event) ?: true
                        }
                    }
                )

                if(this.layout != null && close != null) {
                    this.layout?.addView(close)
                    close.layoutParams.width = 120
                    close.layoutParams.height = 120
                }

                this.fetchInterstitial()
            } else Log.i("Adstronomic", "No Interstitial Ad")
        }

        fun fetchInterstitial() {
            var link = this.api + "getAd?adType=interstitial&publisherCampaignId=" + this.campaignId + "&deviceID=" + this.deviceId

            var request = JsonObjectRequest(Request.Method.GET, link, null, { response ->
                if (response.has("advertiserCampaignId") && response.has("url") && response.has("type") && response.has("redirection")) {
                    if(Config.interstitialAdTypes.contains(response.getString("type"))) {
                        this.interstitialAdBuffer = this.interstitialAdObject
                        this.interstitialAdObject = AdObject(
                            response.getString("advertiserCampaignId"),
                            response.getString("url"),
                            response.getString("type"),
                            response.getString("redirection"),
                        )

                        this.interstitialAdVideoBuffer = VideoView(this.context)
                        this.interstitialAdVideoBuffer?.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                        this.interstitialAdVideoBuffer?.setVideoURI(Uri.parse(this.interstitialAdObject.url))
                        this.interstitialAdVideoBuffer?.requestFocus()
                    } else Log.i("Adstronomic", "Incorrect Type")
                } else Log.i("Adstronomic", "Incorrect JSON")
            }, { error ->
                Log.i("Adstronomic", "Incorrect URL")
            })

            Volley.newRequestQueue(this.context).add(request)
        }

        fun loadRewarded() {
            if (this.rewardedAdObject.advertiserCampaignId != "") {
                this.adVizualized(AdEnum.Rewarded, this.rewardedAdObject.advertiserCampaignId)

                this.rewardedAdVideo = this.rewardedAdVideoBuffer
                this.rewardedAdVideo?.start()

                this.layout?.addView(this.rewardedAdVideo)

                var close = Button(this.context)
                var canClose = false

                close.setOnClickListener {
                    if (canClose) {
                        this.rewardedAdVideo?.pause()
                        this.rewardedAdVideo?.visibility = View.INVISIBLE
                        close.visibility = View.INVISIBLE
                    }
                }

                object: CountDownTimer(this.videoDelay!!, 1000) {
                    override fun onTick(p0: Long) {
                        close.text = (p0 / 1000 + 1).toString()
                    }

                    override fun onFinish() {
                        canClose = true
                        close.text = "X"
                    }
                }.start()

                this.rewardedAdVideo?.setOnTouchListener(
                    object: View.OnTouchListener {
                        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                            redirect(AdEnum.Rewarded, rewardedAdBuffer.advertiserCampaignId, rewardedAdBuffer.redirection)

                            rewardedAdVideo?.visibility = View.INVISIBLE
                            close.visibility = View.INVISIBLE

                            return view?.onTouchEvent(event) ?: true
                        }
                    }
                )

                if(this.layout != null && close != null) {
                    this.layout?.addView(close)
                    close.layoutParams.width = 120
                    close.layoutParams.height = 120
                }

                this.fetchRewarded()
            } else Log.i("Adstronomic", "No Rewarded Ad")
        }

        fun fetchRewarded() {
            var link = this.api + "getAd?adType=rewarded&publisherCampaignId=" + this.campaignId + "&deviceID=" + this.deviceId

            var request = JsonObjectRequest(Request.Method.GET, link, null, { response ->
                if (response.has("advertiserCampaignId") && response.has("url") && response.has("type") && response.has("redirection")) {
                    if(Config.rewardedAdTypes.contains(response.getString("type"))) {
                        this.rewardedAdBuffer = this.rewardedAdObject
                        this.rewardedAdObject = AdObject(
                            response.getString("advertiserCampaignId"),
                            response.getString("url"),
                            response.getString("type"),
                            response.getString("redirection"),
                        )

                        this.rewardedAdVideoBuffer = VideoView(this.context)
                        this.rewardedAdVideoBuffer?.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                        this.rewardedAdVideoBuffer?.setVideoURI(Uri.parse(this.rewardedAdObject.url))
                        this.rewardedAdVideoBuffer?.requestFocus()
                    } else Log.i("Adstronomic", "Incorrect Type")
                } else Log.i("Adstronomic", "Incorrect JSON")
            }, { error ->
                Log.i("Adstronomic", "Incorrect URL")
            })

            Volley.newRequestQueue(this.context).add(request)
        }

        fun adVizualized(adType: AdEnum, advertiserCampaignId: String) {
            var link = this.api + "adVisualized?adType=" + adType.get + "&publisherCampaignId=" + this.campaignId + "&advertiserCampaignId=" + advertiserCampaignId

            var request = JsonObjectRequest(Request.Method.GET, link, null, { response ->
            }, { error ->
                Log.i("Adstronomic", "Incorrect URL")
            })

            Volley.newRequestQueue(this.context).add(request)
        }

        fun redirect(adType: AdEnum, advertiserCampaignId: String, redirection: String) {
            var link = this.api + "click?clicked=true&type=" + adType.get + "&publisherCampaignId=" + this.campaignId + "&advertiserCampaignId=" + advertiserCampaignId

            var request = JsonObjectRequest(Request.Method.GET, link, null, { response ->
            }, { error ->
                Log.i("Adstronomic", "Incorrect URL")
            })

            Volley.newRequestQueue(this.context).add(request)

            this.context?.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(redirection))
            )
        }

    }

}
