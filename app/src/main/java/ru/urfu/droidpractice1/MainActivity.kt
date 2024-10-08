package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import ru.urfu.droidpractice1.processor.MainActivityProcessor
import ru.urfu.droidpractice1.content.MainActivityScreen
import ru.urfu.droidpractice1.domain.ActivityDomain
import ru.urfu.droidpractice1.domain.BundleKey

class MainActivity : LoggableLifeCycleComponentActivity() {

    private var activityDomain: ActivityDomain = ActivityDomain();

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                activityDomain.read = result.data?.getBooleanExtra(BundleKey.READ, false) ?: false
            }
        }

    override fun getClazz(): String {
        return this.localClassName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen(MainActivityProcessorImpl(this), activityDomain)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BundleKey.READ, activityDomain.read)
        outState.putInt(BundleKey.LIKES, activityDomain.likes)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        activityDomain.read = savedInstanceState.getBoolean(BundleKey.READ)
        activityDomain.likes = savedInstanceState.getInt(BundleKey.LIKES)
    }

    inner class MainActivityProcessorImpl(private val context: MainActivity) : MainActivityProcessor {

        override fun onArticleLinkClick() {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(BundleKey.READ, activityDomain.read)
            resultLauncher.launch(intent)
        }

        override fun onShareClick() {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.article_header))
                startActivity(Intent.createChooser(this, "Поделиться"))
            }
        }

        override fun onLikeButtonClick() {
            activityDomain.likes++;
        }

        override fun onDislikeButtonClick() {
            activityDomain.likes = if (activityDomain.likes > 0) --activityDomain.likes else 0
        }

    }

}