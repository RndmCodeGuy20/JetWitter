package com.example.twitterclone.model

import androidx.annotation.DrawableRes

data class TweetList(
    val setting: String,
    @DrawableRes val image: Int,
    val description: String? = null
)