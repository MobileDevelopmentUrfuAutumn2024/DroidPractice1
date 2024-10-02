package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.data.Article
import ru.urfu.droidpractice1.data.getFragments
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var readed by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val article = Article.articles[1]

        setUI(article)
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onCreate")
    }

    private fun setUI(article: Article) {
        readed = intent.getBooleanExtra(READ_KEY, false)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val texts = article.getFragments()

        binding.articleName.text = article.name
        binding.switchRead.isChecked = readed
        binding.textStartSecond.text = texts.first()
        binding.textEndSecond.text = texts.last()

        Glide
            .with(binding.photoArticle)
            .asBitmap()
            .load(article.path[0])
            .into(binding.photoArticle)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.switchRead.setOnCheckedChangeListener { _, isReaded ->
            readed = isReaded
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply {
                putExtra(READ_KEY, readed)
            })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(MainActivity.LIFECYCLE_KEY, "SecondActivity onDestroy")
    }

    companion object {
        const val READ_KEY = "READ"
    }
}