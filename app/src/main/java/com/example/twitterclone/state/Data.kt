package com.example.twitterclone.state


import com.example.twitterclone.R
import com.example.twitterclone.model.TweetList
import com.example.twitterclone.model.Tweet
import com.example.twitterclone.model.User


val rndmcodeguy2002 = User(
    name = "Shantanu Mane",
    username = "RndmCodeGuy2002",
    avatar = R.drawable.avatar,
    banner = R.drawable.banner,
    bio = "Student | Athlete | Abstract \n\n\n\n and delusional...",
    following = 63,
    followers = 3,
    ispressed = false,
    verified = false
)

val rudrapratik = User(
    name = "Pratik Agrawal",
    username = "Rudra_Pratik",
    avatar = R.drawable.avatar_lead,
    banner = R.drawable.banner_lead,
    bio = "Google DSC Lead at @gdsc_rcoem.",
    following = 233,
    followers = 150,
    ispressed = true,
    verified = true
)

val tweetListContents = mutableListOf(
    TweetList("Profile", R.drawable.profile),
    TweetList("Lists", R.drawable.lists),
    TweetList("Twitter Circle", R.drawable.twittercircle),
    TweetList("Bookmarks", R.drawable.bookmarks),
    TweetList("Moments", R.drawable.moments),
    TweetList("Monetization", R.drawable.monetization),
    TweetList("Settings and Privacy", R.drawable.settings),
)
val settings = mutableListOf(
    TweetList(
        "Your Account",
        R.drawable.profile,
    ),
    TweetList(
        "Security and account access",
        R.drawable.lock,
    ),
    TweetList(
        "Privacy and safety",
        R.drawable.bolt,
    ),
    TweetList(
        "Notifications",
        R.drawable.notifications,
    ),
    TweetList(
        "Accessibility, display and languages",
        R.drawable.display,
    ),
//    DrawerRow("Additional resources",R.drawable.profile_photo,"Check out other places for helpful information to learn more about Twitter products and services.")
)

val tweets = mutableListOf(
    Tweet(
        rndmcodeguy2002,
        "So excited to attend this year's #cloudcommunityday at #persistent_systems @ Nagpur!!!\n\n#gdgc_ngp #ccdngp2022",
        R.drawable.post,
        495,
        193,
        2,
    ),
    Tweet(
        rudrapratik,
        "The BIGGEST, BADDEST AND BOLDEST DevFest is back in Nagpur!\n\nDiscover what DevFest Nagpur 2022 can do for you.\n\nRegister by scanning the QR Code or clicking the link below 👇",
        R.drawable.post_lead,
        495,
        193,
        2,
    ),
    Tweet(
        rndmcodeguy2002,
        "Error 404! Veg Biryani Does Not Exist! 😂😂",
        null, 200, 20, 4
    )
)