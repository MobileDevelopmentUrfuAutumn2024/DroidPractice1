@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    clickLike: () -> Unit = {},
    clickDislike: () -> Unit = {},
    countLike: Int = 0,
    countDislike: Int = 0,
    isRead: Boolean = false,
    clickNewActivity: () -> Unit = {},
    shareArticle: () -> Unit = {}
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
                        IconButton(onClick = shareArticle) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Поделиться"
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.article_h1),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        style = TextStyle(
                            textIndent = TextIndent(firstLine = 16.sp)
                        )
                    )

                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        model = stringResource(id = R.string.article_image),
                        contentDescription = null
                    )

                    ArticleText(text = stringResource(R.string.article_source_part1))
                    ArticleText(text = stringResource(R.string.article_source_part2))
                    ArticleText(text = stringResource(R.string.article_source_part3))
                    ArticleText(text = stringResource(R.string.article_source_part4))

                    Row(
                    ) {
                        IconButton(onClick = { clickLike() }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = "Like"
                            )
                        }

                        Text(
                            text = countLike.toString(),
                            modifier = Modifier.padding(top = 10.dp)
                        )

                        IconButton(onClick = { clickDislike() }) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = "Dislike",
                                modifier = Modifier.graphicsLayer { rotationZ = 180f }
                            )
                        }

                        Text(
                            text = countDislike.toString(),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .clickable { clickNewActivity() }
                            .padding(start = 80.dp)
                    ) {
                        Text(
                            text = "Роберт Левандовский",
                            modifier = Modifier.padding(20.dp),
                            color = if (isRead) Color.Gray else Color.Blue
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ArticleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            textIndent = TextIndent(firstLine = 16.sp),
            fontSize = 18.sp,
            lineHeight = 24.sp
        ),
        modifier = modifier.padding(bottom = 16.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}