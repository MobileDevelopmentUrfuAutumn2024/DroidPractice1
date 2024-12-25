package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var isArticleRead: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("cycleMethods", "Second activity called \"onCreate\"")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        Glide.with(binding.image)
            .asBitmap()
            .load("https://images.stopgame.ru/uploads/users/2024/526708/00449.hfBR4Rh.jpg")
            .into(binding.image)

        isArticleRead = intent.getBooleanExtra(KEY_READ, false)

        binding.readSwitch.isChecked = isArticleRead
        binding.readSwitch.setOnCheckedChangeListener { _, isRead -> isArticleRead = isRead }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, isArticleRead) })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isArticleRead)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isArticleRead = savedInstanceState.getBoolean(KEY_READ, false)
    }

    override fun onStart() {
        Log.d("cycleMethods", "Second activity called \"onStart\"")
        super.onStart()
    }
    override fun onResume() {
        Log.d("cycleMethods", "Second activity called \"onResume\"")
        super.onResume()
    }
    override fun onPause() {
        Log.d("cycleMethods", "Second activity called \"onPause\"")
        super.onPause()
    }
    override fun onStop() {
        Log.d("cycleMethods", "Second activity called \"onStop\"")
        super.onStop()
    }
    override fun onRestart() {
        Log.d("cycleMethods", "Second activity called \"onRestart\"")
        super.onRestart()
    }
    override fun onDestroy() {
        Log.d("cycleMethods", "Main activity called \"onDestroy\"")
        super.onDestroy()
    }


    companion object {
        const val KEY_READ = "READ"
    }
}