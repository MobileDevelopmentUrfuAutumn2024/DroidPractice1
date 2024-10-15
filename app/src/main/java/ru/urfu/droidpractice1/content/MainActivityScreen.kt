@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.interfaces.MainActivityInterface
import ru.urfu.droidpractice1.storage.DataStorage
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme

@Composable
fun MainActivityScreen(
    mainActivityInterface: MainActivityInterface,
    dataStorage: DataStorage
) {

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
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
                            text = stringResource(id = R.string.article_title),
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp)
                    .padding(innerPadding)
            ) {
                Text(
                    text = dataStorage.articleName,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { mainActivityInterface.onLikeClick() },
                        painter = painterResource(R.drawable.ic_thumbup),
                        contentDescription = null
                    )
                    Text(
                        text = dataStorage.likesCounter.toString()
                    )
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { mainActivityInterface.onDislikeClick() },
                        painter = painterResource(R.drawable.ic_thumbdown),
                        contentDescription = null
                    )
                    Text(
                        text = dataStorage.dislikesCounter.toString()
                    )
                }
                AsyncImage(
                    placeholder = ColorPainter(Color.Gray),
                    model = dataStorage.img,
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = null,
                    error = ColorPainter(Color.Red),
                    )
                Text(
                    text = dataStorage.content
                )
                TextButton(
                    onClick = {
                        mainActivityInterface.onLinkClick()
                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFebdada))
                ) {
                    Text(
                        text = "Lebron James",
                        color = Color.Black,
                    )
                }
            }
        }
    }
}