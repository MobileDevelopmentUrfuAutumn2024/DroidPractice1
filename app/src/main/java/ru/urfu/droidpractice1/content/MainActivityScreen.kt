@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import android.app.Activity
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.SecondActivity
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme


@Composable
fun MainActivityScreen() {

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    }
    var isSecondArticleRead by remember { mutableStateOf(sharedPreferences.getBoolean("isSecondArticleRead", false)) }


    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra("isSecondArticleRead", false) ?: false
        }
    }
    val scrollState = rememberScrollState()


    DroidPractice1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(
                text = "Мечта всех фанатов. Возродили культовую модель «Субару», которая блистала в ралли",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C3E50)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Опубликовано: 24 июня 2022",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Снова с нами легендарный автомобиль, на котором гонял Колин Макрей.",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(R.string.first_article_t1),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.first_article_t2),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            AsyncImage(
                model = "https://img.championat.com/s/732x488/news/big/l/s/kak-getzhi-stal-samym-yarkim-bojcom-lyogkogo-vesa_17410047411063813390.jpg",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.first_article_t3),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.first_article_t4),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "Интересная статья")
                        putExtra(Intent.EXTRA_TEXT, "Прочитай эту статью про ИИ и мобильную разработку!")
                    }
                    context.startActivity(Intent.createChooser(intent, "Поделиться через"))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Поделиться статьёй")
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    resultLauncher.launch(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (isSecondArticleRead) Color(0xFF27AE60) else Color(0xFFE74C3C)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Перейти на вторую статью")
            }

        }

    }
}




@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}