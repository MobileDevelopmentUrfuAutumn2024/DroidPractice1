package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding
import ru.urfu.droidpractice1.domain.ActivityDomain
import ru.urfu.droidpractice1.domain.BundleKey

class SecondActivity : LoggableLifeCycleComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var activityDomain: ActivityDomain = ActivityDomain()

    override fun getClazz(): String {
        return this.localClassName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        activityDomain.read = intent.getBooleanExtra(BundleKey.READ, false)
        binding.switchRead.isChecked = activityDomain.read
        binding.switchRead.setOnCheckedChangeListener { _, checked -> activityDomain.read = checked }

        Glide.with(binding.linkPhoto)
            .asBitmap()
            .load(resources.getString(R.string.link_article_picture_link))
            .into(binding.linkPhoto)

        onBackPressedDispatcher.addCallback(this) {
            setResult(RESULT_OK, Intent().apply {
                putExtra(BundleKey.READ, activityDomain.read)
            })
            finish()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BundleKey.READ, activityDomain.read)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        activityDomain.read = savedInstanceState.getBoolean(BundleKey.READ)
    }

}