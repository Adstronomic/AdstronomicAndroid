## [**🇺🇸 ENGLISH VERSION AVAILABLE 🇺🇸**](https://flrn.gitbook.io/adstronomic/adstronomic-android/adstronomic-installation-guide-android)



# **Adstronomic - Guide d'installation (Android)**



## <u>1 - Introduction</u>



Adstronomic est une plateforme publicitaire vous permettant d'exploiter et de synthétiser les données de vos utilisateurs, afin de leur proposer la publicité la plus adaptée à leurs besoins. En nous appuyant sur les spécificités de chaque jeu et de ses utilisateurs, couplé à une IA révolutionnaire, nous parvenons à identifier les publicités les plus pertinentes, afin d'augmenter les revenus de votre jeu. Pour y parvenir, Adstronomic met à votre disposition trois outils clé :

	- Une plateforme web sur laquelle vous pouvez paramètrer vos projets, et les publicités associées.
	- Une API permettant d'interagir avec les données d'Adstronomic.
	- Un SDK qui vous permet d'utiliser facilement et rapidement tout le potentiel d'Adstronomic

Chacun de ces outil est intuitif, afin de vous permettre de vous concentrer sur ce qui compte le plus pour vous : La réussite de votre projet. Dans ce guide, nous allons nous concentrer sur le troisième point : L'installation et l'utilisation du SDK, ici dans sa version Android. 📱

Veuillez noter que deux solutions s'offrent à vous pour utiliser Adstronomic : Si vous commencez un nouveau projet, nous vous invitons à clôner directement ce dépôt, et à l'utiliser comme base de travail. Vous pourrez ainsi sauter la section "2 - Installation d'Adstronomic". Toutefois, si votre projet est déjà bien avancé, vous préfererez sûrement installer manuellement le SDK à votre projet existant. Dans ce cas là, la section suivante est faite pour vous !



## <u>2 - Installation d'Adstronomic</u>



En tant que développeur Android, vous êtes sûrement familier avec Android Studio, l'outil de développement de Google. Nous allons donc le lancer, et créer un nouveau projet.

Vous pouvez démarrer depuis le template que vous voulez. Je vais choisir le "Empty Activity" pour avoir un projet le plus simple possible pour ce guide. Ensuite, configurez votre projet en mettant le nom, nom de package et emplacement que vous voulez. Concernant le langage, nous allons utiliser du Kotlin, et le SDK Android 21. Vous pouvez utilisez un SDK supérieur si vous le souhaitez, mais je vous déconseille d'en prendre un inférieur, pour ne pas avoir de soucis de compatibilité.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/1.png" alt="Adstronomic-Android-1" style="zoom:25%;" />

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/2.png" alt="Adstronomic-Android-2" style="zoom:25%;" />

Votre projet est maintenant crée. Toutefois, il s'agit d'un projet vide, qui n'intègre pas Adstronomic.

Pour commencer, jetez un oeil à la colonne de gauche. Vous devriez avoir Android de sélectionné tout en haut, et l'arborescence de vos différents packages en dessous. Faîtes un clic droit sur app > java, et cliquez sur New > Package. Sélectionnez le dossier "../app/src/main/java" et indiquez le nom "com.adstronomic.sdk.android".

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/3.png" alt="Adstronomic-Android-3" style="zoom:25%;" />

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/4.png" alt="Adstronomic-Android-4" style="zoom:25%;" />

Notez que comme j'avais déjà le package com.adstronomic.sdk.android à la création du projet, ces deux packages seront fusionnés dans Android Studio. Mais sur votre projet à vous, vous devriez avoir un second package, en dessous de votre package par défaut. Ouvrez maintenant ce second package.

