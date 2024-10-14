@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.interfaces.MainActivityInterface
import ru.urfu.droidpractice1.storage.Article
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    mainActivityInterface: MainActivityInterface,
    article: Article
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    Icon(
                        modifier = Modifier.padding(10.dp)
                            .clickable { mainActivityInterface.onShareClick() },
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = null
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Text(
                    text = article.title
                )
                AsyncImage(
                    placeholder = ColorPainter(Color.Gray),
                    // URI на картинку я нагло украл, так как у меня падала ошибка на мои картинки
                    model = article.imageUri,
                    modifier = Modifier.padding(innerPadding)
                        .wrapContentSize(align = Alignment.Center)
                        .fillMaxWidth().height(300.dp),
                    contentDescription = null,
                    error = ColorPainter(Color.Red),
                    )
                Text(
                    text = article.text
                )
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp)
                            .clickable { mainActivityInterface.onLikeClick() },
                        painter = painterResource(R.drawable.ic_thumbup),
                        contentDescription = null
                    )
                    Text(
                        text = article.likes.toString()
                    )
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { mainActivityInterface.onDislikeClick() },
                        painter = painterResource(R.drawable.ic_thumbdown),
                        contentDescription = null
                    )
                    Text(
                        text = article.disLikes.toString()
                    )
                    // тут лайки дизлайки
                }
                Text(
                    text = "Гиперссылка на другую очень крутую статью",
                    modifier = Modifier.padding(vertical = 50.dp)
                )
            }
        }
    }
}