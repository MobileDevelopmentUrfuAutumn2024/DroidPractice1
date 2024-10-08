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
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    private var isStateRead: Boolean by mutableStateOf(false)
    private var likeCount: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::onToOtherScreenClicked,
                ::onToShareClicked,
                ::onIncrementLike,
                ::onDecrementLike,
                isStateRead,
                likeCount,
            )
        }
    }

    override fun onStart() {
        Log.d("Main_Activity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Main_Activity", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Main_Activity", "onPause")
        super.onPause()
    }

    override fun onRestart() {
        Log.d("Main_Activity", "onRestart")
        super.onRestart()
    }

    override fun onStop() {
        Log.d("Main_Activity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Main_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("likeCount", likeCount)
        outState.putBoolean("isStateRead", isStateRead)
        Log.d("Main_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likeCount = savedInstanceState.getInt("likeCount")
        isStateRead = savedInstanceState.getBoolean("isStateRead")
        Log.d("Main_Activity", "onRestoreInstanceState")
    }

    private fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, R.string.article_title)
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    private fun onToOtherScreenClicked() {
        /** Перекидываем на другую Activity и передаем значение isStateRead */
        val intent =
            Intent(this, SecondActivity::class.java).apply { putExtra("isStateRead", isStateRead) }
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        /** Получаем значение isStateRead с другой Activity */
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                isStateRead = data?.getBooleanExtra("isStateRead", false) ?: false
            }
        }

    private fun onIncrementLike() {
        likeCount++
    }

    private fun onDecrementLike() {
        if (likeCount <= 0) return;

        likeCount--
    }
}