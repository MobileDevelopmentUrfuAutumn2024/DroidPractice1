package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.READ_KEY
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.data.Article

class MainActivity : ComponentActivity(), MainScreenHandler {

    private var readed by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read = readed)
        }
    }

    override fun onToArticleClick() {
        val intent = Intent(this, SecondActivity::class.java)
        resultLauncher.launch(intent)
    }

    override fun onToShareClick() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, Article.articles[0].name)
        startActivity(Intent.createChooser(share, R.string.send_article.toString()))
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            readed = data?.getBooleanExtra(READ_KEY, false) ?: false
        }
    }
}