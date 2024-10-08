@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.urfu.droidpractice1.R

@Composable
fun MainActivityScreen(onNavigateToSecondActivity: () -> Unit, isArticleRead: Boolean) {
    val context = LocalContext.current
    val articleTitle = "Почему коты любят лежать на ноутбуках?"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Статья") },
                actions = {
                    IconButton(onClick = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, articleTitle)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Поделиться",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val blockColor = if (isArticleRead) Color.LightGray else colorResource(id = R.color.purple_200)

            LikeDislikeButtons()
            ArticleTitle()
            ArticleIntro()
            ArticleImage()
            ArticleConclusion()
            NavigateButton(onClick = onNavigateToSecondActivity, backgroundColor = blockColor)
        }
    }
}

@Composable
fun LikeDislikeButtons() {
    var likeCount by rememberSaveable { mutableStateOf(0) }
    var dislikeCount by rememberSaveable { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { likeCount++ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "Лайк",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = likeCount.toString(), style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { dislikeCount++ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dislike),
                contentDescription = "Дизлайк",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = dislikeCount.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ArticleTitle() {
    Text(
        text = "Почему коты любят лежать на ноутбуках?",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ArticleIntro() {
    Text(
        text = "Каждый, у кого есть кот, наверняка сталкивался с тем, что любимец стремится улечься прямо на клавиатуру ноутбука. Это может вызывать недоумение, но на самом деле у такого поведения есть несколько объяснений.",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun ArticleImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat1),
            contentDescription = "Изображение кота на ноутбуке",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun ArticleConclusion() {
    Text(
        text = """
            Разберёмся, почему так происходит.
        """.trimIndent(),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun NavigateButton(onClick: () -> Unit, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Почему ваш котик любит лежать на ноутбуке?",
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(onNavigateToSecondActivity = {}, isArticleRead = false)
}
