package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity
import android.os.Bundle
import coil.load
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = "https://avatars.mds.yandex.net/i?id=66f05ac87265931efa513ceeaa9754e95428515a-7732367-images-thumbs&n=13"
        binding.photo.load(imageUrl) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
    }
}