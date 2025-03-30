@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content


import androidx.compose.material3.Icon
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
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likes: Int = 0,
    dislikes: Int = 0
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
                Text(text = "Какой-то подпивас сравнил Dwarf Fortress с Excel", style = Typography.titleLarge)
                Row {
                    Icon(
                        modifier = Modifier
                            .clickable { handler.plusLikes() }
                            .padding(16.dp),
                        painter = painterResource(id = R.drawable.thumb_up),
                        contentDescription = null,
                    )
                    Text(text = likes.toString())
                    Icon(
                        modifier = Modifier
                            .clickable { handler.plusDislikes() }
                            .padding(16.dp),
                        painter = painterResource(id = R.drawable.thumb_down),
                        contentDescription = null,
                    )
                    Text(text = dislikes.toString())
                }
                Text(
                    text = "Обезумевший психопат из Стерлипарижа сравнил Dwarf Fortress с Excel.\n" +
                            "\n" +
                            "— Старожилы-геймеры сравнивают Dwarf Fortress с Excel. Таблицы и списки — единственная схожесть?\n" +
                            "— Ой, ну там таблицы и списки с именами и материалами. Ням-ням-ням! ЗУБЫ КОРЕГА!!!\n",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://i.ytimg.com/vi/3vHd4mFhOwU/maxresdefault.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "— В чём  Dwarf Fortress лучше Excel?\n" +
                            "— Это израильская тема. У нас другой Urist. Мы делаем в наших табличках то, что просит наш бог крови Armok. Я знал что у вас, долбаных эльфов, нет чести! Нет достоинства! Нет пива! , — сказал Expedition leader в беседе с elven merchant.\n" +
                            "\n" +
                            "После 10 Seige Goblin Dark Pits набрали восемь Dwarven children и занимают War в World Map."
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Human merchant Ebka объяснил, чего не хватило для ecstatic в Trade с Dwarven local government",
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

        override fun plusLikes() {

        }

        override fun plusDislikes() {

        }
    })
}