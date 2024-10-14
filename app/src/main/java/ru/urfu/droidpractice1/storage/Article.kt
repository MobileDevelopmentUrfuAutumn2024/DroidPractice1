package ru.urfu.droidpractice1.storage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Article {
    var imageUri: String
    var title: String
    var text: String
    var likes: Int by mutableIntStateOf(0)
    var disLikes: Int by mutableIntStateOf(0)
    var isViewed: Boolean by mutableStateOf(false)

    constructor(imageUri: String, title: String, text: String)
    {
        this.imageUri = imageUri
        this.title = title
        this.text = text
    }
}