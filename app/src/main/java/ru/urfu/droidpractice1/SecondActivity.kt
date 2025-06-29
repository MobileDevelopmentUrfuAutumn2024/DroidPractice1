package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding


class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var readSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        Glide.with(binding.secondArticleIMG1)
            .asBitmap()
            .load("https://img.championat.com/s/1350x900/news/big/s/i/audi-postroila-avto-za-12-mln_16668007081565238516.jpg")
            .override(1080, 720)
            .into(binding.secondArticleIMG1)


        Glide.with(binding.secondArticleIMG2)
            .asBitmap()
            .load("https://img.championat.com/i/b/h/16667999191906934541.jpg")
            .override(1080, 720)
            .into(binding.secondArticleIMG2)

        readSwitch = findViewById(R.id.readSwitch)
        val sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        readSwitch.isChecked = sharedPreferences.getBoolean("isSecondArticleRead", false)
        readSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("isSecondArticleRead", isChecked).apply()
        }

        onBackPressedDispatcher.addCallback(this) {
            val resultIntent = Intent().apply {
                putExtra("isSecondArticleRead", readSwitch.isChecked)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }



    }

}