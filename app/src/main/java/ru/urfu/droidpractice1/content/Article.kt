package ru.urfu.droidpractice1.content

data class Article(
    val id: String,
    val imageUrl: String,
    val title: String,
    val text: String,
    val relevant: String,
    val likes: Int,
    val isViewed: Boolean,
)
