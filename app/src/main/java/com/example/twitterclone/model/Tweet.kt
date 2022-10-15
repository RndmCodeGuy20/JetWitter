package com.example.twitterclone.model

import androidx.annotation.DrawableRes

data class Tweet(
    val user: User,
    val tweet: String,
    @DrawableRes val image: Int?,
    var likes: Int,
//    var liked: Boolean,
    var retweets: Int,
//    var retweeted: Boolean,
    val comments: Int,
)