@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.interfaces.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    isRead: Boolean = false,
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
                            modifier = Modifier.clickable { handler.onToShare() }.padding(
                                dimensionResource(R.dimen.padding)
                            ),
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
                    .padding(dimensionResource(R.dimen.padding))
            ) {
                Text(
                    text = stringResource(R.string.main_activity_header),
                    style = Typography.titleLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding))
                            .clickable { handler.onLike() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.padding)),
                        text = likes.toString(),
                        fontSize = Typography.titleLarge.fontSize
                    )

                    Icon(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding))
                            .clickable { handler.onDislike() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(R.dimen.padding)),
                        text = dislikes.toString(),
                        fontSize = Typography.titleLarge.fontSize
                    )
                }

                Text(
                    text = stringResource(R.string.main_activity_text1),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding))
                )

                Text(
                    text = stringResource(R.string.main_activity_text2),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding))
                )

                AsyncImage(
                    model = stringResource(R.string.main_activity_link_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.padding))
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                )

                Card(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.padding))
                        .clickable { handler.onToOtherScreen() },
                ) {
                    Text(
                        text = stringResource(R.string.second_activity_header),
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding)),
                        color = if (isRead) Color.Gray else Color.Black,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding)),
                    text = stringResource(R.string.main_activity_text3)
                )

                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding)),
                    text = stringResource(R.string.main_activity_text4)
                )

                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding)),
                    text = stringResource(R.string.main_activity_text5)
                )


            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(handler = object : MainScreenHandler {
        override fun onToOtherScreen() {

        }

        override fun onToShare() {

        }

        override fun onLike() {

        }

        override fun onDislike() {

        }

    })
}