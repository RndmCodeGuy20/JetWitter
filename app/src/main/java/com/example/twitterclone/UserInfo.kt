package com.example.twitterclone

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twitterclone.model.User
import com.example.twitterclone.state.rndmcodeguy2002
import com.example.twitterclone.ui.theme.MainTheme
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.LocationOn

@Composable
fun UserInfo(user: User?, showBio: Boolean = false) {
    Column(Modifier.padding(15.dp)) {
        if (user != null) {
            Text(
                text = user.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(text = "@${user.username}", color = Color.Gray)
            if (showBio) {
                Text(text = user.bio)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Row() {
                    Icon(
                        Icons.Rounded.LocationOn,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Nagpur, MH", color = Color.Gray, fontWeight = FontWeight.Light)
                }
                Row() {
                    Icon(
                        Icons.Outlined.DateRange,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "joined July 2022",
                        color = Color.Gray,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp)
            ) {
                Text(text = "${user.following}", fontWeight = FontWeight.Bold)
                Text(text = " Following   ")
                Text(text = "${user.followers}", fontWeight = FontWeight.Bold)
                Text(text = " Followers ")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    MainTheme {
        UserInfo(user = rndmcodeguy2002)
    }
}