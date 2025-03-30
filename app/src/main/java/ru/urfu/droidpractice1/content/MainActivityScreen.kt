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
                            modifier = Modifier.clickable { onToShareClicked.invoke() },
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

                Row{
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

                Text(
                    text = "Тренер «Челси» взял вину за два пропущенных мяча от «Брайтона» на себя",
                    style = Typography.titleLarge
                )

                Text(
                    text = "Главный тренер футбольного клуба «Челси» Энцо Мареска намекнул на то, что игроки не виноваты в пропущенных мячах от «Брайтона», а виноват тренерский штаб.\n" +
                            "\n" +
                            "— Достаточно ли хорошо ваши игроки контролируют происходящее в матчах?\n" +
                            "— «Если вы спросите меня о последней игре против «Брайтона», то, конечно, я больше доволен вторым таймом, чем первым. Но причина, по которой мы пропустили в первом тайме, не в игроках, а в том, что мы ожидали от них двух разных способов игры - двух способов, которыми они играли в начале сезона, но против нас они делали совершенно другие вещи»..\n",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://chelseablues.ru/_nw/1239/64516925.webp",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "— «В перерыве нам пришлось скорректировать манеру прессинга, и во втором тайме мы действовали гораздо лучше. Конечно, нам нравится больше контролировать игру, но нужно анализировать другую команду, которая любит атаковать. Да, нам нравится контролировать происходящее на поле, как во втором тайме матча с «Брайтоном» и как в игре с «Вест Хэмом», которая была очень хороша».\n",
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onToOtherScreenClicked.invoke() }
                ) {
                    Text(
                        text = "Игрок «Челси» Палмер прокомментировал свой покер против «Брайтона»",
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
