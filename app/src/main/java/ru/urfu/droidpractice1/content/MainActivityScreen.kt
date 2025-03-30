@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

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
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likeCount: Int = 0,
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
                            modifier = Modifier.clickable { handler.shareOnClick() },
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
                Row(){
                    Icon(
                        modifier = Modifier
                            .clickable { handler.clickOnLike() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )
                    Icon(
                        modifier = Modifier
                            .clickable { handler.clickOnDislike() }
                            .padding(8.dp),
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = likeCount.toString()
                    )
                }
                Text(text = stringResource(R.string.first_article_title), style = Typography.titleLarge)

                Text(
                    text = stringResource(R.string.first_article_preface),
                    modifier = Modifier
                        .padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://avatars.dzeninfra.ru/get-zen_doc/1861837/pub_64e08845dafbcd6f485268bd_64e0887c7a2b0a52f36727f5/scale_1200",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = stringResource(R.string.first_article_text)
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.toOtherScreenOnClick() }
                ) {
                    Text(
                        text = "Ещё 5 дополнительных фактов о пушистых друзьях!»",
                        modifier = Modifier
                            .padding(16.dp),
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
        override fun shareOnClick() {

        }

        override fun toOtherScreenOnClick() {

        }

        override fun clickOnLike() {

        }

        override fun clickOnDislike() {

        }
    })
}