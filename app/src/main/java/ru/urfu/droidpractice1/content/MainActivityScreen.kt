@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.res.Resources.Theme
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorConfig
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.urfu.droidpractice1.HandlerMainScreen
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import java.time.format.TextStyle
import ru.urfu.droidpractice1.ui.theme.Typography
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.MainActivity

@Composable
fun MainActivityScreen(
    handler: HandlerMainScreen,
    read: Boolean = false,
    countLike: Int = 0,
    likeClick: () -> Unit = {},
    disLikeClick: () -> Unit = {},
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
                            imageVector = Icons.Filled.Share,
                            modifier = Modifier.clickable { handler.onToShareClicked() },
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp)
            ) {
                Text(text = "Традиции Масленицы и рецепты праздничных блинов.", fontSize = 27.sp)
                Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                    IconButton(onClick = {likeClick()}) {
                        Icon(
                            painter = painterResource(R.drawable.like),
                            contentDescription = "Понравилось"
                        )
                    }

                    Text(text = countLike.toString(), modifier = Modifier
                        .padding(horizontal = 15.dp))

                    IconButton(onClick = {disLikeClick()}) {
                        Icon(
                            painter = painterResource(R.drawable.dislike),
                            contentDescription = "Не Понравилось"
                        )
                    }
                }
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.maslenica2),
                    contentDescription = "Масленица"
                )
                Text(text = "Масленица — один из самых известных старинных народных праздников, который продолжают отмечать до сих пор. Торжество длится целую неделю и предваряет Великий пост: в эти дни пекут блины и сжигают чучело, чтобы проводить зиму и встретить весну. «Лента.ру» рассказывает, когда масленичная неделя начнется в 2024 году, а также вспоминает историю и традиции праздника.",
                    modifier =
                    Modifier.padding(vertical = 25.dp))
                Text(text = "Рецепты блинов к Масленице", style = Typography.titleLarge, fontSize = 25.sp, modifier = Modifier.padding(bottom = 10.dp))
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.blins),
                    contentDescription = "Масленица2"
                )
                Text(text = "Ингредиенты для блинов:", modifier = Modifier.padding(top = 10.dp))
                Text(text = "мука — 1 стакан;\n" +
                        "сахарная пудра — 1 столовая ложка;\n" +
                        "яйца — 3 штуки;\n" +
                        "молоко — 300 миллилитров;\n" +
                        "сливочное масло — 100 граммов.",
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp))
                Text(text = "Способ приготовления блинов:")
                Text(text = "1. Муку смешать с сахарной пудрой.\n" +
                        "2. Добавить яйца и молоко. Тесто должно напоминать слегка взбитые сливки по консистенции.\n" +
                        "3. Растопить сливочное масло и добавить его в смесь, а затем перемешать все еще раз.\n" +
                        "4. Оставить тесто на столе на 30 минут.\n" +
                        "5. Разогреть сковороду и смазать маслом.\n" +
                        "6. Тесто выливать небольшими порциями, равномерно распределяя по сковороде.",
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp))
                Card(
                    modifier = Modifier
                        .padding(top=16.dp)
                        .clickable { handler.toSecondActivityClick() }
                ) {
                    Text(text = "Рецепт шоколадных блинчиков от кота Кокоса",
                        modifier = Modifier.padding(16.dp),
                        color = if (read) Color.Gray else Color.Black)
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(handler = object : HandlerMainScreen {
        override fun toSecondActivityClick() {
        }
    })
}*/
