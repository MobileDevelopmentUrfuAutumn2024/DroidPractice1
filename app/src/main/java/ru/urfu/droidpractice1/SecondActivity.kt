package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var hasRead: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            Glide
                .with(picture1)
                .asBitmap()
                .load(getString(R.string.picture1))
                .into(picture1)
            Glide
                .with(picture2)
                .asBitmap()
                .load(getString(R.string.picture2))
                .into(picture2)

            hasRead = intent.getBooleanExtra(HAS_READ_KEY, false)
            readSwitch.isChecked = hasRead
            readSwitch.setOnCheckedChangeListener { _, isChecked -> hasRead = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(HAS_READ_KEY, hasRead) })
            finish()
        }

        Log.d("SecondActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SecondActivity", "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SecondActivity", "onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        Log.d("SecondActivity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(HAS_READ_KEY, hasRead)
        Log.d("SecondActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        hasRead = savedInstanceState.getBoolean(HAS_READ_KEY)
        Log.e("SecondActivity", "onRestoreInstanceState")
    }

    companion object {
        const val HAS_READ_KEY = "HAS_READ_KEY"
    }
}