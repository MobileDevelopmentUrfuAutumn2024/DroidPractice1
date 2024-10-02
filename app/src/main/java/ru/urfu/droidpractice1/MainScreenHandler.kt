package ru.urfu.droidpractice1

import androidx.activity.ComponentActivity

/**
 * @author Lapoushko
 * Кнопки главного экрана
 */
interface MainScreenHandler {
    /**
     * Клик на следующую статью
     */
    fun onToArticleClick()

    /**
     * Клик для отправки статьи
     */
    fun onToShareClick()
}