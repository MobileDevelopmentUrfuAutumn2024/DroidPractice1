@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    read: Boolean = false,
    likes: Int = 0,
    dislikes: Int = 0,
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    },
                    actions = {
                        Icon(
                            modifier = Modifier.clickable { handler.onToShareClicked() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(20.dp)
            ) {
                Text(text = "Стала известна предполагаемая дата презентации видеокарт GeForce RTX 5090 и RTX 5080", style = Typography.titleLarge)

                Row {
                    Icon(
                        modifier = Modifier.clickable { handler.addLike() }.padding(20.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = likes.toString(),
                    )

                    Icon(
                        modifier = Modifier.clickable { handler.addDislike() }.padding(20.dp),
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = dislikes.toString(),
                    )
                }

                Text(
                    text = "Похоже, теперь мы точно знаем, когда будут представлены видеокарты GeForce RTX 50.\n",
                    modifier = Modifier.padding(top = 20.dp),
                )

                AsyncImage(
                    model = "https://www.ixbt.com/img/n1/news/2024/9/1/Jensen-Huang-NVIDIA-1200x624_large.jpg",
                    contentDescription = "Дженсен Хуанг, главный исполнительный директор Nvidia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "\n" +
                            "Глава Nvidia Дженсен Хуанг заявил, что проведёт мероприятие на выставке CES 2025 6 января. Сама выставка будет доступна с 7 по 10 января.\n" +
                            "\n" +
                            "Хуанг не говорит о том, что именно будет представлено на этом мероприятии, но обычно на CES как раз анонсируют очень важные новинки. Да и все актуальные слухи говорят о том, что новые видеокарты будут представлены именно на CES 2025. К слову, относительно Radeon RX 8000 информация такая же.\n" +
                            "\n" +
                            "Предположительно, изначально нам покажут либо RTX 5090 и RTX 5080, либо ещё и RTX 5070 Ti. О последней пока вообще нет никаких данных, а первые две были описаны в следующей статье."
                )

                Card(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = "Инсайдер раскрыл параметры GeForce RTX 5090 и RTX 5080",
                        modifier = Modifier.padding(20.dp),
                        color = if (read) Color.Gray else Color.Blue
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(handler = object : MainScreenHandler {
        override fun onToOtherScreenClicked() {

        }

        override fun addLike() {

        }

        override fun addDislike() {

        }

        override fun onToShareClicked() {

        }

    })
}
