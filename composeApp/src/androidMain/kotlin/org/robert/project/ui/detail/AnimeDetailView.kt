package org.robert.project.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.robert.project.R
import org.robert.project.model.Character

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
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = animeDetails.bannerImage,
                            contentDescription = null,
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                style = MaterialTheme.typography.h5,
                                text = animeDetails.title
                            )

                            // Description
                            animeDetails.description?.let {
                                Column(modifier = Modifier.padding(top = 16.dp)) {
                                    Text(
                                        text = "Description",
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = HtmlCompat.fromHtml(
                                            it,
                                            HtmlCompat.FROM_HTML_MODE_COMPACT,
                                        ).toString()
                                    )
                                }
                            }

                            // Characters
                            animeDetails.character?.let {
                                Column(
                                    modifier = Modifier.padding(top = 16.dp)
                                ) {

                                    Text(
                                        text = "Character",
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )

                                    it.forEach { character ->
                                        CharacterListView(character = character)
                                    }
                                }
                            }
                        }

                    }
                }

            },
        )
    }

}

@Composable
fun CharacterListView(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(62.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                AsyncImage(
                    model = character.image, contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(
                        start = 8.dp, top = 8.dp
                    ),
                    text = character.name,
                    style = MaterialTheme.typography.body2,
                )
            }

            Row {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = character.voiceActor.name,
                        style = MaterialTheme.typography.body2,
                    )
                    Text(
                        text = character.voiceActor.language, style = MaterialTheme.typography.body2
                    )
                }
                AsyncImage(
                    modifier = Modifier.padding(start = 8.dp),
                    model = character.voiceActor.image,
                    contentDescription = null
                )
            }
        }
    }
}