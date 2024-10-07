@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle



import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.urfu.droidpractice1.MainActivity
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.viewmodel.LikeDislikeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel




@Composable
fun MainActivityScreen(isRead: Boolean) {
    val context = LocalContext.current

    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = stringResource(id = R.string.article_title)
                            )
                            Text(
                                text = stringResource(id = R.string.first_news_name),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                )
                            )

                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            shareArticle(context)
                        }) {
                            Icon(Icons.Filled.Share, contentDescription = stringResource(id = R.string.share_content_description))
                        }
                    }
                )

            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

                LikeDislikeScreen()

                textForNews(isRead)

            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val isRead =true;
    //MainActivityScreen(isRead,null)

}

@Composable
fun textForNews(isRead : Boolean){

    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {

            Text(
                text = "\n" +
                        "В этом году аниме One Piece исполняется 25 лет. В честь этого события Funko Pop анонсировала выпуск лимитированной коллекции, в которой избранные персонажи One Piece увековечены в бронзовой статуеобразной фигуре. Но не ждите, что получите их все сразу, поскольку в случайные даты выпадет лишь несколько персонажей. Всего можно собрать 10 штук, и Funko выпустила первые два из этого ограниченного набора.",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF333333)
                )
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://storage.yandexcloud.net/otakuy-media/post_media/11853114-15dc-11ef-9684-ae54f4e7377a")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth().clickable {

                        val intent = Intent(context, SecondActivity::class.java)
                        //context.startActivity(intent)
                        (context as? MainActivity)?.startActivityForResult(intent, 1)

                    }
                    ,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isRead) Color.DarkGray else Color.White,
                    contentColor = if (isRead) Color.White else Color.Black
                )

            ) {
                Text(text = "В первом раунде этой бронзовой статуи One Piece выпадают Монки Д. Луффи и Ророноа Зоро из Мугивар. Обе фигуры покрыты блестящим покрытием, напоминающим бронзу, и стоят на вершине камней, на которых выгравированы их имена. ", modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,

                    ))
            }


        }


        }


}


fun shareArticle(context: Context) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.first_news_name))
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}


@Composable
fun LikeDislikeScreen(viewModel: LikeDislikeViewModel = viewModel()) {
    val likes by viewModel.likes.observeAsState(0)
    val dislikes by viewModel.dislikes.observeAsState(0)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Likes: $likes")
        Text(text = "Dislikes: $dislikes")
        Row{
            Button(onClick = { viewModel.like() }) {
            Text("Like")
        }

            Button(onClick = { viewModel.dislike() }) {
                Text("Dislike")
            }}

    }
}



