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
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onToOtherScreenClicked: () -> Unit = {},
    onToShareClicked: () -> Unit = {},
    onLike: () -> Unit = {},
    onDislike: () -> Unit = {},
    read: Boolean = false,
    likeCount: Int = 0
) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        Icon(
                            modifier = Modifier.clickable { onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                    }
                )
            }
        ) { innerPadding ->

            // Обернем Column в verticalScroll, чтобы включить прокрутку
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Добавляем прокрутку
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                // Row для кнопок лайка и дизлайка
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onLike() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onDislike() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = likeCount.toString(),
                        fontSize = 20.sp
                    )
                }

                // Заголовок статьи
                Text(
                    text = "На фоне серьёзного внутреннего конфликта Уругвай разочаровал в Лиме.",
                    style = Typography.titleLarge
                )

                // Основной текст статьи
                Text(
                    text = """
                        Уругвайская команда столкнулась с трудностями в матче против Перу, что стало следствием внутренних разногласий в команде. 
                        Несмотря на старания, они не смогли показать свою привычную игру и потерпели поражение.

                        Основные моменты матча:
                        - Перу проявил уверенность на поле и смог воспользоваться неорганизованностью соперника.
                        - Уругвай не смог наладить взаимодействие между линиями, что привело к ошибкам в обороне.
                        - Конфликт в команде оказал значительное влияние на моральный дух игроков.

                        Теперь уругвайская сборная должна сосредоточиться на решении внутренних проблем, чтобы избежать таких ситуаций в будущем.
                    """.trimIndent(),
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Здесь можно вставить изображение статьи (при наличии)
                AsyncImage(
                    model = "https://example.com/image.jpg", // Замените на реальный URL
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                // Карточка с дополнительной информацией
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Уругвай должен решить свои внутренние проблемы",
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
    MainActivityScreen()
}
