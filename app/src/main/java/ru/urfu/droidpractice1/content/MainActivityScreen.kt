@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainActivity
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likesCount: Int = 0,
    dislikeCount: Int = 0
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(id = R.string.article_title))
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
                Row() {
                    Icon(
                        modifier = Modifier
                            .clickable { handler.onLikeClicked() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = likesCount.toString()
                    )
                    Icon(
                        modifier = Modifier
                            .clickable { handler.onDislikeClicked() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = dislikeCount.toString()
                    )
                }

                Text(
                    text = ""
                )

                Text(
                    text = "«За рулем» рассказал про модели, о которых не любят вспоминать их производители: некоторые были ошибками, а другие просто опередили свое время.",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://www.zr.ru/_next/image/?url=https%3A%2F%2Fimg.zr.ru%2F_ah%2Fimg%2F7YiNTpDJ8tWEeu0KAAEtHQ&w=1920&q=75",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "«Это теперь никто не удивляется внедорожникам под маркой Bentley и Maserati, полноприводным Ferrari или переднеприводным Мерседесам. А в былые годы фирменная специализация была куда жестче."
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "517 л.с., рама и пневмоподвеска — новый эталон внедорожника?",
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
        override fun onToShareClicked() {

        }

        override fun onLikeClicked() {

        }

        override fun onDislikeClicked() {

        }

        override fun onToOtherScreenClicked() {

        }
    })
}