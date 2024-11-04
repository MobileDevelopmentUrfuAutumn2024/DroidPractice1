package ru.urfu.droidpractice1.repositiry

import ru.urfu.droidpractice1.content.Article

fun createArticle(likes: Int, isViewed: Boolean): Article {
    return Article(
        id = "2",
        imageUrl = "https://img.championat.com/s/1350x900/news/big/u/i/igrok-krylev-kostanca-obyasn.jpg",
        title = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
        text = """
                        Бразильский футболист «Крыльев Советов» Фернандо Костанца прокомментировал ничейный результат в матче 10-го тура Российской Премьер-Лиги с «Химками». Встреча прошла сегодня в Самаре. Она закончилась со счётом 0:0.
                        
                        Мы не забили. Вот, что случилось сегодня. Думаю, мы провели хороший матч. Старались что-то создать, контролировали мяч. Возможно, последний пас и удары были не самыми лучшими», — сказал Костанца в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.
                    """.trimIndent(),
        relevant = "Лукас Вера сравнил «Химки» с «Челси»",
        likes = likes,
        isViewed = isViewed,
    )
}
