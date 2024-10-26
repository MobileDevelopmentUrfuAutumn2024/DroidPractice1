package ru.urfu.droidpractice1.entity

import android.content.Context
import android.content.Intent

data class Article(
    val id: String,
    val imageUrl: String,
    val title: String,
    val text: String,
    val relevant: String,
    val likes: Int,
    val dislikes: Int,
    val isViewed: Boolean,
)

fun Article.share(context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, title))
}
