package ru.urfu.droidpractice1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var checked: Boolean = false

    @SuppressLint("UseSwitchCompatOrMaterialCode", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras
        if (extras != null) {
            checked = extras.getBoolean("state_switch")
        }
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val sw1 = findViewById<Switch>(R.id.switchRead)
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            checked = isChecked
        }

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply { putExtra("READ", checked) })
            finish()
        }
    }
}

