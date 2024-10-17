@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    isRead: Boolean = false,
    likesCount: Int = 0
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
                                .clickable { handler.onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "Кнопка поделиться"
                        )
                    }
                )
            },
            ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.article_header),
                    style = Typography.titleLarge
                )

                AsyncImage(
                    model = "https://minio.nplus1.ru/app-images/960401/66fcc509b71ea_img_desktop.jpeg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Изображение дерева"
                )


                Text(
                    text = stringResource(id = R.string.article_subtitle),
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.article_main_text),
                    style = Typography.bodyLarge
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(id = R.string.article_additional_info_button_text),
                        color = if (isRead) Color.Gray else Color.Black
                    )
                }

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { handler.onThumbUpClicked() },
                        painter = painterResource(id = R.drawable.thumb_up),
                        contentDescription = "Кнопка поставить лайк"
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { handler.onThumbDownClicked() },
                        painter = painterResource(id = R.drawable.thumb_down),
                        contentDescription = "Кнопка убрать лайк"
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = likesCount.toString(),
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
        override fun onToOtherScreenClicked() {
        }

        override fun onToShareClicked() {
        }

        override fun onThumbUpClicked() {
        }

        override fun onThumbDownClicked() {
        }

    })
}