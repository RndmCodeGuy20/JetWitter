package com.example.twitterclone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.twitterclone.ui.theme.MainTheme

@Composable
fun Settings(navController: NavController? = null, viewModel: MyViewModel?) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Scaffold(topBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    IconButton(onClick = { /*TODO*/
                        navController?.navigate("homescreen")
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                    Text(text = "Settings", fontWeight = FontWeight.Bold, fontSize = 27.sp)
                }
            }) {
//                val textState = remember { mutableStateOf(TextFieldValue()) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(10.dp)
                ) {
                    if (viewModel != null) {
                        OutlinedTextField(
                            value = viewModel.settingText,
                            onValueChange = { viewModel.onSettingTextChange(it) },
                            placeholder = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Search, contentDescription = null)
                                    Text(text = "Search", textAlign = TextAlign.Center)
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(50.dp),

                            )
                    }
                    LazyColumn(Modifier.padding(10.dp)) {
                        items(com.example.twitterclone.state.settings) { content ->
                            com.example.twitterclone.TweetRow(
                                content.setting,
                                content.image, content.description,
                                modifier = androidx.compose.ui.Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            )
                        }

                    }
                }


            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun SettingsView() {
    MainTheme {
        Settings(viewModel = viewModel())
    }
}