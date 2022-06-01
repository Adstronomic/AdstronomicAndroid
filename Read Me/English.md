## [**🇫🇷 VERSION FRANÇAISE DISPONIBLE 🇫🇷**](https://github.com/Adstronomic/AdstronomicAndroid/blob/master/Read%20Me/French.md)



# **Adstronomic - Installation Guide (Android)**



## <u>1 - Introduction</u>



Adstronomic is an advertising platform that allows you to leverage and synthesize your users' data to deliver the most relevant advertising to them. By taking into account the specificities of each game and its users, coupled with a revolutionary AI, we can identify the most relevant ads to increase your game's revenues. To achieve this, Adstronomic offers three key tools :

	- A web platform where you can set up your projects and associated ads.
	- An API that allows you to interact with Adstronomic data.
	- An SDK that allows you to quickly and easily use the full potential of Adstronomic

Each of these tools is intuitive, so you can focus on what matters most to you : The success of your project. In this guide, we will focus on the third point : Installing and using the SDK, here in its Android version. 📱

Please note that there are two solutions available to you : If you start a new project, we invite you to clone this repository directly, and use it as a working base. This will allow you to skip the section "2 - Creating a new project". However, if your project is already well advanced, you will probably prefer to install the SDK to your existing project. In this case, the next section is for you !



## <u>2 - Creating a new project</u>



As an Android developer, you are probably familiar with Android Studio, Google's development tool. So let's launch it, and create a new project.

You can start from any template you want. I'm going to choose "Empty Activity" to have the simplest project possible for this guide. Then, configure your project by putting the name, package name and location you want. Concerning the language, we will use Kotlin, and the Android 21 SDK. You can use a higher SDK if you want, but I advise you not to use a lower one, to avoid compatibility problems.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/1.png" alt="Adstronomic-Android-1" style="zoom:25%;" />

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/2.png" alt="Adstronomic-Android-2" style="zoom:25%;" />

Your project is now created. However, it is an empty project, which does not include Adstronomic.

To start, take a look at the left column. You should have Android selected at the top, and your package tree below it. Right click on app > java, and click on New > Package. Select the folder "../app/src/main/java" and specify the name "com.adstronomic.sdk.android".

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/3.png" alt="Adstronomic-Android-3" style="zoom:25%;" />

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/4.png" alt="Adstronomic-Android-4" style="zoom:25%;" />

Note that since I already had the com.adstronomic.sdk.android package when I created the project, these two packages will be merged in Android Studio. But on your project, you should have a second package, below your default package. Now open this second package.

