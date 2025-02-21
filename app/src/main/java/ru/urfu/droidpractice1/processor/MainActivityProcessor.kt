package ru.urfu.droidpractice1.processor

/**
 * Обработчик главного окна
 */
interface MainActivityProcessor : EvaluationProcessor {

    /**
     * Нажатие на блок со ссылкой на другую статью
     */
    fun onArticleLinkClick()

    /**
     * Нажатие на кнопку "Поделиться"
     */
    fun onShareClick()

}