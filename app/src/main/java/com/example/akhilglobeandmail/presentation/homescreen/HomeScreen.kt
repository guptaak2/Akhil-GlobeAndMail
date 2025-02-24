package com.example.akhilglobeandmail.presentation.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.akhilglobeandmail.R
import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostInfo
import com.example.akhilglobeandmail.presentation.viewmodel.GlobeAndMailPostsState

@Composable
fun HomeScreen(
    state: GlobeAndMailPostsState,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.errorMessage.isNotEmpty()) {
        Text(
            text = state.errorMessage,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn {
            itemsIndexed(state.postsList) { index, post ->
                GlobeAndMailPost(
                    post = post,
                    modifier = modifier.padding(10.dp)
                )
                if (index != state.postsList.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GlobeAndMailPost(
    post: GlobeAndMailPostInfo,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(post.image)
                .crossfade(true)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .build(),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "globe and mail post image",
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildAnnotatedString {
                append(post.title)
                if (post.isProtectionProduct) {
                    pushStyle(SpanStyle(color = Color.Red))
                    append(" X")
                }
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (post.body.isNotEmpty()) {
            Text(
                text = post.body,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (post.authors.isNotEmpty()) {
            Text(
                text = post.authors,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}