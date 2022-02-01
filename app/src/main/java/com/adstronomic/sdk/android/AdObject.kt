package com.adstronomic.sdk.android

public class AdObject {

    public var advertiserCampaignId: String = ""
    public var url: String = ""
    public var type: String = ""
    public var redirection: String = ""

    constructor(advertiserCampaignId: String, url: String, type: String, redirection: String) {
        this.advertiserCampaignId = advertiserCampaignId
        this.url = url
        this.type = type
        this.redirection = redirection
    }

    companion object {

        fun empty(): AdObject {
            return AdObject("", "", "", "")
        }

    }

}