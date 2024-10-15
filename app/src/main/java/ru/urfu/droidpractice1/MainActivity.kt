package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.interfaces.MainActivityInterface
import ru.urfu.droidpractice1.storage.DataStorage

class MainActivity : ComponentActivity() {
    var dataStorage = DataStorage(
        articleName = "Steph Curry",
        content = "Стефен Карри — американский баскетболист, с 2009 года выступающий за команду НБА «Голден Стэйт Уорриорз». Играет на позиции разыгрывающего защитника. Стефен – сын бывшего игрока НБА Делла Карри и старший брат баскетболиста Сета Карри.",
        img = "https://avatars.mds.yandex.net/i?id=430ea226d65c23bfcff5b770bbf5dc74_l-5869427-images-thumbs&n=13"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(MainActivityLogic(this), dataStorage)
        }
    }
    inner class MainActivityLogic(private val context: MainActivity) : MainActivityInterface
    {
        override fun onLikeClick() {
            dataStorage.likesCounter++
        }

        override fun onDislikeClick() {
            dataStorage.dislikesCounter++
        }

        override fun onFirstViewed() { }

        override fun onShareClick() {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, dataStorage.content)
                startActivity(Intent.createChooser(this, "Поделиться"))
            }
        }

        override fun onLinkClick() {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }
}