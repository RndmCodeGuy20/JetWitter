package com.example.twitterclone

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.twitterclone.model.User
import com.example.twitterclone.state.rudrapratik
import com.example.twitterclone.state.tweets
import com.example.twitterclone.ui.theme.MainTheme


@Composable
fun Profile(navController: NavController? = null, user: User?) {
    Surface() {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            ConstraintLayout() {
                val (banner, closeButton, moreButton, avatar, content, followButton) = createRefs()

                Box(modifier = Modifier.constrainAs(banner) {
                }) {
                    Banner(user)
                }
                Box(
                    modifier = Modifier
                        .constrainAs(avatar) {
                            bottom.linkTo(banner.bottom, -20.dp)
                        }
                        .padding(5.dp, 5.dp, 5.dp, 5.dp)
                        .offset(0.dp, 20.dp)

                ) {
                    Avatar(user)
                }
                Box(modifier = Modifier.constrainAs(content) {
                    top.linkTo(avatar.bottom)
                }) {
                    ProfileContent(user, navController)
                }
                TopBar(navController)
                Box(modifier = Modifier.constrainAs(followButton) {
                    end.linkTo(banner.end, 5.dp)
                    top.linkTo(banner.bottom)
                }) {
                    if (user != null) {
                        FollowButton(user.ispressed)
                    }
                }


            }
        }
    }

}

@Composable
fun Banner(user: User?) {
    if (user != null) {
        Image(
            painter = painterResource(id = user.banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}

@Composable
fun FollowButton(isPressed: Boolean) {
    Button(
        onClick = { }, shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                if (!isPressed) 0xFF03A9F4 else 0xFFAAB8C2
            ),
        ),
    ) {
        Text(text = if (isPressed) "Following ✔️" else "Follow")
    }
}

@Composable
fun TopBar(navController: NavController?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier,
            onClick = {
                navController?.navigate("screenhome")
            }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }

        IconButton(modifier = Modifier, onClick = {}) {
            Icon(Icons.Filled.MoreVert, contentDescription = null, tint = Color.White)
        }

    }
}

@Composable
fun Avatar(user: User?) {
    if (user != null) {

        Column(
            Modifier
                .size(87.dp)
                .clip(RoundedCornerShape(200.dp))
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = user.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

    }
}

@Composable
fun ProfileContent(user: User?, navController: NavController?) {
    Column {
        Spacer(modifier = Modifier.height(5.dp))
        Column {
            UserInfo(user, true)
        }
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column {
                Text(
                    text = "Tweets",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Spacer(
                    modifier = Modifier
                        .background(Color(0xFF1DA1F2))
                        .height(5.dp)
                        .width(52.dp)
                )
            }
            Text(text = "Tweets & Replies", fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(text = "Media", fontWeight = FontWeight.Bold, color = Color.Gray)
        }
        Divider()
        tweets.filter { it.user == user }.forEach { tweet ->
            TweetCard(tweet, navController)
        }
    }
}


@Preview
@Composable
fun ProfileView() {
    MainTheme() {
        Profile(user = rudrapratik)
    }
}