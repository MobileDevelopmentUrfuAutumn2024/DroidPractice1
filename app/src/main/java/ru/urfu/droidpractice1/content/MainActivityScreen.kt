@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
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
    hasRead: Boolean = false,
    likeCount: String = "0",
    dislikeCount: String = "0"
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
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { handler.onShare() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = stringResource(id = R.string.share_icon)
                        )
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(20.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.article_head),
                    fontSize = 20.sp,
                    style = Typography.titleLarge,
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp),
                    text = stringResource(id = R.string.sub_head1),
                    fontSize = 20.sp,
                    style = Typography.titleLarge,
                )

                AsyncImage(
                    model = stringResource(R.string.picture1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Гениальный пароль"
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    text = stringResource(id = R.string.text1),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    style = Typography.bodyLarge,
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp),
                    text = stringResource(id = R.string.sub_head2),
                    fontSize = 20.sp,
                    style = Typography.bodyLarge,
                )

                AsyncImage(
                    model = stringResource(R.string.picture2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Установите это немедленно"
                )

                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    text = stringResource(id = R.string.text2),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify,
                    style = Typography.bodyLarge,
                )

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { handler.onAddLike() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(12.dp),
                        text = likeCount,
                        fontSize = 20.sp,
                        style = Typography.labelSmall,
                    )

                    Icon(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { handler.onAddDislike() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(12.dp),
                        text = dislikeCount.toString(),
                        fontSize = 20.sp,
                        style = Typography.labelSmall
                    )
                }

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onGoingNext() }
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "К следующей статье",
                        color = if (hasRead) Color.Gray else Color.Black
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
        override fun onAddLike() {
            TODO("Not yet implemented")
        }

        override fun onAddDislike() {
            TODO("Not yet implemented")
        }

        override fun onGoingNext() {
            TODO("Not yet implemented")
        }

        override fun onShare() {
            TODO("Not yet implemented")
        }
    })
}