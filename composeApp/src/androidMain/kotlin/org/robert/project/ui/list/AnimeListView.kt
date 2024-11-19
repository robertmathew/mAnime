package org.robert.project.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.robert.project.R
import org.robert.project.ui.Screen

@Composable
@Preview
fun AnimeListView(navController: NavController, viewModel: AnimeListViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                )
            },
            content = { paddingValues ->
                AnimeListViewCompose(
                    modifier = Modifier.padding(paddingValues),
                    uiState = uiState,
                    navController = navController
                )
            },
        )
    }

}

@Composable
fun AnimeListViewCompose(
    modifier: Modifier, uiState: AnimeListUiState, navController: NavController
) {
    uiState.popularAnimeList?.let { animeList ->
        LazyVerticalGrid(
            modifier = modifier.padding(8.dp),
            columns = GridCells.Fixed(3),
            content = {
                items(animeList) { animeData ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(Screen.DetailScreen.withIdArgs(animeData?.id))
                            },
                    ) {
                        animeData?.coverImage?.let {
                            AsyncImage(
                                modifier = Modifier.clip(shape = RoundedCornerShape(8.dp)),
                                model = it,
                                contentDescription = null,
                            )
                        }
                        animeData?.title?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1,
                                maxLines = 1,
                            )
                        }
                    }
                }
            },
        )
    }
}
