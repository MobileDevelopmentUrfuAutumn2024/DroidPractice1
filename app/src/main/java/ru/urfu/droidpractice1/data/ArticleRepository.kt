package ru.urfu.droidpractice1.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.urfu.droidpractice1.entity.Article

interface ArticleRepository {

    fun articleFlow(id: String): Flow<Article>

    fun like(id: String)

    fun dislike(id: String)

    fun view(id: String)

    // singleton instead of proper DI
    companion object : ArticleRepository by ArticleRepositoryImpl()
}

class ArticleRepositoryImpl : ArticleRepository {

    // dummy flow as a data source
    private val flows = mutableMapOf<String, MutableStateFlow<Article>>()

    override fun articleFlow(id: String): Flow<Article> {
        return flows.getOrPut(id) { MutableStateFlow(dummyArticle(id)) }
    }

    override fun like(id: String) {
        flows[id]?.update { it.copy(likes = it.likes + 1) }
    }

    override fun dislike(id: String) {
        flows[id]?.update { it.copy(dislikes = it.dislikes + 1) }
    }

    override fun view(id: String) {
        flows[id]?.update { it.copy(isViewed = true) }
    }

    companion object DummyData {

        private fun dummyArticle(id: String): Article {
            return when (id) {
                "1" -> Article(
                    id = "1",
                    imageUrl = "https://s-cdn.sportbox.ru/images/styles/upload/fp_fotos/1e/6a/74e9bfc4ca26735ebd52f1e8cf8495da66a8b457a4918095248260.jpg",
                    title = "Лукас Вера сравнил «Химки» с «Челси»",
                    text = """
                        Полузащитник «Химок» Лукас Вера сравнил подмосковную команду с лондонским «Челси».

                        — Общественность сравнивает «Химки» с «Челси». Количество игроков — единственная схожесть?
                        — Есть такое. Сейчас очень много игроков в команде, но самое важное, что мы хорошо работаем. Никаких проблем я не вижу.
                        — В чём «Химки» лучше «Челси»?
                        — Это другая лига. У нас другой стиль. Мы делаем на поле то, что просит наш тренер Франк Артига. Но пока очков не так много у нас, — сказал Вера в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.

                        После 10 туров РПЛ сезона-2024/2025 «Химки» набрали восемь очков и занимают 14-е место в турнирной таблице.
                    """.trimIndent(),
                    relevant = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
                    likes = 68,
                    dislikes = 419,
                    isViewed = false,
                )
                "2" -> Article(
                    id = "2",
                    imageUrl = "https://img.championat.com/s/1350x900/news/big/u/i/igrok-krylev-kostanca-obyasn.jpg",
                    title = "Игрок «Крыльев» Костанца объяснил, чего не хватило для победы в матче с «Химками»",
                    text = """
                        Бразильский футболист «Крыльев Советов» Фернандо Костанца прокомментировал ничейный результат в матче 10-го тура Российской Премьер-Лиги с «Химками». Встреча прошла сегодня в Самаре. Она закончилась со счётом 0:0.
                        
                        Мы не забили. Вот, что случилось сегодня. Думаю, мы провели хороший матч. Старались что-то создать, контролировали мяч. Возможно, последний пас и удары были не самыми лучшими», — сказал Костанца в беседе с корреспондентом «Чемпионата» Ильёй Никульниковым.
                    """.trimIndent(),
                    relevant = "Лукас Вера сравнил «Химки» с «Челси»",
                    likes = 227,
                    dislikes = 1336,
                    isViewed = false,
                )
                else -> error("Article not found")
            }
        }
    }
}
