package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticleScreen()
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ArticleScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("like_dislike_prefs", Context.MODE_PRIVATE)

    var isSecondArticleRead by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(loadPreference(sharedPreferences, "likes")) }
    var dislikeCount by remember { mutableStateOf(loadPreference(sharedPreferences, "dislikes")) }

    val secondActivityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        val isRead = result.data?.getBooleanExtra("article_read_status", false) ?: false
        isSecondArticleRead = isRead
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArticleTitle()
            ArticleImage()
            ArticleText(stringResource(id = R.string.article_content))
            ReactionSection(likeCount, dislikeCount, sharedPreferences, onLikeClick = {
                likeCount += 1
                savePreference(sharedPreferences, "likes", likeCount)
            }, onDislikeClick = {
                dislikeCount += 1
                savePreference(sharedPreferences, "dislikes", dislikeCount)
            })
            ShareButton(context, stringResource(id = R.string.article_content))

            Button(
                onClick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    secondActivityLauncher.launch(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSecondArticleRead) Color.Gray else Color.Blue  // Меняем цвет кнопки на серый, если статья прочитана
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Перейти ко второй статье")
            }
        }
    }
}

@Composable
fun ArticleTitle() {
    Text(
        text = stringResource(id = R.string.article_title),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun ArticleImage() {
    Image(
        painter = painterResource(id = R.drawable.image),
        contentDescription = "Изображение статьи",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun ArticleText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Color.DarkGray,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun ReactionSection(
    likeCount: Int,
    dislikeCount: Int,
    sharedPreferences: SharedPreferences,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ReactionButton(
            count = likeCount,
            iconResource = R.drawable.heart,
            color = Color.Green,
            onClick = { onLikeClick() },  // Передаем callback для лайка
            text = "Likes: $likeCount"
        )
        ReactionButton(
            count = dislikeCount,
            iconResource = R.drawable.brokenheart,
            color = Color.Red,
            onClick = { onDislikeClick() },  // Передаем callback для дизлайка
            text = "Dislikes: $dislikeCount"
        )
    }
}

@Composable
fun ReactionButton(count: Int, iconResource: Int, color: Color, onClick: () -> Unit, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun ShareButton(context: Context, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { shareText(context, text) },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.share),
                contentDescription = "Поделиться статьей",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

fun savePreference(sharedPreferences: SharedPreferences, key: String, value: Int) {
    sharedPreferences.edit().putInt(key, value).apply()
}

fun loadPreference(sharedPreferences: SharedPreferences, key: String): Int {
    return sharedPreferences.getInt(key, 0)
}

fun shareText(context: android.content.Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    val chooser = Intent.createChooser(intent, "Поделиться текстом")
    context.startActivity(chooser)
}

