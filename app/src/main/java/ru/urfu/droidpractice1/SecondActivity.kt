package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import coil.load
import ru.urfu.droidpractice1.content.Article
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.repositiry.createArticle


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var likes = 0

    private val article = createArticle(likes, isViewed)

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
        setArticleData(article)
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

    private fun setArticleData(article: Article) {
        binding.image.load(article.imageUrl)
        binding.title.text = article.title
        binding.text.text = article.text
        binding.relevant.text = article.relevant
        binding.viewed.isVisible = isViewed
    }

    companion object {
        private var isViewed = false
    }
}
