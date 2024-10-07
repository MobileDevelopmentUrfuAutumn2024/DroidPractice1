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
            .load("https://cdn.vox-cdn.com/thumbor/jn02bT3OlUFWU8dwajwIxBW06Wg=/42x0:558x290/1600x900/cdn.vox-cdn.com/uploads/chorus_image/image/36583762/df_intro.0.0.jpg")
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
        Log.i("droid practice", "${this::class.java.name} : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("droid practice", "${this::class.java.name} : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("droid practice", "${this::class.java.name} : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("droid practice", "${this::class.java.name} : onStop")
    }

    override fun onDestroy() {
        Log.i("droid practice", "${this::class.java.name} : onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("droid practice", "${this::class.java.name} : onRestart")
    }
}