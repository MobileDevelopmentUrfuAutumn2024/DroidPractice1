@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import MainScreenHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likes: Int = 0,
    dislikes: Int = 0
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        Icon(
                            modifier = Modifier.clickable { handler.onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    Row {
                        Icon(
                            modifier = Modifier
                                .clickable { handler.onLikeClicked() }
                                .padding(start = 10.dp),
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp),
                            text = likes.toString()
                        )
                    }
                    Row {
                        Icon(
                            modifier = Modifier
                                .clickable { handler.onDislikeClicked() }
                                .padding(start = 10.dp),
                            painter = painterResource(id = R.drawable.dislike),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp),
                            text = dislikes.toString()
                        )
                    }
                }
                Text(
                    text = "Карты теней",
                    style = Typography.titleLarge
                )
                Text(
                    text = stringResource(R.string.article_one_part_one),
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                )
                AsyncImage(
                    model = "https://habrastorage.org/r/w1560/webt/g6/dj/qh/g6djqh4wgzwvwxsw90mqzeqbhty.png",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.article_one_part_two),
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp),
                )
                Card(modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = stringResource(R.string.article_two_title),
                        modifier = Modifier.padding(16.dp),
                        color = if (read) Color.Gray else Color.Black
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(handler = object : MainScreenHandler {
        override fun onToOtherScreenClicked() {

        }

        override fun onToShareClicked() {

        }

        override fun onLikeClicked() {
        }

        override fun onDislikeClicked() {
        }
    })
}