Now we will need the Adstronomic SDK sources. To do this, first [download](https://drive.google.com/file/d/1lhuTI7s4zuih7RegNTvZerMPY7TtQFta/view?usp=sharing), then move them to the app > src > main > java > com > adstronomic > sdk > android folder.

Finally, I also invite you to get the [Layout](https://drive.google.com/file/d/16oF2R_ZhlgS78jYsDozUiZY94m6dM0rH/view?usp=sharing), and add it to the app > src > main > res > layout folder. (You can replace the pre-existing file) This is a predefined layout, which contains all the components to display our different ads. However, this is an example layout, and you can create these components yourself.

If you have followed this far, you should have the following hierarchy in front of you :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/5.png" alt="Adstronomic-Android-5" style="zoom:25%;" />

Again, on your own project, you will normally have a separate package for the SDK, and the MainActivity should be separate in another package.



Now we'll tackle the external libraries ! To do this, let's open the file "Gradle Scripts" > build.gradle. We normally have two build.gradle files, and we will modify the second one, the one that defines the modules used. We have to find the dependencies block, and insert the following lines :

```
implementation 'com.android.volley:volley:1.2.1'
implementation 'com.squareup.picasso:picasso:2.71828'
```

Finally, after modifying this file, a yellow banner should appear at the top of the screen, suggesting us to synchronize the modifications by clicking on "Sync Now", which we will do.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/6.png" alt="Adstronomic-Android-6" style="zoom:25%;" />

Normally, this synchronization should remove the errors present in the SDK.

We will make sure that the project works properly by starting it. To do this, select an AVD from the list at the top and click on the Run button to the right of it. If the Adstronomic SDK is correctly installed, an AVD should open, displaying the following screen :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/7.png" alt="Adstronomic-Android-7" style="zoom:25%;" />

Congratulations ! You've just added Adstronomic to your project ! 🥳 The next step is now to configure it !



## <u>3 - SDK configuration</u>



Now that Adstronomic is integrated into our project, let's see how to set it up so that it retrieves and sends the right data.

To do this, let's start by opening the MainActivity file, which is contained in the project's default package. It contains the following code :

```kotlin
package com.adstronomic.sdk.android

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

Note that I will remove the imports and comments in the examples given, for the sake of simplicity.

The first step is to initialize Adstronomic with a campaignID, via the following function :

```kotlin
Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this)
```

🚨 Note that the first parameter is the campaign ID, as indicated on the web platform. The username shown here is a demo account, which can be used to check how Adstronomic works, but should definitely not be deployed in production !

The second parameter is the current context, referenced by the self attribute.

This function will allow Adstronomic to connect correctly to the API, and will automatically load and cache an ad of each type (Banner Ad, Interstitial Ad and Rewarded Ad) to speed up its display later on.

Finally, one last important step : Adstronomic will need access to the Internet to communicate with the API. So we need to allow it to do so, by adding the following line in the app > manifests > AndroidManifest file.

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Now that Adstronomic is set up correctly, we can start loading our ads !



## <u>4 - Chargement d'une Banner Ad</u>



A BannerAd is simply an advertisement in the form of an image, usually displayed at the bottom of the screen during a game. The advantage of this type of display is that it does not block the rest of the screen, and can therefore be displayed throughout the game.

In Adstronomic, these ads are represented by images, of type ImageView.

So we will manually add one to our project. To do so, in Android Studio, open the file res > layout > activity_main.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/8.png" alt="Adstronomic-Android-8" style="zoom:25%;" />

Select an ImageView from the component window, add it to our layout, and assign it the ID bannerAdImage. Now add a Button, assign it the ID bannerAdButton, and you are ready to display a Banner Ad.

Note that if you have retrieved the layout from this repository, these components are already in place.

Usually, such an ad is present at the bottom of the screen, across its entire width, but you can change that, if needed.



Now that our layout is ready, let's go back to our MainActivity. We'll start by adding an event to the button, to react to its click.

```kotlin
(findViewById(R.id.bannerAdButton) as Button).setOnClickListener {

}
```

Once this is done, we just need to display our Banner Ad, with the following function :

```kotlin
Adstronomic.loadBanner(findViewById(R.id.bannerAdImage))
```

Note that this function takes as parameter an ImageView, in which our ad will be displayed.

Where to call this function ? Well, that's up to you. You can call it from the beginning, so that your ad appears as soon as it is launched, or wait for a specific action. Since we are on a test project here, I made sure to call it when the button we just created is clicked. But you can call it at any other event in our application.

If you have followed these steps correctly, you should have the following code in front of you :

```kotlin
package com.adstronomic.sdk.android

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this)

        (findViewById(R.id.bannerAdButton) as Button).setOnClickListener {
            Adstronomic.loadBanner(findViewById(R.id.bannerAdImage))
        }
    }
}
```

And by launching our AVD, and clicking on our button, our Banner Ad should appear at the bottom of the screen :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/9.png" alt="Adstronomic-Android-9" style="zoom:25%;" />



## <u>5 - Loading an Interstitial Ad</u>



If you have successfully displayed a Banner Ad, you should have no trouble displaying Interstitial Ad and Rewarded Ad, as they are used in almost the same way. The advantage of video ads is that Adstronomic initializes them independently, and we don't have to create them in the layout.

We will only need to get a layout to display the video on. To do this, let's open the activity_main file again and look for a layout. I suggest you use the ConstraintLayout, which is present in this layout by default, but you can create another layout, for example to display our ads on a portion of the screen only.

Once we have chosen our layout, we will assign it the constraintLayout ID to retrieve it in our script.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/10.png" alt="Adstronomic-Android-10" style="zoom:25%;" />

Once that's done, we'll create, reference with the InterstitialAdButton ID, and listener a button, just like we did for the Banner Ad.

One last step to get Adstronomic to display our video : We need to tell it which layout we're using. To do this, let's modify the Adstronomic.initialize function, like this :

```kotlin
Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this, findViewById(R.id.constraintLayout))
```

Now that this is done, we just need to call the function to load an Interstitial Ad, exactly as we did for the Banner Ad.

```kotlin
(findViewById(R.id.interstitialAdButton) as Button).setOnClickListener {
    Adstronomic.loadInterstitial()
}
```

By starting the application and clicking on the corresponding button, we should have our Interstitial Ad launching in full screen.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/11.png" alt="Adstronomic-Android-11" style="zoom:25%;" />



## <u>6 - Loading a Rewarded Ad</u>



Ready for the final step ?

Rewarded Ads are video ads similar to Interstitial Ads. The difference is that they have a different marketing goal, and are more focused on rewarding a specific action. Technically speaking, they work the same way, except that you have to use the loadRewarded function and not loadInterstitial.

Note that, as with the Banner Ad and Interstitial Ad, you can launch the video at any time. I did it here when a new button was clicked, but you can do it as soon as the application is launched, or at any other event.

Since these changes are really simple, you should end up with something similar to the following code :

```kotlin
package com.adstronomic.sdk.android

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
```

When launching our AVD, we should see our Rewarded Ad appear before our eyes, in the same way as the Interstitial Ad in the previous section.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/12.png" alt="Adstronomic-Android-12" style="zoom:25%;" />

That's it ! You are now an Adstronomic pro ! 😎



## <u>7 - Conclusions and Resources</u>



Through this guide, we have installed, initialized and used Adstronomic's Android SDK. Of course, this SDK integrates with the rest of the Adstronomic ecosystem, and I strongly encourage you to learn more about it.

To do so, here are a few links that will be helpful :

    Website : https://www.adstronomic.com
    Campaign Manager : https://app.adstronomic.com
    Our GCU : https://terms.adstronomic.com
    Our GitHub : https://www.github.com/adstronomic
    Our Discord : https://discord.gg/Uz5EMFpWuU

The last step for you is to sign up for our campaign manager, create a campaign, add your ads, and specify your campaignId when the application loads.

Finally, Adstronomic thrives on our users, and it is our duty to do our best for them. If you have any comments, questions, or suggestions, we invite you to send us a feedback via our contact page or our Discord.

See you soon,

The Adstronomic Team ✌️
