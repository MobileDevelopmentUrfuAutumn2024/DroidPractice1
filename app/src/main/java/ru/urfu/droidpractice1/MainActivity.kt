package ru.urfu.droidpractice1

import android.media.metrics.LogSessionId
import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.urfu.droidpractice1.content.MainActivityScreen
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.SecondActivity.Companion.KEY_READ
import android.content.Intent

class MainActivity : ComponentActivity() {
    private var read: Boolean by mutableStateOf(false)
    private var counterOfLikes: Int by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::onToOtherScreenClicked,
                ::onToShareClicked,
                ::onLikePlus,
                ::onLikeMinus,
                read,
                counterOfLikes
            )
        }
    }

    private val mainTag = "Main_Activity"

    override fun onStart() {
        Log.d(mainTag, "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(mainTag, "onStop")
        super.onStop()
    }

    override fun onResume() {
        Log.d(mainTag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(mainTag, "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d(mainTag, "onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(mainTag, "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", counterOfLikes)
        outState.putBoolean("read", read)
        Log.d(mainTag, "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counterOfLikes = savedInstanceState.getInt("count")
        read = savedInstanceState.getBoolean("read")
        Log.d(mainTag, "onRestoreInstanceState")
    }

    private fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "...")
        startActivity(Intent.createChooser(share, "Переслать"))
    }

    private fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply { putExtra("read", read) }
        resultLauncher.launch(intent)
    }

    private fun onLikePlus() {
        ++counterOfLikes
    }
    private fun onLikeMinus() {
        --counterOfLikes
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    companion object {
        const val READ = "READ"
    }
}