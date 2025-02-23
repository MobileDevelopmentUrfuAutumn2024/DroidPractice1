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

class MainActivity : ComponentActivity() {

    private var read: Boolean by mutableStateOf(false)

    private var countLike: Int by mutableIntStateOf(0)

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(KEY_READ, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                ::onToOtherScreenClicked,
                ::onToShareClicked,
                ::onLikePlus,
                ::onLikeMinus,
                read,
                countLike
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
        /* У нас на прошлой паре придумано предположение, что этот метод не вызовиться */
        Log.d("Main_Activity", "onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", countLike)
        outState.putBoolean("read", read)
        Log.d("Main_Activity", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        countLike = savedInstanceState.getInt("count")
        read = savedInstanceState.getBoolean("read")
        Log.d("Main_Activity", "onRestoreInstanceState")
    }

    private fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Проезд будет стоить 20 рублей")
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    private fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply { putExtra("read", read) }
        resultLauncher.launch(intent)
    }

    private fun onLikePlus() {
        countLike++
    }

    private fun onLikeMinus() {
        if (countLike > 0) {
            countLike--
        }
    }
}