Nous allons maintenant avoir besoin des sources du SDK Adstronomic. Pour cela, commencez par les [télécharger](https://drive.google.com/file/d/1lhuTI7s4zuih7RegNTvZerMPY7TtQFta/view?usp=sharing), puis déplacez-les dans le dossier app > src > main > java > com > adstronomic > sdk > android.

Enfin, je vous invite également à récupérer le [Layout](https://drive.google.com/file/d/16oF2R_ZhlgS78jYsDozUiZY94m6dM0rH/view?usp=sharing), et à l'ajouter au dossier app > src > main > res > layout. (Vous pouvez remplacez le fichier pré-existant) Il s'agit d'un layout prédéfini, qui contient tous les composants pour afficher nos différentes publicités. Toutefois, il s'agit d'un exemple de layout, et vous pouvez tout à fait créer vous-même ces composants.

Si vous avez bien suivi jusque là, vous devriez avoir la hiérarchie suivante sous les yeux :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/5.png" alt="Adstronomic-Android-5" style="zoom:25%;" />

Encore une fois, sur votre propre projet, vous aurez normalement un package distinct pour le SDK, et le MainActivity devrait être séparé dans un autre package.



Maintenant, nous allons nous attaquer aux librairies externes ! Pour cela, ouvrons le fichier "Gradle Scripts" > build.gradle. Nous avons normalement deux fichiers build.gradle, et nous allons modifier le second, celui qui définit les modules utilisés. Nous devons y trouver le bloc dependencies, et y insérer les lignes suivantes :

```
implementation 'com.android.volley:volley:1.2.1'
implementation 'com.squareup.picasso:picasso:2.71828'
```

Enfin, après avoir modifié ce fichier, un bandeau jaune devrait apparaître en haut de l'écran, nous proposant de synchroniser les modifications en cliquant sur "Sync Now", ce que nous allons faire.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/6.png" alt="Adstronomic-Android-6" style="zoom:25%;" />

Normalement, cette synchronisation devrait supprimer les erreurs présentes dans le SDK.

Nous allons nous assurer que le projet fonctionne correctement en le démarrant. Pour cela, sélectionnons un AVD dans la liste tout en haut, puis cliquons sur le bouton Run à sa droite. Si le SDK Adstronomic est correctement installé, un AVD devrait s'ouvrir, et afficher l'écran suivant :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/7.png" alt="Adstronomic-Android-7" style="zoom:25%;" />

Félicitations ! Vous venez d'ajouter Adstronomic à votre projet ! 🥳 La prochaine étape est maintenant de le configurer !



## <u>3 - Configuration d'Adstronomic</u>



Maintenant qu'Adstronomic est intégré à notre projet, nous allons voir comment le paramétrer pour qu'il récupère et envoie les bonnes données.

Pour cela, commençons par ouvrir le fichier MainActivity, contenu dans le package par défaut du projet. Celui-ci contient le code suivant :

```kotlin
package com.adstronomic.sdk.android

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

Notez que j'enlèverai les imports et les commentaires dans les exemples donnés, par soucis de simplicité.

La première étape est d'initialiser Adstronomic avec un campaignID, via la fonction suivante :

```kotlin
Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this)
```

🚨 Notez bien que le premier paramètre correspond à l'identifiant de votre campagne, tel qu'indiqué sur la plateforme web. L'identifiant indiqué ici corresponds à un compte de démonstration, qui peut vous servir à vérifier le bon fonctionnement d'Adstronomic, mais qui ne doit absoluement pas être déployé en prodution !

Le second paramètre correspond quand à lui au contexte courant, reférencé par l'attribut self.

Cette fonction va permettre à Adstronomic de se connecter correctement à l'API, et va automatiquement charger et mettre en cache une publicité de chaque type (Banner Ad, Interstitial Ad et Rewarded Ad) pour accélérer son affichage par la suite.

Enfin, une dernière étape importante : Adstronomic va avoir besoin d'accéder à Internet pour communiquer avec l'API. Nous devons donc l'y autoriser, en ajoutant la ligne suivante dans le fichier app > manifests > AndroidManifest.

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Maintenant qu'Adstronomic est correctement configuré, nous allons pouvoir charger nos publicités !



## <u>4 - Chargement d'une Banner Ad</u>



Une BannerAd est tout simplement une publicité sous forme d'image, habituellement affichée au bas de l'écran pendant une partie. L'intérêt de ce type de publicité est qu'elle ne bloque pas le reste de l'écran, et peut donc être affichée pendant toute une partie.

Dans Adstronomic, ces publicités sont représentées par des images, de type ImageView.

Nous allons donc manuellement en ajouter une à notre projet. Pour cela, sur Android Studio, ouvrez le fichier res > layout > activity_main.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/8.png" alt="Adstronomic-Android-8" style="zoom:25%;" />

Sélectionnez une ImageView dans la fenêtre des composants, ajoutez-là à notre layout, et assignez-lui l'ID bannerAdImage. Maintenant, ajoutez un Button, attribuez-lui l'ID bannerAdButton, et vous êtes prêts à afficher une Banner Ad.

Notez que si vous avez récupéré le layout de ce dépôt, ces composants sont déjà en place.

Habituellement, une telle publicité est présente en bas de l'écran, sur toute sa largeur, mais vous pouvez changer cela, si besoin.



Maintenant que notre layout est prêt, retournons à notre MainActivity. Nous allons commencer par ajouter un évenement sur le bouton, pour réagit à son clic.

```kotlin
(findViewById(R.id.bannerAdButton) as Button).setOnClickListener {

}
```

Une fois cela fait, il nous suffit d'afficher notre Banner Ad, avec la fonction suivante :

```kotlin
Adstronomic.loadBanner(findViewById(R.id.bannerAdImage))
```

Notez que cette fonction prends en paramètre une ImageView, dans laquelle sera affichée notre publicité.

Où appeler cette fonction ? Et bien, c'est à vous de voir. Vous pouvez l'appeler dès le début, afin que votre publicité apparaisse dès le lancement, ou attendre une action spécifique. Comme nous sommes ici sur un projet de test, j'ai fait en sorte de l'appeler au clic sur le bouton que nous venons de créer. Mais vous pouvez l'appeler à n'importe quel autre événement de notre application.

Si vous avez correctement suivi ces étapes, vous devriez avoir le code suivant sous les yeux :

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

Et en lançant notre AVD, et en cliquant sur notre bouton, notre Banner Ad devrait apparaître en bas de l'écran :

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/9.png" alt="Adstronomic-Android-9" style="zoom:25%;" />



## <u>5 - Chargement d'une Interstitial Ad</u>



Si vous avez réussi à afficher une Banner Ad, vous ne devriez pas avoir de difficulté pour l'affichage des Interstitial Ad et des Rewarded Ad, car elles s'utilisent presque de la même manière. L'avantage des publicité vidéo est qu'Adstronomic les initialise indépendamment, et nous n'avons pas à les créer dans le layout.

Nous allons seulement avoir besoin de récupérer un layout sur lequel afficher la vidéo. Pour cela, ouvrons à nouveau le fichier activity_main et cherchons-y un layout. Je vous suggère d'utiliser le ConstraintLayout, présent par défaut dans ce layout, mais vous pouvez créer un autre layout, par exemple pour afficher nos publicités sur une portion de l'écran seulement.

Une fois notre layout choisi, nous allons lui attribuer l'ID constraintLayout pour le récupérer dans notre script.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/10.png" alt="Adstronomic-Android-10" style="zoom:25%;" />

Une fois cela fait, nous allons créer, référencer avec l'ID interstitialAdButton, et mettre sur écoute un bouton, exactement comme nous l'avons fait pour la Banner Ad.

Une dernière étape pour que Adstronomic puisse afficher notre vidéo : Nous devons lui indiquer le layout utilisé. Pour cela, modifions la fonction Adstronomic.initialize, comme ceci :

```kotlin
Adstronomic.initialize("wJMvF7kouz0lsO4m3d5a", this, findViewById(R.id.constraintLayout))
```

Maintenant que cela est fait, il nous suffit d'appeler la fonction de chargement d'une Interstitial Ad, exactement comme nous l'avons fait pour les Banner Ad.

```kotlin
(findViewById(R.id.interstitialAdButton) as Button).setOnClickListener {
    Adstronomic.loadInterstitial()
}
```

En démarrant l'application et en cliquant sur le bouton correspondant, nous devrions avoir notre Interstitial Ad qui se lance en plein écran.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/11.png" alt="Adstronomic-Android-11" style="zoom:25%;" />



## <u>6 - Chargement d'une Rewarded Ad</u>



Prêts pour la dernière étape ?

Les Rewarded Ad sont des publicités vidéo semblables aux Interstitial Ad. La différence est que celles-çi ont un but marketing différent, et visent plus à récompenser une action spécifique. Techniquement parlant, leur fonctionnement est identique, sauf qu'il faut utiliser la fonction loadRewarded et non loadInterstitial.

Notez que, comme pour les Banner Ad et Interstitial Ad, vous pouvez lancer la vidéo à n'importe quel moment. Je l'ai fait ici lors du clic sur un nouveau bouton, mais vous pouvez le faire dès le lancement de l'application, ou à n'importe quel autre évènement.

Ces changements étant vraiment simples, vous devriez arriver à un résultat similaire au code suivant :

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

En lançant notre AVD, nous devrions voir notre Rewarded Ad apparaître sous nos yeux, de la même façon que l'Interstitial Ad à la section précédente.

<img src="https://raw.githubusercontent.com/Adstronomic/AdstronomicAndroid/master/Read%20Me/12.png" alt="Adstronomic-Android-12" style="zoom:25%;" />

Et voilà ! Vous êtes maintenant un pro d'Adstronomic ! 😎



## <u>7 - Conclusions et Resources</u>



Au travers de ce guide, nous avons installé, initialisé et utilisé le SDK Android d'Adstronomic. Bien sûr, ce SDK s'intègre au reste de l'écosystème Adstronomic, et je vous invite fortement à en apprendre davantage.

Pour cela, voici quelques liens qui vous seront utiles :

    Site Web : https://www.adstronomic.com
    Gestionnaire de Campagnes : https://app.adstronomic.com
    Nos CGU : https://terms.adstronomic.com
    Notre GitHub : https://www.github.com/adstronomic
    Notre Discord : https://discord.gg/Uz5EMFpWuU

La dernière étape pour vous est de vous inscrire sur notre gestionnaire de campagnes, d'y créer une campagne, y ajouter vos publicités, et indiquer votre campaignId au chargement de l'application.

Enfin, Adstronomic prospère grâce à nos utilisateurs, et nous nous devons de faire le maximum pour eux. Si vous avez la moindre remarque, question, ou suggestion à nous faire part, nous vous invitons à nous faire un retour via notre page de contact, ou notre Discord.

À très vite,

L'Équipe Adstronomic ✌️
