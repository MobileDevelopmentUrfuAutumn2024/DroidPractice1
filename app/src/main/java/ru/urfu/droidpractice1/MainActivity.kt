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

    private var read by mutableStateOf(false)
    private var likes by mutableIntStateOf(STARTVALUE)
    private var dislikes by mutableIntStateOf(STARTVALUE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(
                onClickActivity = { onClickActivity() },
                onClickShare = {onClickShare()},
                onClickLike = { likes++ },
                onClickDislike = { dislikes++ },
                read = read,
                likes = likes,
                dislikes = dislikes
            )
        }

        Log.e(LOG, "${this.componentName.className} onCreate")
    }

    private fun onClickActivity() {
        val intent = Intent(this, SecondActivity::class.java)
            .apply {
                putExtra(READ, read)
            }
        launcher.launch(intent)
    }

    private fun onClickShare() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, "Говорит Земля! (2024)")
        startActivity(Intent.createChooser(share, "Поделиться"))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LIKES, likes)
        outState.putInt(DISLIKES, dislikes)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        likes = savedInstanceState.getInt(LIKES)
        dislikes = savedInstanceState.getInt(DISLIKES)
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            read = data?.getBooleanExtra(READ, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(LOG, "${this.componentName.className} onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LOG, "${this.componentName.className} onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG, "${this.componentName.className} onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LOG, "${this.componentName.className} onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LOG, "${this.componentName.className} onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG, "${this.componentName.className} onDestroy")
    }

    companion object {
        const val LIKES = "LIKES"
        const val DISLIKES = "DISLIKES"

        const val READ = "READ"
        const val STARTVALUE = 0

        const val LOG = "LIFECYCLE"
    }
}