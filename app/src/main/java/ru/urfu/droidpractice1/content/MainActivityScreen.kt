@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onOtherArticleCardClicked: () -> Unit = {},
    onShareClicked: () -> Unit = {},
    onLikeClicked: () -> Unit = {},
    onDislikeClicked: () -> Unit = {},
    isSecondArticleRead: Boolean = false,
    likesCount: Int = 0,
    dislikesCount: Int = 0
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
                            modifier = Modifier.clickable { onShareClicked.invoke() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "Кнопка поделиться"
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    Text(stringResource(R.string.main_title))
                    RatingThumbs(
                        likesCount,
                        dislikesCount,
                        onLikeClicked,
                        onDislikeClicked
                    )
                }
                Text(
                    stringResource(R.string.main_part1),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                AsyncImage(
                    model = "https://images.stopgame.ru/uploads/users/2024/526708/00393.hmp35GL.png",
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = 32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
                )
                Text(
                    stringResource(R.string.main_part2),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                ToNextArticleButton(
                    stringResource(R.string.second_title),
                    modifier = Modifier.padding(bottom = 32.dp),
                    onCardClicked = onOtherArticleCardClicked,
                    isActive = !isSecondArticleRead
                )
            }
        }
    }
}

@Composable
fun RatingThumbs(
    likesCount: Int,
    dislikesCount: Int,
    onLikeClicked: () -> Unit,
    onDislikeClicked: () -> Unit
) {
    Row () {
        RateButton(
            dislikesCount.toString(),
            onDislikeClicked,
            "Кнопка не нравится",
            painterResource(id = R.drawable.img)
        )
        RateButton(
            likesCount.toString(),
            onLikeClicked,
            "Кнопка нравится",
            painterResource(id = R.drawable.img_1)
        )
    }
}
@Composable
fun RateButton(
    fingerValue: String,
    onClicked: () -> Unit,
    contentDescription: String,
    painter: Painter
) {
    Column {
        Icon(
            modifier = Modifier.clickable { onClicked.invoke() },
            painter = painter,
            contentDescription = contentDescription
        )
        if (fingerValue.isNotEmpty()) {
            Text(
                fingerValue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun ToNextArticleButton(
    cardText: String, modifier: Modifier = Modifier, onCardClicked: () -> Unit,
    isActive: Boolean = false
) {
    val ifActiveColor = if (isActive) Color.LightGray else Color.Gray
    val ifInactiveColor = if (isActive) Color.Gray else Color.DarkGray
    Column(modifier = modifier) {
        Text(
            stringResource(R.string.card_article_text),
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = ifInactiveColor,
                contentColor = ifActiveColor
            ),
            border = BorderStroke(1.dp, ifActiveColor),
            modifier = Modifier.clickable { onCardClicked.invoke() }
        ) {
            Text(
                text = cardText,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}