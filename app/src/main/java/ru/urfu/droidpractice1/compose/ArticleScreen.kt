package ru.urfu.droidpractice1.compose

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.common.ArticleViewModel
import ru.urfu.droidpractice1.entity.Article
import ru.urfu.droidpractice1.entity.share
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.view.SecondActivity

@Composable
fun ArticleScreen(
    id: String,
    onBackClick: () -> Unit,
    viewModel: ArticleViewModel = viewModel(factory = ArticleViewModel.Factory(id)),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    when (val uiState = uiState) {
        ArticleViewModel.State.Loading -> Loading()
        is ArticleViewModel.State.Content -> Article(
            article = uiState.article,
            onBackClick = {
                viewModel.view()
                onBackClick()
            },
            onLikeClick = viewModel::like,
            onDislikeClick = viewModel::dislike,
            onRelevantClick = {
                viewModel.view()
                context.startActivity(Intent(context, SecondActivity::class.java))
            }
        )
    }
}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(R.string.loading))
    }
}

@Composable
private fun Article(
    article: Article,
    onBackClick: () -> Unit = {},
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {},
    onRelevantClick: () -> Unit = {},
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            ArticleTopAppBar(
                onBackClick = onBackClick,
                onShareClick = { article.share(context) },
            )
        },
    ) { paddingValues ->
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
                style = MaterialTheme.typography.titleLarge,
            )
            if (article.isViewed) {
                Text(
                    text = stringResource(R.string.viewed),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            ArticleLikeDislikeRow(
                likes = article.likes,
                dislikes = article.dislikes,
                onLikeClick = onLikeClick,
                onDislikeClick = onDislikeClick,
            )
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16.0f / 9.0f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop,
            )
            Text(text = article.text)
            RelevantCard(
                title = article.relevant,
                onClick = onRelevantClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleTopAppBar(
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.article)) },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(
                onClick = onShareClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
private fun ArticleLikeDislikeRow(
    likes: Int,
    dislikes: Int,
    onLikeClick: () -> Unit = {},
    onDislikeClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        var likePressed by remember { mutableStateOf(false) }
        var dislikePressed by remember { mutableStateOf(false) }
        FilledTonalButton(
            onClick = {
                likePressed = true
                onLikeClick()
            },
        ) {
            Icon(
                imageVector = if (likePressed) {
                    Icons.Filled.ThumbUp
                } else {
                    Icons.Outlined.ThumbUp
                },
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$likes")
        }
        FilledTonalButton(
            onClick = {
                dislikePressed = true
                onDislikeClick()
            },
        ) {
            Icon(
                imageVector = if (dislikePressed) {
                    Icons.Filled.ThumbDown
                } else {
                    Icons.Outlined.ThumbDown
                },
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "$dislikes")
        }
    }
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

@Composable
@Preview(showSystemUi = true)
fun ArticleScreenPreview(
    id: String = "1",
) {
    DroidPractice1Theme {
        Article(
            article = Article(
                id = "1",
                imageUrl = "https://s-cdn.sportbox.ru/images/styles/upload/fp_fotos/1e/6a/74e9bfc4ca26735ebd52f1e8cf8495da66a8b457a4918095248260.jpg",
                title = "Лукас Вера сравнил «Химки» с «Челси»",
                text = """
                        Полузащитник «Химок» Лукас Вера сравнил подмосковную команду с лондонским «Челси».

                        — Общественность сравнивает «Химки» с «Челси». Количество игроков — единственная схожесть?
                        — Есть такое. Сейчас очень много игроков в команде, но самое важное, что мы хорошо работаем. Никаких проблем я не вижу.
                        — В чём «Химки» лучше «Челси»?
                        — Это другая лига. У нас другой стиль. Мы делаем на поле то, что просит наш тренер Франк Артига. Но пока очков не так много у нас, — сказал Вера в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.

                        После 10 туров РПЛ сезона-2024/2025 «Химки» набрали восемь очков и занимают 14-е место в турнирной таблице.
                    """.trimIndent(),
                relevant = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
                likes = 68,
                dislikes = 419,
                isViewed = true,
            )
        )
    }
}