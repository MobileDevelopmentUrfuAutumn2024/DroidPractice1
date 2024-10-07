package ru.urfu.droidpractice1

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import ru.urfu.droidpractice1.content.MainActivityScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity(), HandlerMainScreen {

    private var read: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read)
        }
    }

    override fun toSecondActivityClick() {
        intent = Intent(this, SecondActivity::class.java).putExtra("state_switch", read)
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                read = data?.getBooleanExtra("READ", false  ) ?: true
            }
        }
}