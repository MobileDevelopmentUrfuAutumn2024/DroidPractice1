package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
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
    }

    private fun setUI(article: Article){
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

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.switchRead.setOnCheckedChangeListener{_,isReaded -> readed = isReaded}

        onBackPressedDispatcher.addCallback(this){
            setResult(RESULT_OK, Intent().apply {
                putExtra(READ_KEY, readed)
            })
            finish()
        }
    }

    companion object{
        const val READ_KEY = "READ"
    }
}