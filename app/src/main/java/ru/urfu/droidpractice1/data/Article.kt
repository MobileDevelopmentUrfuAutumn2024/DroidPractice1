package ru.urfu.droidpractice1.data

/**
 * @author Lapoushko
 * Статья
 * @param name имя статьи
 * @param text текст статьи
 * @param path пути к картинкам статьи
 */
class Article(
    val name: String,
    val text: String,
    val path: List<String>
) {
    companion object {
        val articles = listOf(
            Article(
                name = "Стало известно, что Даниил Медведев сказал Карлосу Алькарасу после поражения в Пекине",
                text = "«Я сказал Карлосу, что в следующий раз покрашусь в блондинку и " + "напишу на лбу «Ботик», может, это поможет!», " + "— сказал Медведев после матча.\n" + "\n" + "Встреча продолжалась 1 час 25 минут. " + "Россиянин три раза подал навылет," + "допустил две двойные ошибки и реализовал два брейк-пойнта из двух заработанных. " + "На счету испанца три эйса, одна двойная ошибка и 5 выигранных брейк-пойнтов из 11.\n" + "\n" + "В матче за титул на турнире в Пекине Алькарас " + "встретится с победителем встречи между первой ракеткой мира " + "итальянцем Янником Синнером " + "и представителем Китая Бу Юньчаокэтэ (96-й в рейтинге ATP).",
                path = listOf("https://img.championat.com/s/732x488/news/big/n/r/stalo-izvestno-chto-daniil-medvedev-skazal-karlosu-alkarasu-posle-porazheniya-v-pekine_1727781319135979761.jpg")
            ),
            Article(
                name = "Аслан Карацев не смог пробиться в основную сетку «Мастерса» в Шанхае",
                text = "Российский теннисист 139-я ракетка мира Аслан Карацев не смог пробиться " + "в основную сетку турнира категории «Мастерс» в Шанхае (Китай). " + "В финальном матче квалификационного раунда он в двух сетах проиграл итальянцу Маттии Беллуччи — 7:6 (7:3), 6:1.\n" + "\n" + "Встреча продолжалась 1 час 34 минуты. " + "Россиянин один раз подал навылет, допустил одну двойную ошибку и реализовал один брейк-пойнт из двух заработанных. " + "На счету итальянца 11 эйсов, две двойные ошибки и 3 выигранных брейк-пойнта из 11.\n" + "\n" + "Соревнования в Шанхае пройдут с 2 по 13 октября. " + "Действующим чемпионом турнира является польский теннисист Хуберт Хуркач, " + "в прошлогоднем финале обыгравший россиянина Андрея Рублёва (6:3, 3:6, 7:6).",
                path = listOf("https://img.championat.com/s/732x488/news/big/l/m/aslan-karacev-ne-smog-probitsya-v-osnovnuyu-setku-mastersa-v-shanhae_17277805131536517985.jpg")
            )
        )
    }
}

/**
 * Разделить текст по абзацам
 * @return список абзацев
 */
fun Article.getFragments(): List<String> = this.text
    .split("\n")
    .filter { it != "" }