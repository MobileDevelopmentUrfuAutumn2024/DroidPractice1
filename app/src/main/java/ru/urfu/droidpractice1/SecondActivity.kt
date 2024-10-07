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

    private var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.photo)
            .asBitmap()
            .load("https://opis-cdn.tinkoffjournal.ru/mercury/witcher-games-preview-01.zegc4gjelhug..jpg?preset=image_570w_2x")
            .into(binding.photo)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("Second_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Second_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Second_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Second_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Second_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        /* У нас на прошлой паре придумано предположение, что этот метод не вызовиться */
        Log.d("Second_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("read", checked)
        Log.d("Second_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean("read")
        Log.d("Second_Activity", "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}