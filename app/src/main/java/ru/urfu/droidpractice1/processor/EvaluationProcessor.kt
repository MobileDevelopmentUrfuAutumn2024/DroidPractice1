package ru.urfu.droidpractice1.processor

/**
 * Механизм оценивания
 */
interface EvaluationProcessor {

    /**
     * Нажатие на кнопку "Лайк"
     */
    fun onLikeButtonClick();

    /**
     * Нажатие на кнопку "Дизлайк"
     */
    fun onDislikeButtonClick();

}