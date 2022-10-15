package com.example.twitterclone.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val username: String,
    val avatar: Int,
    val banner: Int,
    val bio: String,
    val following: Int,
    val followers: Int,
    val ispressed: Boolean,
    val verified: Boolean,

): Parcelable