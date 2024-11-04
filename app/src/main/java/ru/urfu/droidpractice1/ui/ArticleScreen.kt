package ru.urfu.droidpractice1.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.content.Article
import ru.urfu.droidpractice1.repositiry.createArticleScreen
import ru.urfu.droidpractice1.theme.DroidPractice1Theme


@Composable
fun ArticleScreen() {

    var likesCount by remember { mutableIntStateOf(0) }
    var viewed by remember { mutableStateOf(false) }

    val article = createArticleScreen(likesCount,viewed)
    val context = LocalContext.current
    DroidPractice1Theme {
        Article(
            article = article,
            onShareClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, article.text)
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(intent, article.title))
            },
            onLikeClick = {
                likesCount++
            },
            onDislikeClick = {
                likesCount--
            },
            onRelevantClick = {
                context.startActivity(Intent(context, SecondActivity::class.java))
                viewed = true
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Article(
    article: Article,
    onShareClick: () -> Unit,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit,
    onRelevantClick: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.article)) },
            actions = {
                IconButton(onClick = onShareClick) {
                    Icon(Icons.Default.Share, null)
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge
            )
            if (article.isViewed) {
                Text(
                    text = stringResource(R.string.viewed),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onLikeClick) {
                    Icon(Icons.Default.ThumbUp, null)
                }
                Text(
                    text = article.likes.toString()
                )
                IconButton(onClick = onDislikeClick) {
                    Icon(Icons.Default.ThumbDown, null)
                }
            }
            Text(
                text = article.text,
            )
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop,
            )
            RelevantCard(
                title = article.relevant,
                onClick = onRelevantClick
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun ArticleScreenPreview() {
    ArticleScreen()
}

@Composable
private fun RelevantCard(
    title: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.relevant),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(text = title)
        }
    }
}