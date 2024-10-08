@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Grayscale0
import ru.urfu.droidpractice1.ui.theme.Grayscale200
import ru.urfu.droidpractice1.ui.theme.Red400

@Composable
fun MainActivityScreen(
    onToOtherScreenClicked: () -> Unit = {},
    onToShareClicked: () -> Unit = {},
    onIncrementLike: () -> Unit = {},
    onDecrementLike: () -> Unit = {},
    isStateRead: Boolean = false,
    likeCount: Int = 0
) {

    DroidPractice1Theme {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Grayscale0), topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Grayscale200)
                .border(width = 1.dp, color = Grayscale200), title = {
                Column {
                    Text(
                        fontSize = 24.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.W500,
                        text = stringResource(id = R.string.article_title)
                    )

                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        text = stringResource(id = R.string.article_description)
                    )
                }
            }, actions = {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onToShareClicked() },
                    painter = painterResource(id = R.drawable.share_variant_icon),
                    contentDescription = null
                )
            })
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 32.dp),
                        text = stringResource(id = R.string.hero_title),
                    )
                }

                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(id = R.string.hero_description),
                )

                AsyncImage(
                    model = stringResource(id = R.string.hero_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentDescription = stringResource(id = R.string.hero_image_descr),
                )

                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(bottom = 32.dp),
                    text = stringResource(id = R.string.hero_description_second),
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = stringResource(id = R.string.rating_title),
                    )

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .clickable { onIncrementLike() },
                            painter = painterResource(id = R.drawable.thumb_up_icon),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(16.dp))


                        Icon(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .clickable { onDecrementLike() },
                            painter = painterResource(id = R.drawable.thumb_down_icon),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400,
                            modifier = Modifier.padding(bottom = 16.dp),
                            text = likeCount.toString(),
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.footer_title),
                    )

                    Row(
                        modifier = Modifier
                            .padding(32.dp)
                            .background(if (isStateRead) Grayscale200 else Red400)
                            .clip(RoundedCornerShape(16.dp)),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            color = Grayscale0,
                            textAlign = TextAlign.Center,
                            text = stringResource(id = if (isStateRead) R.string.footer_description_readed else R.string.footer_description),
                        )
                    }

                    TextButton(
                        onClick = onToOtherScreenClicked, modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = stringResource(id = R.string.footer_link))
                    }
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