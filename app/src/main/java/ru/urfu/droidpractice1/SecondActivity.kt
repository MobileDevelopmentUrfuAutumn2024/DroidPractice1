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
    private var checked : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("switch_state", MODE_PRIVATE)

        checked = sharedPreferences.getBoolean("switch_state", false)
        binding.switchRead.isChecked = checked

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.photo)
            .asBitmap()
            .load("https://educon.by/images/article/018.jpg")
            .into(binding.photo)


        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked
            sharedPreferences.edit().putBoolean("switch_state", isChecked).apply()
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}