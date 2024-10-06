package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            Glide.with(photo)
                .asBitmap()
                .load("https://n1s1.hsmedia.ru/36/6a/82/366a826bdafd6348f8f15fbfaa6024c8/656x438_1:5523_d56bb1fd264484cd4db894f82c9f60e1@3008x2008_0xVcnir67y_8864381406318415952.jpg.webp")
                .into(photo)

            checked = intent.getBooleanExtra("read", false)
            switchRead.isChecked = checked
            switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

}