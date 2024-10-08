@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    onToOtherScreenClicked: () -> Unit = {},
    onToShareClicked:       () -> Unit = {},
    onLikePlus:             () -> Unit = {},
    onLikeMinus:            () -> Unit = {},
    read:           Boolean = false,
    counterOfLikes: Int     = 0
) {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                Text(
                    text = stringResource(id = R.string.article_title)
                )
            }
            )
        { innerPadding ->
            Column (
                modifier = Modifier.verticalScroll(rememberScrollState())
                                   .padding(innerPadding)
                                   .padding(16.dp)
            ) {
                Row {

                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = counterOfLikes.toString(),
                        fontSize = 20.sp
                    )
                }

                Row {
                    Column {
                        Icon(
                            modifier = Modifier.padding(horizontal = 8.dp)
                                .clickable { onLikePlus() },
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }

                    Column {
                        Icon(
                            modifier = Modifier.padding(horizontal = 8.dp)
                                .clickable { onLikeMinus() },
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                }

                Icon(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .clickable { onToShareClicked() },
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.first_title_h1),
                    style = Typography.titleLarge
                )

                Text(
                    text = stringResource(id = R.string.first_text_a),
                    modifier = Modifier.padding(vertical = 16.dp)
                )


                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.first_text_b),
                    style = Typography.bodyLarge
                )

                Card(
                    modifier = Modifier.padding(top = 16.dp)
                                       .clickable { onToOtherScreenClicked() }
                ) {
                    Text(
                        text = stringResource(id = R.string.second_title_h1),
                        modifier = Modifier.padding(16.dp),
                        color = if (read) Color.Red else Color.Gray
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