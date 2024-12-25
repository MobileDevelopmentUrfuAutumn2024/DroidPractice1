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
    private var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Second_Activity", "onCreate")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
            Glide.with(photo)
                .asBitmap()
                .load("https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_675c5d974cc190059b74ec55_675c5da42fe0dd58695e0b30/scale_2400")
                .into(photo)

            checked = intent.getBooleanExtra(KEY_READ, false)
            switchRead.isChecked = checked
            switchRead.setOnCheckedChangeListener { _, isChecked -> checked = isChecked }
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra(KEY_READ, checked) })
            finish()
        }
    }

    override fun onStart() {
        Log.d("Second_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Second_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Second_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Second_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Second_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Second_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_READ, checked)
        Log.d("Second_Activity", "onRestoreInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean(KEY_READ)
        Log.d("Second_Activity", "onRestoreInstanceState")
    }

    companion object {
        const val KEY_READ = "READ"
    }
}