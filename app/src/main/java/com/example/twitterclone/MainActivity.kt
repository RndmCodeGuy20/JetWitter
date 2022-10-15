package com.example.twitterclone

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.twitterclone.model.User
import com.example.twitterclone.state.tweetListContents
import com.example.twitterclone.state.tweets
import com.example.twitterclone.ui.theme.MainTheme
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                // A surface container using the 'background' color from the theme
                val viewModel by viewModels<MyViewModel>()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationController(navController = navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationController(navController: NavHostController, viewModel: MyViewModel?) {
    NavHost(
        navController = navController,
        startDestination = "homescreen",
    ) {
        composable("homescreen") {
            HomeScreenTweets(navController)
        }
        composable("settings") {
            Settings(navController, viewModel)
        }
        composable(
            "profile/{User}",
            arguments = listOf(navArgument("User") {
                type = UserType()
            })
        ) {
            val User = it.arguments?.getParcelable<User>("User")
            Profile(navController, user = User)
        }
    }
}


@Composable
fun CommonScaffold(
    navController: NavController? = null,
    content: @Composable() () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopBar(scope = scope, scaffoldState = scaffoldState)

        },
        drawerContent = {

            SideMenuContainer(
                navController = navController,
                scope = scope,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = {
            MainBottomBar(navController = navController)
        },
        floatingActionButton = {
            ComposeTweetButton(navController = navController)
        }
    ) {
        content()
    }
}

@Composable
fun ComposeTweetButton(navController: NavController?) {
    FloatingActionButton(
        onClick = { navController?.navigate("composetweet") },
        backgroundColor = Color(0xFF03A9F4)
    ) {
        Image(
            painter = painterResource(id = R.drawable.compose),
            contentDescription = "compose tweet",
            modifier = Modifier.size(30.dp)
        )
    }
}


@Composable
fun TweetRow(
    setting: String,
    @DrawableRes image: Int,
    description: String? = null,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
//                .padding(20.dp)
        )

        Column(Modifier.padding(30.dp, 10.dp, 10.dp, 10.dp)) {
            Text(text = setting, fontSize = 25.sp)
            if (description != null) {
                Text(text = description)
            }
        }
    }
    Divider()

}

@Composable
fun SideMenuContainer(
    navController: NavController?,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {

    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .padding(20.dp, 20.dp, 20.dp, 10.dp)
                .clickable {
                    val json = Uri.encode(Gson().toJson(tweets[0].user))
                    navController?.navigate("profile/$json") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
            Avatar(user = tweets[0].user)
        }
        UserInfo(user = tweets[0].user)
        Divider()
        LazyColumn() {
            items(tweetListContents) { content ->
                when (content.setting) {
                    "Settings and Privacy" -> {
                        TweetRow(
                            content.setting, content.image, modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clickable {
                                    navController?.navigate("settings")
                                }
                        )
                    }
                    "Profile" -> {
                        TweetRow(
                            content.setting, content.image, modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clickable {
                                    val json = Uri.encode(Gson().toJson(tweets[0].user))
                                    navController?.navigate("profile/$json") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                        )
                    }
                    else -> {
                        TweetRow(
                            content.setting, content.image, modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                }


            }

        }
    }
}

@Composable
fun MainBottomBar(navController: NavController?) {
    BottomAppBar {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            contentColor = Color.White,
            elevation = 10.dp
        ) {
            if (navController != null) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Image(
                                painter = painterResource(id = screen.resourceId),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun MainTopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .size(30.dp)
                .clickable {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
        )
        Image(
            painter = painterResource(id = R.drawable.twitter),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.trends),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )

    }
}


class MyViewModel : ViewModel() {
    var tweetText by mutableStateOf("")
    fun onTweetChange(newString: String) {
        tweetText = newString
    }

    var settingText by mutableStateOf("")
    fun onSettingTextChange(newString: String) {
        settingText = newString
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainTheme {
        MainBottomBar(navController = null)
    }
}