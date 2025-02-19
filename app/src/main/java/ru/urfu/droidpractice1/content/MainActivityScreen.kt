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
import androidx.compose.material3.Typography
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
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
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
                    text = "«Локомотив» дома обыграл «Крылья Советов» и возглавил таблицу РПЛ",
                    style = Typography.titleLarge
                )

                Text(
                    text = "Завершился матч 11-го тура Российской Премьер-Лиги, в котором встречались московский «Локомотив» и самарские «Крылья Советов». Игра проводилась на стадионе «РЖД Арена (Локомотив)». В качестве главного арбитра встречи выступил Артём Чистяков. Стартовый свисток судьи прозвучал в 16:30 мск. Игра закончилась со счётом 1:0.",
                    modifier = Modifier.padding(top = 16.dp)
                )

                AsyncImage(
                    model = "https://img.championat.com/s/732x488/news/big/j/n/lokomotiv-doma-obygral-krylya-sovetov-i-vozglavil-tablicu-rpl_1728228427524228466.jpg",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "В составе железнодорожников автором единственного гола стал Тимур Сулейманов.\n" +
                            "\n" +
                            "На данный момент «Локомотив» занимает первое место в турнирной таблице Российской Премьер-Лиги. Команда набрала 27 очков за 11 матчей. «Крылья Советов» заработали 11 очков и располагаются на 10-й строчке.",
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onToOtherScreenClicked.invoke() }
                ) {
                    Text(
                        text = "«Локомотив-Кубань» на выезде обыграл «Зенит» в матче Единой лиги",
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