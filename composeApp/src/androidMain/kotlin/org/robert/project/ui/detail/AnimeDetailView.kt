package org.robert.project.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalMaterial3Api::class)
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
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = "back",
                            )
                        }
                    },
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                )
            },
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
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
                                style = MaterialTheme.typography.headlineMedium,
                                text = animeDetails.title
                            )

                            // Description
                            animeDetails.description?.let {
                                Column(modifier = Modifier.padding(top = 16.dp)) {
                                    Text(
                                        text = "Description",
                                        style = MaterialTheme.typography.titleSmall,
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
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                AsyncImage(
                    model = character.image, contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(
                        start = 8.dp, top = 8.dp,
                    )
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = character.role,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            Row {
                Column(
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = character.voiceActor.name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = character.voiceActor.language,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                AsyncImage(
                    model = character.voiceActor.image,
                    contentDescription = null,
                )
            }
        }
    }
}