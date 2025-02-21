package ru.urfu.droidpractice1.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Состояние доменной модели Activity
 */
class ActivityDomain {
    /**
     * Прочитана ли статья
     */
    var read: Boolean by mutableStateOf(false)

    /**
     * Кол-во лайков на записи
     */
    var likes: Int by mutableIntStateOf(0);
}