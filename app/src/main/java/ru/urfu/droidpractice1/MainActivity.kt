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
    private var likesCount: Int by mutableStateOf(0)
    private var diizzlikesCount: Int by mutableStateOf(0)
    private val TAG = "Main_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read, likesCount, diizzlikesCount)
        }
    }

    override fun onLikeClicked() {
        likesCount++
        print("Pressed")
    }

    override fun onDislikeClicked() {
        diizzlikesCount++
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("likesCount", likesCount)
        outState.putInt("dislikesCount", diizzlikesCount)
        outState.putBoolean("read", read)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likesCount = savedInstanceState.getInt("likesCount")
        diizzlikesCount = savedInstanceState.getInt("dislikesCount")
        read = savedInstanceState.getBoolean("read")
        Log.d("Main_Activity", "onRestoreInstanceState")
    }


    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)

        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Это была ошибка! — самые странные автомобили мира")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(KEY_READ, read)
        }
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    read = data?.getBooleanExtra(KEY_READ, false) ?: false
                }
        }


    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart")
        super.onRestart()
    }
}