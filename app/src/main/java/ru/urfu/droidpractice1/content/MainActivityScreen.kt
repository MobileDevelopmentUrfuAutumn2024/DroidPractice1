@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onToOtherScreenClicked: () -> Unit = {},
    onToShareClicked: () -> Unit = {},
    onToLiked: () -> Unit = {},
    onToDisliked: () -> Unit = {},
    read: Boolean = false,
    likes: Int = 0,
    dislikes: Int = 0

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
                            modifier = Modifier.clickable { onToShareClicked.invoke() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = null
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "Познавательные научно-популярные статьи", style = Typography.titleLarge, fontStyle = FontStyle.Italic,)

                AsyncImage(
                    model = "https://educon.by/images/facty.jpg",
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Row (
                    modifier = Modifier.padding(10.dp)
                )
                {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ){
                        Icon(
                            modifier = Modifier.clickable { onToLiked() },
                            painter = painterResource(id = R.drawable.like_svgrepo_com),
                            contentDescription = null
                        )
                        Text(
                            text = likes.toString(),
                            fontSize = 24.sp,
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier.clickable { onToDisliked() },
                            painter = painterResource(id = R.drawable.dislike_svgrepo_com),
                            contentDescription = null
                        )
                        Text(
                            text = dislikes.toString(),
                            fontSize = 24.sp,)
                    }
                }
                Text(
                    text = "\tФизика и математика очень интересные науки. Особенно это относится к физике, ведь она изучает окружающий нас мир, который ещё пока полон загадок и тайн. Познавать эти тайны и узнавать новые интересные факты о мире вокруг нас очень занимательно.",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 18.dp),
                )

                AsyncImage(
                    model = "https://i.pinimg.com/originals/84/b3/2f/84b32f6bc52da764fa2f72c2b55bdfc4.jpg",
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .padding(top = 16.dp)
                )

                Text(
                    text = "\tВ этом разделе собраны различные научные, интересные и познавательные факты, которые помогут убедиться в особенной занимательности науки, полюбить физику и математику, а также отвлечься и развеяться во время трудоемкой и зачастую скучной подготовки к экзаменам. Здесь сложные научные теории излагаются простым языком, понятным даже школьникам.",
                    modifier = Modifier.padding(top = 18.dp),
                    fontSize = 20.sp,
                )

                Card(
                   modifier = Modifier
                       .padding(16.dp)
                       .clickable { onToOtherScreenClicked.invoke() }
                ) {
                    Text(
                        text = "Как добиться того, чтобы вода перестала проводить электричество?",
                        modifier = Modifier.padding(18.dp),
                        fontSize = 20.sp,
                        color = if (read) Color.LightGray else Color.Blue
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}