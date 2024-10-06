@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
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
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(bottom=16.dp)
                )

                AsyncImage(
                    model = "https://habrastorage.org/r/w1560/getpro/habr/upload_files/95f/221/f7c/95f221f7c2e9df81d29fcc4fca625db5.png",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Архитектура современных LLM приложений"
                )

                Text(
                    text = stringResource(id = R.string.article_annotation),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.article_section1_subtitle),
                    modifier = Modifier.padding(vertical = 16.dp),
                    style= Typography.titleMedium
                )
                Text(
                    text = stringResource(id = R.string.article_section1_body),
                    modifier = Modifier.padding(vertical = 16.dp),
                    style= Typography.bodyMedium
                )
                AsyncImage(
                    model = "https://habrastorage.org/r/w1560/getpro/habr/upload_files/f24/b5a/55e/f24b5a55efabd7944ed057865ce2b19d.png",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "Архитектура современных LLM приложений"
                )
                Text(text="Выберите подходящую LLM",
                    style= Typography.titleMedium,
                    modifier = Modifier.padding(vertical = 16.dp))
                Text(text="Вы пытаетесь уменьшить издержки, разрабатывая приложение, использующее предобученную модель, но как выбрать правильную? Вот пара вещей, которые надо учесть.",
                    style=Typography.bodyMedium,
                    modifier = Modifier.padding(start=16.dp)
                    )
                Text(text="Ищете LLM с открытым кодом?\n" +
                        "\n" +
                        "Ознакомьтесь с нашим руководством для разработчиков по LLM с открытым кодом, который включает модели вроде OpenLLaMA и модели семейства Falcon\n",
                    modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))
                        .background(color= Color("#bae1ff".toColorInt()))
                        .padding(horizontal = 24.dp)
                        .padding(top=16.dp)

                )

                Text(
                    modifier = Modifier.padding(start = 16.dp, top=10.dp),
                    text = stringResource(id = R.string.article_main_text),
                    style = Typography.bodyLarge,
                )

                Text(text="Производительность модели. Прежде чем дообучать модель используя подходы вроде fine‑tuning или in‑context learning (о них будет чуть ниже), протестируйте, насколько хорошо, быстро и связно модель генерирует тот ответ, который вы от неё ожидаете. Для измерения этих качеств модели можно воспользоваться offline evaluations.",
                    modifier=Modifier.padding(start=16.dp),
                    style= Typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
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