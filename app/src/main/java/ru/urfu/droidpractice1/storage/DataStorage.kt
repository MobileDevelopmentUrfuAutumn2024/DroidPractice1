package ru.urfu.droidpractice1.storage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class DataStorage {
    var img: String
    var articleName: String
    var content: String
    var likesCounter: Int by mutableIntStateOf(0)
    var dislikesCounter: Int by mutableIntStateOf(0)

    constructor(img: String, articleName: String, content: String)
    {
        this.img = img
        this.articleName = articleName
        this.content = content
    }
}