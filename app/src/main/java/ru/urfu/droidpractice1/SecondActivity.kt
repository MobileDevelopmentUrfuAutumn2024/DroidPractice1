package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.compose.ui.res.stringResource
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
            .load("https://habrastorage.org/r/w1560/webt/r9/lx/7m/r9lx7mpk883fazw1cf_gebpzers.png")
            .into(binding.photo)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("SecondActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("SecondActivity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("SecondActivity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("SecondActivity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("SecondActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, checked)
        Log.d("SecondActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean(KEY_READ)
        Log.d("SecondActivity", "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}