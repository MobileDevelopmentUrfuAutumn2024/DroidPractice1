@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun MainActivityScreen() {
    DroidPractice1Theme {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(Grayscale0), topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                    modifier = Modifier.padding(horizontal = 16.dp),
                    painter = painterResource(id = R.drawable.arrow_back),
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
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = stringResource(id = R.string.hero_image_descr),
                )

                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    text = stringResource(id = R.string.hero_description_second),
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}