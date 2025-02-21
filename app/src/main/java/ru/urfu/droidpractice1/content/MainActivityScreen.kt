@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenController
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@SuppressLint("ResourceAsColor")
@Composable
fun MainActivityScreen(
    controller: MainScreenController,
    likesCount: Int = 0,
    hasRead: Boolean = false
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
                                .clickable { controller.onShareClick() },
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Поделиться содержимым"
                        )
                    }
                )
            }, bottomBar = {
                BottomAppBar (
                    content = {
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { controller.onLikeClick() },
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "Отметка «Нравится»"
                        )
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { controller.onDislikeClick() },
                            painter = painterResource(id = R.drawable.ic_dislike),
                            contentDescription = "Отметка «Не нравится»"
                        )
                        Text(
                            text = "$likesCount «Нравится»",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                )
            }) { innerPadding ->
                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.article_paragraph_1),
                        style = Typography.bodyLarge,
                        textAlign = TextAlign.Justify
                    )
                    AsyncImage(
                        model = "https://overclockers.ru/st/r/800/-/legacy/blog/422417/570435_O.png",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .padding(top = dimensionResource(R.dimen.article_gap)),
                        contentDescription = "Изображение корабля Starship в руках «Мехазиллы»"
                    )
                    Text(
                        text = stringResource(id = R.string.article_paragraph_2),
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.article_gap)),
                        style = Typography.bodyLarge,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = stringResource(id = R.string.article_paragraph_3),
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.article_gap)),
                        style = Typography.bodyLarge
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.article_gap))
                            .clickable { controller.onOtherScreenClick() }
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(id = R.string.link_button_text),
                            color = if (hasRead) colorResource(id = R.color.purple_700) else colorResource(id = R.color.teal_200),
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(
        controller = object : MainScreenController {
            override fun onShareClick() {}
            override fun onLikeClick() {}
            override fun onOtherScreenClick() {}
            override fun onDislikeClick() {}
        }
    )
}