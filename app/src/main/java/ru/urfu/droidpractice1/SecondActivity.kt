package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.model.Article

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var likes = 0

    private val article = Article(
        id = "2",
        imageUrl = "https://img.championat.com/s/1350x900/news/big/u/i/igrok-krylev-kostanca-obyasn.jpg",
        title = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
        text = """
                        Бразильский футболист «Крыльев Советов» Фернандо Костанца прокомментировал ничейный результат в матче 10-го тура Российской Премьер-Лиги с «Химками». Встреча прошла сегодня в Самаре. Она закончилась со счётом 0:0.
                        
                        Мы не забили. Вот, что случилось сегодня. Думаю, мы провели хороший матч. Старались что-то создать, контролировали мяч. Возможно, последний пас и удары были не самыми лучшими», — сказал Костанца в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.
                    """.trimIndent(),
        relevant = "Лукас Вера сравнил «Химки» с «Челси»",
        likes = likes,
        isViewed = isViewed,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.like.setOnClickListener {
            binding.likeCount.text = (++likes).toString()
        }
        binding.dislike.setOnClickListener {
            binding.likeCount.text = (--likes).toString()
        }
        binding.relevantCard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.image.load(article.imageUrl)
        binding.title.text = article.title
        binding.text.text = article.text
        binding.relevant.text = article.relevant
        binding.viewed.isVisible = isViewed
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_share -> {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, article.text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, article.title))
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        isViewed = true
        binding.viewed.isVisible = isViewed
    }

    companion object {
        private var isViewed = false
    }
}
