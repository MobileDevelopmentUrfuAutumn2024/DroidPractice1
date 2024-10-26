package ru.urfu.droidpractice1.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArticleScreen(
                id = "1",
                onBackClick = { onBackPressedDispatcher.onBackPressed() }
            )
        }
    }
}
