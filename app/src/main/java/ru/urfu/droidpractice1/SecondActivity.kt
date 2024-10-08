package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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

        checked = intent.getBooleanExtra(KEY_READ, false)
        binding.switchRead.isChecked = checked

        Glide.with(binding.photo)
            .asBitmap()
            .load("https://www.ixbt.com/img/n1/news/2024/8/4/RTX5090-HERO-1-1200x624_large.jpg")
            .into(binding.photo)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    companion object {
        const val KEY_READ = "READ"
    }

    override fun onStart() {
        super.onStart()
        Log.i("testLog", "${this::class.java.name} : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("testLog", "${this::class.java.name} : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("testLog", "${this::class.java.name} : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("testLog", "${this::class.java.name} : onStop")
    }

    override fun onDestroy() {
        Log.i("testLog", "${this::class.java.name} : onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("testLog", "${this::class.java.name} : onRestart")
    }
}