@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.Image
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
import ru.urfu.droidpractice1.Feedback
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onToOtherScreen: () -> Unit = {},
    isRead: Boolean = true,
    onShareClick: () -> Unit = {},
    feedback: Feedback = Feedback.getEmpty(),
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {}
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
                        Image(
                            painter = painterResource(id = R.drawable.share),
                            modifier = Modifier.clickable { onShareClick.invoke() },
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = stringResource(R.string.s1_title), style = Typography.titleLarge)
                FeedbackView(feedback, onLikeClick, onDislikeClick)
                Text(
                    text = stringResource(R.string.s1_1), modifier = Modifier.padding(top = 16.dp)
                )
                AsyncImage(
                    model = "https://i.pinimg.com/736x/49/e1/1b/49e11ba5a4ccf2c7d128d9e2c3751736.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .padding(top = 16.dp)
                )
                Text(
                    text = stringResource(R.string.s1_2),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onToOtherScreen.invoke() }
                ) {
                    Text(
                        text = stringResource(R.string.s1_3),
                        modifier = Modifier.padding(16.dp),
                        color = if (isRead) Color.Gray else Color.Black
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}

@Composable
fun FeedbackView(feedback: Feedback, onLikeClick: () -> Unit, onDislikeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(feedback.likesImage),
            modifier = Modifier.clickable { onLikeClick.invoke() },
            contentDescription = null
        )
        Text(
            text = feedback.likes,
            modifier = Modifier.padding(start = 4.dp, end = 16.dp)
        )
        Image(
            painter = painterResource(feedback.dislikesImage),
            modifier = Modifier.clickable { onDislikeClick.invoke() },
            contentDescription = null
        )
        Text(
            text = feedback.dislikes,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}