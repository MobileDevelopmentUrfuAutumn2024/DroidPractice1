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
import androidx.compose.ui.Alignment
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
import ru.urfu.droidpractice1.ui.theme.DarkGreen
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.LightGreen
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
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { onShareClicked.invoke() },
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
                val articleTextPadding = Modifier.padding(bottom = 32.dp)

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = articleTextPadding
                ) {
                    Text(
                        stringResource(R.string.article1_title),
                        style = Typography.titleLarge
                    )

                    RatingThumbs(
                        likesCount,
                        dislikesCount,
                        onLikeClicked,
                        onDislikeClicked
                    )
                }

                Text(
                    stringResource(R.string.article1_part1),
                    style = Typography.bodyLarge,
                    modifier = articleTextPadding
                )

                AsyncImage(
                    model = "https://0d314c86-f76b-45cc-874e-45816116a667.selcdn.net/4846ed83-2924-48d5-8814-5c1938a00ac3.jpg",
                    contentDescription = null,
                    modifier = articleTextPadding
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
                )

                Text(
                    stringResource(R.string.article1_part2),
                    style = Typography.bodyLarge,
                    modifier = articleTextPadding
                )

                NextArticleCard(
                    stringResource(R.string.article2_title),
                    modifier = articleTextPadding,
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
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Оценка:",
            style = Typography.titleMedium,
        )
        Thumb(
            if (likesCount > 0) likesCount.toString() else "",
            onLikeClicked,
            "Кнопка нравиться",
            painterResource(id = R.drawable.thumb_up_24)
        )
        Thumb(
            if (dislikesCount > 0) dislikesCount.toString() else "",
            onDislikeClicked,
            "Кнопка не нравиться",
            painterResource(id = R.drawable.thumb_down_24)
        )
    }
}

@Composable
fun Thumb(
    fingerValue: String,
    onClicked: () -> Unit,
    contentDescription: String,
    painter: Painter
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .clickable { onClicked.invoke() },
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
fun NextArticleCard(
    cardText: String, modifier: Modifier = Modifier, onCardClicked: () -> Unit,
    isActive: Boolean = false
) {
    val primaryCardColor = if (isActive) DarkGreen else Color.Gray
    val secondaryCardColor = if (isActive) LightGreen else Color.LightGray

    Column(modifier = modifier) {
        Text(
            stringResource(R.string.card_article_text),
            style = Typography.titleLarge,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = secondaryCardColor,
                contentColor = primaryCardColor
            ),
            border = BorderStroke(1.dp, primaryCardColor),
            modifier = Modifier.clickable { onCardClicked.invoke() }
        ) {
            Text(
                text = cardText,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = Typography.titleMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}