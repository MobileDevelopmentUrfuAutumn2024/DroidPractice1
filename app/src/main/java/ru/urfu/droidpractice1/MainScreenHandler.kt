package ru.urfu.droidpractice1

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

    /**
     * Лайкнуть статью
     */
    fun onClickLike()

    /**
     * Дизлайкнуть статью
     */
    fun onClickDislike()
}