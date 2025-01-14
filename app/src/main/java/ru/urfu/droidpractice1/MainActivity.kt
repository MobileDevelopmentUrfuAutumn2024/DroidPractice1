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
        title = "Spider-man",
        text = "Челове́к-пау́к (англ. Spider-Man), настоящее имя Пи́тер Бе́нджамин Па́ркер (англ. Peter Benjamin Parker) — супергерой, появляющийся в комиксах издательства Marvel Comics, созданный Стэном Ли и Стивом Дитко. С момента своего первого появления на страницах комикса Amazing Fantasy № 15 (рус. Удивительная фантазия, август 1962) он стал одним из самых популярных супергероев. Ли и Дитко задумывали персонажа как подростка-сироту, воспитанного дядей и тётей, совмещающего жизнь обычного студента и борца с преступностью. Человек-паук получил суперсилу, увеличенную ловкость, «паучье чутьё», а также способность держаться на отвесных поверхностях и выпускать паутину из рук с использованием прибора собственного изобретения.",
        imageUri = "https://upload.wikimedia.org/wikipedia/ru/8/81/Spider-Man_Marvel.jpg"
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
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }
}
