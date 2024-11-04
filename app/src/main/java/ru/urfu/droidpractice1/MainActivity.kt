package ru.urfu.droidpractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.ArticleScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidPractice1Theme {
                ArticleScreen()
            }
        }
    }
}