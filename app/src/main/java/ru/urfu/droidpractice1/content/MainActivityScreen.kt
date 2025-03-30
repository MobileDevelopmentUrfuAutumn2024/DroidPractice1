@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    onClickActivity: () -> Unit = {},
    onToShareClick: () -> Unit = {},
    onClickLike: () -> Unit = {},
    onClickDislike: () -> Unit = {},
    read: Boolean = false,
    countLike: Int = 0,
    countDislike: Int = 0
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
                        IconButton(onClick = onToShareClick) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = stringResource(id = R.string.share_content_description)
                            )
                        }
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    IconButton(onClick = onClickLike) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = stringResource(id = R.string.like)
                        )
                    }
                    Text(
                        text = countLike.toString(),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 10.dp, end = 20.dp)
                    )
                    IconButton(onClick = onClickDislike) {
                        Icon(
                            painter = painterResource(id = R.drawable.deslike),
                            contentDescription = stringResource(id = R.string.like)
                        )
                    }
                    Text(
                        text = countDislike.toString(),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 10.dp, end = 20.dp)
                    )

                }

                Text(
                    text = "\t «Конец и начало»",
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "\t Как многим понятно из названия это представитель спортивных аниме, история крутится вокруг команды Карасуно («карасу» — ворон), но не будем забегать вперёд. Сюжет начинается с низкого парня, учащегося в средней школе и мечтающего стать самым крутым волейболистом, как его кумир «маленький гигант», парня зовут Сё Хината и сколотив команду из своих друзей, которые, к слову, не очень горят желанием играть в волейбол, отправляется на свой первый турнир среди средних школ. Там он встречает игрока по имени Тобия Кагеяму, который является гением в свои годы и является чуть ли не самым сильным связующим.",
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.first)
                        .build(),
                    contentDescription = null

                )

                Text(
                    text = "\t Связующий в волейболе — это пасующий игрок, которого также называют разводящим или плеймейкером. Это самая важная позиция в волейболе и после того, как идёт приём мяча, второе касание делает связующий: именно от волейболиста данного амплуа зависит будущий удар нападающего.",
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "\t Между Хинатой и Кагеямой возникает чувство соперничества, но команда Кагеямы выигрывает матч и Хината вылетает из турнира. В последствии главные герои поступают в одну старшую школу и вступают в волейбольный клуб, где им придётся играть в одной команде, что выливается в весьма интересный, но типичный сюжет для спортивных аниме. Элементы комедии работают хорошо, юмор здесь не превращается в парад абсурда и вплетён в общий сюжет уместно, чтобы зритель не заскучал.",
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Card(
                    modifier = Modifier
                        .clickable { onClickActivity() }
                        .padding(start = 100.dp),

                    )
                {
                    Text(
                        text = "«Грозный союзник»",
                        modifier = Modifier
                            .padding(16.dp),
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