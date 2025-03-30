package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity() : ComponentActivity(), HandlerMainScreen {

    private var read: Boolean by mutableStateOf(false)
    private var countLike: Int by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(this, read,
                likeClick = ::likeClick,
                disLikeClick = ::disLikeClick,
                countLike = countLike)
        }
    }

    override fun onToShareClicked() {
        val share = Intent(Intent.ACTION_SEND)
        share.setType("text/plain")
        share.putExtra(Intent.EXTRA_TEXT, "Блины и Кот Кокос")
        startActivity((Intent.createChooser(share, "Лучший товар от кота Кокоса")))
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        outState.putInt("countLike", countLike)
        outState.putBoolean("read", read)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        countLike = savedInstanceState.getInt("countLike")
        read = savedInstanceState.getBoolean("read")
    }

    override fun likeClick(){
        countLike++
    }

    override fun disLikeClick(){
        if (countLike > 0){
            countLike--
        }
    }

    override fun toSecondActivityClick() {
        intent = Intent(this, SecondActivity::class.java).putExtra("state_switch", read)
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                read = data?.getBooleanExtra("READ", false  ) ?: true
            }
        }
}