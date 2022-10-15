package com.example.twitterclone

import android.content.res.Resources
import android.content.res.loader.ResourcesLoader
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.twitterclone.model.User

sealed class Screens(val route: String, val title: String, @DrawableRes val resourceId: Int) {

    object Favorite : Screens("homescreen", "Favorite", R.drawable.home)
    object Notification :
        Screens("homescreen", "Notification", R.drawable.notifications)

    object MyNetwork : Screens("homescreen", "MyNetwork", R.drawable.search)
    object Messages : Screens("messages", "Messages", R.drawable.messages)
    object Profile : Screens( "profile/{User}",title = "Profile",resourceId = R.drawable.home)
}

val items = listOf(
    Screens.Favorite,
    Screens.MyNetwork,
    Screens.Notification,
    Screens.Messages
)

