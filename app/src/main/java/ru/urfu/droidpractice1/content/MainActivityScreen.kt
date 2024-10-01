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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false
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
                Text(text = "Лукас Вера сравнил «Химки» с «Челси»", style = Typography.titleLarge)

                Text(
                    text = "Полузащитник «Химок» Лукас Вера сравнил подмосковную команду с лондонским «Челси».\n" +
                            "\n" +
                            "— Общественность сравнивает «Химки» с «Челси». Количество игроков — единственная схожесть?\n" +
                            "— Есть такое. Сейчас очень много игроков в команде, но самое важное, что мы хорошо работаем. Никаких проблем я не вижу.\n",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://s-cdn.sportbox.ru/images/styles/upload/fp_fotos/1e/6a/74e9bfc4ca26735ebd52f1e8cf8495da66a8b457a4918095248260.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "— В чём «Химки» лучше «Челси»?\n" +
                            "— Это другая лига. У нас другой стиль. Мы делаем на поле то, что просит наш тренер Франк Артига. Но пока очков не так много у нас, — сказал Вера в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.\n" +
                            "\n" +
                            "После 10 туров РПЛ сезона-2024/2025 «Химки» набрали восемь очков и занимают 14-е место в турнирной таблице."
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
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