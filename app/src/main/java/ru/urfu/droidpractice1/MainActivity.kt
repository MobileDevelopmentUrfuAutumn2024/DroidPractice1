package ru.urfu.droidpractice1

import MainScreenHandler
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {
    private var read: Boolean by mutableStateOf(false)
    private var countLike: Int by mutableIntStateOf(0)
    private var countDislike: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read, countLike, countDislike)
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Карты теней")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun onLikeClicked() {
        countLike++;
    }

    override fun onDislikeClicked() {
        countDislike++;
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(KEY_READ, read)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }
}