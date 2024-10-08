package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.MainActivity.Companion.IS_READ

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var isRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       binding.apply {

           toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

           Glide.with(imageView)
               .asBitmap()
               .load("https://img.championat.com/s/732x488/news/big/w/c/vashington-provyol-udarnoe-leto_1727881683626943292.jpg")
               .into(imageView)

           isRead = intent.getBooleanExtra(IS_READ, false)
           switchIsRead.isChecked = isRead
           switchIsRead.setOnCheckedChangeListener { _, isChecked -> isRead = isChecked }
       }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, isRead) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("SecondActivity", "inside onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "inside onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "inside onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "inside onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("SecondActivity", "inside onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "inside onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isRead)
        Log.d("SecondActivity", "inside onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRead = savedInstanceState.getBoolean(KEY_READ)
        Log.d("SecondActivity", "inside onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}