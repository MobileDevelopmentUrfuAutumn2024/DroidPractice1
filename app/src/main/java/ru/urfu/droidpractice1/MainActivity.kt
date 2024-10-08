package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity(), MainScreenHandler {

    private var like by mutableIntStateOf(0)
    private var read: Boolean by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.apply {
            like = getInt(LIKE, like)
        }

        setContent {
            MainActivityScreen(
                this,
                like
            )
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, R.string.a1_header)
        startActivity(Intent.createChooser(share, "Переслать статью"))
    }

    override fun onToOtherScreenClicked() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply {
                putExtra(READ, read)
            }
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(READ, false) ?: false
        }
    }

    override fun onClickLike() {
        like++
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Main_Activity", "onSaveInstanceState")
        outState.apply {
            putInt(LIKE, like)
        }
    }

    companion object {
        const val LIKE = "LIKE"
        const val READ = "READ"
    }
}