@file:OptIn(ExperimentalMaterial3Api::class)

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
    read: Boolean = false,
    likes: Int = 0
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
                Text(text = "Не \"дрожь Земли\"", style = Typography.titleLarge)

                Row {
                    Icon(
                        modifier = Modifier.clickable { handler.increaseLikes() }.padding(16.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null,
                    )

                    Icon(
                        modifier = Modifier.clickable { handler.decreaseLikes() }.padding(16.dp),
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null,
                    )

                    Text(
                        text = likes.toString()
                    )
                }

                Text(
                    text = "Землетрясение магнитудой 4,4 балла по шкале Рихтера произошло в провинции Семнан. Иранские СМИ сообщили об инциденте. Однако геологическая станция в соседней Армении утверждает, что не зарегистрировала никаких афтершоков, что говорит о мощном взрыве.\n" +
                            "\n" +
                            "Есть сравнения графиков с испытаниями ядерного оружия в Пакистане, России Северной Корее и Индии. Красным цветом на них выделены график сейсмической активности при ядерном взрыве. Синим - обычная \"дрожь Земли\" - с волнами афтершоков.\n",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_67035c074ac7562f9e3b822e_67035cf4303c8129ca349816/scale_2400",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Оманский профессор Хамуд Аль-Нуфли написал в субботу, говоря о землетрясении:\n" +
                            "— «Иран может вскоре объявить, что у него есть ядерное оружие, что вызовет очень большой шок для Запада, Израиля и тех, кто находится под его защитой».\n" +
                            "\n" +
                            "Политологи и ранее говорили о подозрительном затишье во внешней политике Ирана в последнее время. На провокации Израиля страна отвечала крайне скупо. Это могло косвенно свидетельствовать о каком-то готовящемся сюрпризе, под который руководство Ирана выигрывало себе время.\n" +
                            "Тревожная информация из Ирана может объяснить и позицию США, всеми силами старающихся сейчас смягчить конфликт на Ближнем Востоке. Вашингтон, якобы, даже предложил Нетаньяху некую «компенсацию», если Израиль воздержится от атак по объектом энергетики и атомной промышленности в Иране."
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Команда Quantinuum добилась прорыва в квантовых вычислениях",
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

        override fun increaseLikes() {

        }

        override fun decreaseLikes() {

        }

        override fun onToShareClicked() {

        }

    })
}