package com.example.szakdolgozat.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.szakdolgozat.R
import com.example.szakdolgozat.data.model.Game
import com.example.szakdolgozat.screens.main.GameCard
import com.example.szakdolgozat.ui.theme.SzakdolgozatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    toBack: () -> Unit
) {
    val viewModel: GameDetailViewModel = viewModel()
    val gameDetail by viewModel.gameDetail.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cím") },
                navigationIcon = {
                    IconButton(onClick = toBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        gameDetail?.let { game ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .padding(paddingValues)
            ) {
                AsyncImage(
                    model = game.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Column {
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(id = R.string.game_genre, game.genre))
                    Text(text = stringResource(id = R.string.game_platform, game.platform))
                    Text(text = stringResource(id = R.string.game_publisher, game.publisher))
                    Text(text = stringResource(id = R.string.game_release_date, game.releaseDate))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = game.shortDescription,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}