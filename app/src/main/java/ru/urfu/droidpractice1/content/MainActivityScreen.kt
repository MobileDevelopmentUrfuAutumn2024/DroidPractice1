@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import android.graphics.fonts.FontStyle.FONT_WEIGHT_LIGHT
import androidx.compose.foundation.background
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
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@SuppressLint("InlinedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likesCount: Int = 0,
    onClickActivity: () -> Unit = {},
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
                Text(text = "Обзор мультфильма «Унесенные призраками»",
                    style = Typography.titleLarge,
                    fontFamily = FontFamily.Serif
                    )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.spiritedfir)
                        .build(),
                    contentDescription = null
                )

                Text(
                    text = "Самый знаменитый, отмеченный разнообразнейшими наградами фильм, принёсший режиссёру Хаяо Миядзаки «Оскара»… Трогательная история любви и дружбы, покорившая сердца зрителей всего мира. Мир детства, открывающийся взрослым зрителям, заставляющий почувствовать себя ребёнком…\n" +
                            "\n" +
                            "Честно говоря, никаких призраков в фильме нет. Оригинальное японское название фильма: «Сэн и таинственное исчезновение Тихиро», и только для краткости в американском прокате придумали версию с «Унесёнными призраками». \n",
                    modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.spiritedsec)
                        .build(),
                    contentDescription = null

                )

                Text(
                    text = "Мир, в который так неожиданно попадает Тихиро вместе с родителями – мир синтоистских духов и божеств, наследие Древней Японии. Люди здесь – незваные гости, и, чтобы выручить папу с мамой, вернуть им человеческий облик, и самой вернуться в мир людей, Тихиро приходится работать в купальнях «Абурая», под присмотром ворчливой Рин, подчиняясь злой и жадной колдунье Юбабе. Поначалу ей приходится очень трудно, она теряет даже своё имя, но вскоре находит друзей, постепенно осваиваясь в незнакомом и странном месте. Встреча с Хаку многое меняет – принимая его помощь, Тихиро в ответ дарит ему свою любовь, помогает вернуть забранное Юбабой имя.\n" +
                            "«Унесённые призраками» - фильм, в котором есть всё – и красивейшая графика, и увлекательный сюжет, и замечательные герои, и целое море чувств! Этот фильм – пропуск в давно забытый мир детства! ",
                    modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))
                        .background(color= Color("#cafcca".toColorInt()))
                        .padding(horizontal = 24.dp)
                        .padding(top=26.dp)
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "«Унесённые призраками» - фильм, в котором есть всё – и красивейшая графика, и увлекательный сюжет, и замечательные герои, и целое море чувств! Этот фильм – пропуск в давно забытый мир детства! ",
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { onClickActivity() }
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Обзор мультфильма «Мой сосед Тоторо»",
                        modifier = Modifier.padding(16.dp),
                        fontFamily = FontFamily.Serif,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = if (read) Color.Gray else Color.Black,

                    )
                }

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { handler.onLikeClicked() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = "Like"
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = likesCount.toString(),
                        fontSize = 20.sp
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { handler.onDislikeClicked() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = "Dislike"
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

        override fun onLikeClicked() {
        }

        override fun onDislikeClicked() {
        }

    })
}