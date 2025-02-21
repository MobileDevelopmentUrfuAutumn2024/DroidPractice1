package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.LIFE_CYCLE_TAG
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var isArticleRead: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(LIFE_CYCLE_TAG, "Callback \"onCreate\" was called from \"SecondActivity\"")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.image)
            .asBitmap()
            .load("https://0d314c86-f76b-45cc-874e-45816116a667.selcdn.net/ac9c7de4-6d7b-4095-aa6b-767d9017655e.jpg")
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
        Log.d(LIFE_CYCLE_TAG, "Callback \"onSaveInstanceState\" was called from \"SecondActivity\"")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isArticleRead = savedInstanceState.getBoolean(KEY_READ, false)
        Log.d(
            LIFE_CYCLE_TAG,
            "Callback \"onRestoreInstanceState\" was called from \"SecondActivity\""
        )
    }


    override fun onStart() {
        super.onStart()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onStart\" was called from \"SecondActivity\"")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onResume\" was called from \"SecondActivity\"")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onPause\" was called from \"SecondActivity\"")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onStop\" was called from \"SecondActivity\"")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LIFE_CYCLE_TAG, "Callback \"onRestart\" was called from \"SecondActivity\"")
    }

    override fun onDestroy() {
        Log.d(LIFE_CYCLE_TAG, "Callback \"onDestroy\" was called from \"SecondActivity\"")
        super.onDestroy()
    }

    companion object {
        const val KEY_READ = "READ"
    }
}