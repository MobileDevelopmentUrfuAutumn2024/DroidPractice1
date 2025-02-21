@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.domain.ActivityDomain
import ru.urfu.droidpractice1.processor.MainActivityProcessor
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import ru.urfu.droidpractice1.ui.theme.Typography

@Composable
fun MainActivityScreen(
    mainActivityProcessor: MainActivityProcessor,
    activityDomain: ActivityDomain
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
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.main_padding))
                                .clickable { mainActivityProcessor.onShareClick() },
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = stringResource(id = R.string.like_button_description)
                        )
                    }
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(dimensionResource(id = R.dimen.main_padding))
            ) {
                Text(
                    text = stringResource(id = R.string.article_header),
                    style = Typography.titleLarge
                )

                Row {
                    Icon(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.main_padding))
                            .clickable { mainActivityProcessor.onDislikeButtonClick() },
                        painter = painterResource(id = R.drawable.dislike),
                        contentDescription = stringResource(id = R.string.dislike_button_description)
                    )
                    Icon(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.main_padding))
                            .clickable { mainActivityProcessor.onLikeButtonClick() },
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = stringResource(id = R.string.like_button_description)
                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = dimensionResource(id = R.dimen.main_padding)),
                        text = activityDomain.likes.toString(),
                        fontSize = 22.sp
                    )
                }

                Text(
                    text = stringResource(id = R.string.first_part),
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.main_padding)),
                    style = Typography.bodyLarge
                )

                AsyncImage(
                    model = stringResource(id = R.string.article_picture_link),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.photo_round))),
                    contentDescription = stringResource(id = R.string.article_picture_link_description)
                )

                Text(
                    text = stringResource(id = R.string.second_part),
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.main_padding)),
                    style = Typography.bodyLarge
                )

                Text(
                    text = stringResource(id = R.string.third_part),
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.main_padding)),
                    style = Typography.bodyLarge
                )

                Card(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.main_padding))
                        .clickable { mainActivityProcessor.onArticleLinkClick() }
                ) {
                    Text(
                        text = stringResource(id = R.string.link_article_header),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.main_padding)),
                        color = if (activityDomain.read) Color.Gray else Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen(mainActivityProcessor = object : MainActivityProcessor {
        override fun onArticleLinkClick() {
            TODO("Not yet implemented")
        }

        override fun onShareClick() {
            TODO("Not yet implemented")
        }

        override fun onLikeButtonClick() {
            TODO("Not yet implemented")
        }

        override fun onDislikeButtonClick() {
            TODO("Not yet implemented")
        }
    }, ActivityDomain())
}