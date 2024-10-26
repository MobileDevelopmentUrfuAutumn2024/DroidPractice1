package ru.urfu.droidpractice1.view

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import kotlinx.coroutines.launch
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.common.ArticleViewModel
import ru.urfu.droidpractice1.compose.MainActivity
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.entity.Article
import ru.urfu.droidpractice1.entity.share

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel by viewModels<ArticleViewModel> { ArticleViewModel.Factory(id = "2") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                viewModel.view()
                onBackPressedDispatcher.onBackPressed()
            }
            like.setOnClickListener { viewModel.like() }
            dislike.setOnClickListener { viewModel.dislike() }
            relevantCard.setOnClickListener {
                viewModel.view()
                startActivity(Intent(this@SecondActivity, MainActivity::class.java))
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is ArticleViewModel.State.Content -> updateLayout(it.article)
                        ArticleViewModel.State.Loading -> Unit
                    }
                }
            }
        }
    }

    private fun updateLayout(article: Article) = with(binding) {
        title.text = article.title
        viewed.isVisible = article.isViewed
        like.text = article.likes.toString()
        dislike.text = article.dislikes.toString()
        image.load(article.imageUrl) { crossfade(true) }
        text.text = article.text
        relevant.text = article.relevant
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_share -> when (val uiState = viewModel.uiState.value) {
            is ArticleViewModel.State.Content -> {
                uiState.article.share(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        else -> super.onOptionsItemSelected(item)
    }
}
