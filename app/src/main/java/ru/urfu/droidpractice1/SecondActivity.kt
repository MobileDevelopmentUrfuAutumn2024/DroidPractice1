package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.addCallback
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.target.ImageViewTarget
import ru.urfu.droidpractice1.MainActivity.Companion.LIKES_COUNT
import ru.urfu.droidpractice1.MainActivity.Companion.SECOND_ARTICLE_IS_READ
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.let {
            it.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            val imageView: ImageView = findViewById(R.id.image_main)
            imageView.load("https://img.shields.io/packagist/dt/laravel/framework") {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
            }

            isRead = intent.getBooleanExtra(SECOND_ARTICLE_IS_READ, false)
            it.switchIsRead.isChecked = isRead
            it.switchIsRead.setOnCheckedChangeListener { _, isChecked -> isRead = isChecked }
        }
        onBackPressedDispatcher.addCallback(this) {
            Log.d("SecondActivity", "Article is read: $isRead")
            setResult(RESULT_OK, Intent().apply { putExtra(SECOND_ARTICLE_IS_READ, isRead) })
            finish()
        }
    }

    override fun onRestart() {
        Log.d("SecondActivity", "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "onDestroy")
        super.onDestroy()
    }

    // Bundle manipulation
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("SecondActivity", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        outState.putBoolean(SECOND_ARTICLE_IS_READ, isRead)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("SecondActivity", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(SECOND_ARTICLE_IS_READ)
    }
}
