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
    private var likeCount: Int by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read, likeCount)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("likeCount", likeCount)
        outState.putBoolean("read", read)
        Log.d("Main_Activity", "onRestoreInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likeCount = savedInstanceState.getInt("likeCount")
        read = savedInstanceState.getBoolean("read")
        Log.d("Main_Activity", "onRestoreInstanceState")
    }

    override fun clickOnLike(){
        likeCount++
    }

    override fun clickOnDislike(){
        if (likeCount>0)
            likeCount--
    }

    override fun shareOnClick(){
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "10 фактов о кошках")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun toOtherScreenOnClick() {
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
}