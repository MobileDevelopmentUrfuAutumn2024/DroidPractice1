@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(context: Context) {
    val likesCount = rememberSaveable { mutableIntStateOf(0) }
    val dislikesCount = rememberSaveable { mutableIntStateOf(0) }
    val titleText = "Вышел Chrome 105"
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = stringResource(id = R.string.article_title)
                            )
                            IconButton(onClick = {
                                // Создаем Intent для отправки заголовка
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, titleText)
                                    type = "text/plain"
                                }

                                startActivity(
                                    context,
                                    Intent.createChooser(shareIntent, "Share"),
                                    null
                                )
                            }) {
                                Icon(Icons.Default.Share, contentDescription = "Share")
                            }
                        }
                    }
                )
            }) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Заголовок статьи
                    Text(titleText, style = MaterialTheme.typography.titleLarge)

                    Text(
                        "Google выпустила релиз браузера Chrome 105. Также стал доступен стабильный выпуск свободного проекта Chromium.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Изображение статьи
                    val imageUrl =
                        "https://habrastorage.org/getpro/habr/upload_files/57a/95c/d21/57a95cd21b44ac1534fc0f42d48388dc.JPG"  // пример ссылки на изображение

                    AsyncImage(
                        model = imageUrl,
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "Translated description of what the image contains"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Основной текст статьи
                    Text(
                        text = "В Chrome 105 устранили 24 уязвимости. Одной из них (CVE-2022-3038) присвоен критический уровень опасности. Детали по данной уязвимости пока не разглашаются, известно только, что она вызвана обращением к освобождённому блоку памяти (use-after-free) в Network Service. ",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Кнопка для перехода на другую статью
                    Button(onClick = {
                        val intent = Intent(context, SecondActivity::class.java)
                        startActivity(context, intent, null)
                    }) {
                        Text("Читать следующую статью")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Счетчики лайков/дизлайков
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { likesCount.intValue++ }) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Like")
                        }
                        Text(text = "${likesCount.intValue}")

                        IconButton(onClick = { dislikesCount.intValue++ }) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Dislike")
                        }
                        Text(text = "${dislikesCount.intValue}")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(LocalContext.current)
}