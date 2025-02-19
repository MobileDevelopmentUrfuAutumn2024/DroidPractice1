package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen
import kotlin.math.log

class MainActivity : ComponentActivity() {
    private var read: Boolean by mutableStateOf(false)
    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate ")
        if (savedInstanceState != null) {
            // Загрузить состояние likes и dislikes
            likes = savedInstanceState.getInt("likes")
            dislikes = savedInstanceState.getInt("dislikes")}
        setContent {
            MainActivityScreen(::onToOtherScreenClicked, ::onToShareClicked, ::onToLiked, ::onToDisliked, read, likes, dislikes)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainActivity", "onSaveInstanceState")
        // Сохранить состояние likes и dislikes
        outState.putInt("likes", likes)
        outState.putInt("dislikes", dislikes)
    }

    private fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Познавательные научно-популярные статьи")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    private fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        resultLauncher.launch(intent)
    }

    private fun onToLiked() {
        likes++
    }

    private fun onToDisliked() {
        dislikes++
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }
}