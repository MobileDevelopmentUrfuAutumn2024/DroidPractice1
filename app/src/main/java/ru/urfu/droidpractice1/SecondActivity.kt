package ru.urfu.droidpractice1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var isRead: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val sharedPreferences = getSharedPreferences("article_prefs", Context.MODE_PRIVATE)

        val switchRead = findViewById<Switch>(R.id.switchRead)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        isRead = sharedPreferences.getBoolean("second_article_read", false)
        switchRead.isChecked = isRead

        switchRead.setOnCheckedChangeListener { _, isChecked ->
            isRead = isChecked
            sharedPreferences.edit().putBoolean("second_article_read", isRead).apply()
        }


        buttonBack.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("article_read_status", isRead)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
