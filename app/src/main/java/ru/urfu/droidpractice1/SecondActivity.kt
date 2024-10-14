package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var isChecked by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar navigation
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Load image using Glide
        Glide.with(binding.photo)
            .load("https://sudamerica.ru/images/2024-10-12/120172.webp")
            .into(binding.photo)

        // Handle switch state
        isChecked = intent.getBooleanExtra(KEY_READ, false)
        binding.switchRead.isChecked = isChecked
        binding.switchRead.setOnCheckedChangeListener { _, isChecked ->
            this.isChecked = isChecked
        }

        // Handle back button pressed
        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, isChecked) })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, isChecked)
        Log.d("SecondActivity", "onSaveInstanceState: read=$isChecked")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isChecked = savedInstanceState.getBoolean(KEY_READ, false)
        Log.d("SecondActivity", "onRestoreInstanceState: read=$isChecked")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}
