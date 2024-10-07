package ru.urfu.droidpractice1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

interface MainScreenHandler {
    fun onToOtherScreenClicked()
    fun onToShareClicked()
    fun onLike()
    fun onDislike()
    var likesCounter: Int
}