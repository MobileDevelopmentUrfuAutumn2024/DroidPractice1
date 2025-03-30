package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.MainActivity.Companion.LOG_TAG

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            isChecked = getBoolean(IS_CHECKED_KEY)
        }
        Log.i(LOG_TAG, "${this::class.java.name} : onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.image)
            .asBitmap()
            .load("https://pm1.aminoapps.com/7725/c691759fec689233f6cce1fef6ed10139ec6b265r1-1080-1081v2_uhq.jpg")
            .into(binding.image)

        initializeSwitch()
    }

    private fun initializeSwitch(){
        intent.getBooleanExtra(VIEWED_KEY, false).let {
            binding.read.isChecked = it
            isChecked = it
        }

        binding.read.isChecked = isChecked

        binding.read.setOnCheckedChangeListener { _, checked -> isChecked = checked }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(VIEWED_KEY, isChecked) })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_CHECKED_KEY, binding.read.isChecked)
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG, "${this::class.java.name} : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "${this::class.java.name} : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG, "${this::class.java.name} : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG_TAG, "${this::class.java.name} : onStop")
    }

    override fun onDestroy() {
        Log.i(LOG_TAG, "${this::class.java.name} : onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_TAG, "${this::class.java.name} : onRestart")
    }

    companion object {
        const val VIEWED_KEY = "IS_VIEWED"
        const val IS_CHECKED_KEY = "IS_CHECKED"
    }
}