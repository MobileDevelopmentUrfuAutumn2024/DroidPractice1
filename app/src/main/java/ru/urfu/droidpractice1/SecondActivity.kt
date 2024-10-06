package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
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
                .load("https://img.championat.com/s/732x488/news/big/r/q/dzhoker-2-2024-otzyv-obzor_1728155102644467966.jpg")
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