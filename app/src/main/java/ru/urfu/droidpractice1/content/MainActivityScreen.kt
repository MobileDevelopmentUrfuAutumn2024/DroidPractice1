@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme


@Composable
fun MainActivityScreen(
    modifyLikesCount: (modify: Int) -> () -> Unit,
    gotoNextArticle: () -> Unit,
    shareBtn: () -> Unit,
    likesCount: Int = 0,
    secondArticleIsRead: Boolean = false
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.main_article_title)
                        )
                    }
                )
            },
            bottomBar = {
                Button(
                    onClick = gotoNextArticle,
                    content = {Text(text = stringResource(R.string.second_article_title))},
                    colors = ButtonColors(
                        containerColor = if (secondArticleIsRead) Color.Cyan else Color.Gray,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Blue,
                        disabledContainerColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = modifyLikesCount(1))
                            .padding(start = 5.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "Like button"
                    )
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = modifyLikesCount(-1))
                            .padding(start = 5.dp),
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = "Dislike button"
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = likesCount.toString()
                    )
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = shareBtn)
                            .padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Dislike button"
                    )
                }
                Row(modifier = Modifier.padding(top = 25.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.string.main_article_picture_url)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "Main image main activity",
                        contentScale = ContentScale.Fit,
                    )
                }
                Row(modifier = Modifier.padding(top = 150.dp)) {
                    Text(text = stringResource(R.string.first_articale_text))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen({{}}, {}, {})
}