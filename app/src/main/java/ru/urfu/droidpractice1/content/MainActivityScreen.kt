@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.graphicsLayer
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.MainScreenHandler
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography


@Composable
fun MainActivityScreen(
    handler: MainScreenHandler,
    onLikePlus: () -> Unit = {},
    onLikeMinus: () -> Unit = {},
    read: Boolean = false,
    countLike: Int = 0
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
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title1),
                    modifier = Modifier.padding(top = 20.dp),
                    style = Typography.titleLarge
                )

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onLikePlus() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Icon(
                        modifier = Modifier
                            .padding(16.dp)
                            .graphicsLayer { rotationZ = 180f }
                            .clickable { onLikeMinus() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        text = countLike.toString(),
                        fontSize = 20.sp
                    )
                }

                Text(
                    text = stringResource(id = R.string.products1),
                    modifier = Modifier.padding(top = 12.dp)
                )

                AsyncImage(
                    model = stringResource(id = R.string.picture1),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = stringResource(id = R.string.recipe1)
                )

                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { handler.onToOtherScreenClicked() }
                ) {
                    Text(
                        text = stringResource (id = R.string.title2),
                        modifier = Modifier.padding(16.dp),
                        color = if (read) Color.Gray else Color.Black
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

        override fun onToShareClicked() {

        }

    })
}