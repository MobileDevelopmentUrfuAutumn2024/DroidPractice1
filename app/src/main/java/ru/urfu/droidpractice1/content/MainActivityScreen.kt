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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    isChecked: Boolean = false,
    likesCount: Int = 0
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                            style = Typography.titleSmall
                        )
                    },
                    actions = {
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { handler.onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "поделиться"
                        )
                    }
                )
            },
        ) { innerPadding ->
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.main_article_header),
                    style = Typography.titleLarge,
                    fontWeight = FontWeight.Black
                )

                AsyncImage(
                    model = "https://photobooth.cdn.sports.ru/preset/message/c/d7/9ff2cd5fc44a98f96829fbdc40001.jpeg?f=webp&q=90&s=2x&w=730",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .padding(top = 10.dp),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.main_article_title),
                    style = Typography.titleMedium,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = stringResource(id = R.string.main_article_text),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Text(
                    text = stringResource(id = R.string.main_article_text_second),
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Card(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = stringResource(id = R.string.main_article_title),
                        color = if (isChecked) Color.Gray else Color.Black
                    )
                }

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { handler.onThumbUpClicked() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "поставить лайк"
                    )

                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { handler.onThumbDownClicked() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = "убрать лайк"
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp),
                        text = likesCount.toString(),
                        color = Color(0xFFFF28ad59),
                        fontSize = 20.sp
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

        override fun onToOtherScreenClicked() {}

        override fun onToShareClicked() {}

        override fun onThumbUpClicked() {}

        override fun onThumbDownClicked() {}

    })
}