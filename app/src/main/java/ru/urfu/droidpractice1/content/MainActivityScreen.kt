@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    onLikePlus: () -> Unit = {},
    onLikeMinus: () -> Unit = {},
    read: Boolean = false,
    countLike: Int = 0
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
                Row {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onLikePlus() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = countLike.toString(),
                        fontSize = 20.sp
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .graphicsLayer { rotationZ = 180f }
                            .clickable { onLikeMinus() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                }

                Text(text = stringResource(id = R.string.title_mainActivity), style = Typography.titleLarge)

                Text(
                    text = stringResource(id = R.string.first_p_mainActivity),
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = stringResource(id = R.string.link_mainActivity_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.second_p_mainActivity)
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "«Ведьмак» от CD Projekt RED: что известно про следующие игры во вселенной Сапковского",
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

    })
}