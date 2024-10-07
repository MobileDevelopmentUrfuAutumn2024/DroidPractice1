package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {
    private var read: Boolean by mutableStateOf(false)
    private var likes: Int by mutableStateOf(0)
    private var dislikes: Int by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read, likes, dislikes)
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Какой-то подпивас сравнил Dwarf Fortress с Excel")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, read)
        }
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    override fun plusLikes() {
        likes++
    }

    override fun plusDislikes() {
        dislikes++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("likes", likes)
        outState.putInt("dislikes", dislikes)
        outState.putBoolean(KEY_READ, read)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likes = savedInstanceState.getInt("likes")
        dislikes = savedInstanceState.getInt("dislikes")
        read = savedInstanceState.getBoolean(KEY_READ)
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