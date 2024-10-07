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
import ru.urfu.droidpractice1.SecondActivity.Companion.VIEWED_KEY
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {
    private var read: Boolean by mutableStateOf(false)
    private var feedback: Feedback by mutableStateOf(Feedback.getEmpty())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            read = getBoolean(READ_KEY, read)
            val likes = getString(LIKES_KEY, feedback.likes)
            val dislikes = getString(DISLIKES_KEY, feedback.dislikes)
            feedback = Feedback.getByStrings(likes, dislikes)
        }
        Log.i(LOG_TAG, "${this::class.java.name} : onCreate")
        setContent {
            MainActivityScreen(
                ::onToOtherScreen,
                read,
                ::onShareClick,
                feedback,
                ::onLikeClick,
                ::onDislikeClick
            )
        }
    }

    private fun onToOtherScreen() {
        resultLauncher.launch(Intent(this, SecondActivity::class.java).apply {
            putExtra(VIEWED_KEY, read)
        })
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                read = result.data?.getBooleanExtra(VIEWED_KEY, false) ?: false
            }
        }

    private fun onShareClick() {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.s1_1))
            startActivity(Intent.createChooser(this, "Поделиться"))
        }
    }

    private fun onLikeClick() {
        feedback = feedback.doAction(FeedbackAction.Like)
    }

    private fun onDislikeClick() {
        feedback = feedback.doAction(FeedbackAction.Dislike)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putBoolean(READ_KEY, read)
            putString(LIKES_KEY, feedback.likes)
            putString(DISLIKES_KEY, feedback.dislikes)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG, "${this::class.java.name} : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "${this::class.java.name} : onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG, "${this::class.java.name} : onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LOG_TAG, "${this::class.java.name} : onStop")
    }

    override fun onDestroy() {
        Log.i(LOG_TAG, "${this::class.java.name} : onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_TAG, "${this::class.java.name} : onRestart")
    }

    companion object {
        const val LOG_TAG = "MYTAG"
        const val READ_KEY = "READ"
        const val LIKES_KEY = "LIKES"
        const val DISLIKES_KEY = "DISLIKES"
    }

}