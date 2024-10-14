package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.interfaces.MainActivityInterface
import ru.urfu.droidpractice1.storage.Article

class MainActivity : ComponentActivity() {
    var article = Article(
        title = "Очень крутая статья",
        text = "Очень крутой многострочный текст. Очень крутой многострочный текстОчень крутой многострочный текст. Очень крутой многострочный текстОчень крутой многострочный текстОчень крутой многострочный текст",
        imageUri = "https://cdn.bulldogjob.com/system/readables/covers/000/002/329/max_res/How_%28not%29_to_break_your_app_with_hasCode%28%29_and_equals%28%29.png"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(MainActivityLogic(this), article)
        }
    }
    inner class MainActivityLogic(private val context: MainActivity) : MainActivityInterface
    {
        override fun onLikeClick() {
            article.likes++
        }

        override fun onDislikeClick() {
            article.disLikes++
        }

        override fun onFirstViewed() {
            article.isViewed = true
        }

        override fun onShareClick() {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, article.text)
                startActivity(Intent.createChooser(this, "Поделиться"))
            }
        }

        override fun onLinkClick() {
            // не успел
        }
    }
}