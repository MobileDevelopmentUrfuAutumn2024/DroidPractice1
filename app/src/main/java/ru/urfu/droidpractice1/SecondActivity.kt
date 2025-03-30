package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "onCreate")

        setContent {
            val isRead = remember { mutableStateOf(intent.getBooleanExtra("isRead", false)) }
            MainScreen { onBackPressedDispatcher.onBackPressed() }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(onBackPressed: () -> Unit) {
        var isRead by remember { mutableStateOf(intent.getBooleanExtra("isRead", false)) }
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            TopAppBar(
                title = { Text("Статья", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
            )

            Text(
                text = "Почему коты любят лежать на ноутбуках?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            )

            ArticleImage()

            Text(
                text = "Рассмотрим основные причины:",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            )

            Text(
                text = getPointsText(),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Прочитано", modifier = Modifier.padding(end = 8.dp))
                Switch(
                    checked = isRead,
                    onCheckedChange = {
                        isRead = it
                        val resultIntent = Intent().apply {
                            putExtra("isRead", isRead)
                        }
                        setResult(RESULT_OK, resultIntent)
                    }
                )
            }
        }
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
                painter = painterResource(id = R.drawable.cat2),
                contentDescription = "Изображение кота на ноутбуке",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }

    private fun getPointsText(): String {
        return """
            1. Тепло и комфорт. Коты любят тепло, а ноутбуки излучают его, особенно когда работают на высокой мощности.

            2. Привлечение внимания. Коты — социальные животные, и им нравится, когда хозяин обращает на них внимание.

            3. Ваш запах. Коты часто выбирают предметы, на которых есть запах их хозяев.

            4. Важность личного пространства. Коты стремятся занять все самое важное в вашей жизни.
        """.trimIndent()
    }
}
