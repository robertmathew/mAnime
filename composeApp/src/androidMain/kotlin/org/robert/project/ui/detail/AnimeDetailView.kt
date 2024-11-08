package org.robert.project.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.robert.project.R

@Composable
@Preview
fun AnimeDetailView(
    animeId: Int?, navController: NavController, viewModel: AnimeDetailViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    viewModel.fetchPopularAnime(animeId!!)

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back",
                            )
                        }
                    },
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                )
            },
            content = { paddingValues ->
                uiState.animeDetail?.let { animeDetails ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            AsyncImage(
                                modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                                model = animeDetails.coverImage,
                                contentDescription = null,
                            )
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                style = MaterialTheme.typography.h5,
                                text = animeDetails.title
                            )
                            animeDetails.description?.let {
                                Column(modifier = Modifier.padding(top = 16.dp)) {
                                    Text(text = "Description", style = MaterialTheme.typography.h6)
                                    Text(
                                        text = HtmlCompat.fromHtml(
                                            it,
                                            HtmlCompat.FROM_HTML_MODE_COMPACT,
                                        ).toString()
                                    )
                                }
                            }
                        }
                    }
                }

            },
        )
    }

}