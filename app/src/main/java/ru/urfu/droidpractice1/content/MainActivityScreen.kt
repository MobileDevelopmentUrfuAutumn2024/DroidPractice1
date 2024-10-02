@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.data.Article
import ru.urfu.droidpractice1.data.getFragments
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

/**
 * Главный экран
 */
@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false
) {
    val article = Article.articles[0]
    val texts: List<String> = article.getFragments()
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
                                .clickable { handler.onToShareClick() }
                                .padding(10.dp),
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
                    .padding(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = article.name,
                    fontSize = 20.sp,
                    style = Typography.titleLarge,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = texts[0],
                    fontSize = 16.sp
                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    model = Article.articles[0].path[0],
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = texts[1],
                    fontSize = 16.sp
                )

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            handler.onToArticleClick()
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(20.dp),
                        style = Typography.titleLarge,
                        fontStyle = FontStyle.Italic,
                        text = Article.articles[1].name,
                        fontSize = 20.sp,
                        color = if (read) Color.LightGray else Color.Black
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
        override fun onToArticleClick() {

        }

        override fun onToShareClick() {
        }

    })
}