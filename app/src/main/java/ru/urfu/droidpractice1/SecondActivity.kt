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




        Glide.with(binding.photo).asBitmap()
            .load("https://www.zr.ru/_next/image/?url=https%3A%2F%2Fst1.zr.ru%2F_ah%2Fimg%2FxMa35Kc2F-Mw3nA8rTMUVQ%3Ds800&w=1920&q=75")
            .into(binding.photo)

        binding.switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked}

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("checked", checked)
        Log.d("SecondActivity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean("checked")
        Log.d("SecondActivity", "onRestoreInstanceState")
    }

    companion object{
        const val KEY_READ = "READ"
    }
}