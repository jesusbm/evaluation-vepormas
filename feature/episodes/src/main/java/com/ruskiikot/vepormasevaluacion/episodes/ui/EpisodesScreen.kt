package com.ruskiikot.vepormasevaluacion.episodes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.domain.model.ShowSummary
import com.ruskiikot.vepormasevaluacion.episodes.R
import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesIntent
import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesUiState
import com.ruskiikot.vepormasevaluacion.episodes.presentation.EpisodesViewModel
import com.ruskiikot.vepormasevaluacion.episodes.presentation.LoadingStatusEnum

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = hiltViewModel(),
    onEpisodeItemClick: (idShow: Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EpisodesScreenContent(
        uiState = uiState,
        onEpisodeItemClick = { onEpisodeItemClick(it) },
        onEpisodeItemLongClick = { viewModel.sendIntent(EpisodesIntent.RequestDeleteItemIntent(it)) },
        onDismissDeleteDialog = { viewModel.sendIntent(EpisodesIntent.CancelDeleteItemIntent) },
        onConfirmDeleteDialog = { viewModel.sendIntent(EpisodesIntent.ConfirmDeleteItemIntent) },
    )
    LaunchedEffect(Unit) {
        viewModel.sendIntent(EpisodesIntent.LoadEpisodesIntent)
    }
}

@Composable
private fun EpisodesScreenContent(
    uiState: EpisodesUiState,
    onEpisodeItemClick: (idShow: Long) -> Unit,
    onEpisodeItemLongClick: (idShow: Long) -> Unit,
    onDismissDeleteDialog: () -> Unit,
    onConfirmDeleteDialog: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (uiState.loadingState) {
            LoadingStatusEnum.INITIALIZED -> Text(
                modifier = Modifier,
                color = Color.Red,
                text = uiState.loadingState.name,
            )

            LoadingStatusEnum.LOADING -> {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.Gray,
                    text = uiState.loadingState.name,
                )
            }

            LoadingStatusEnum.LOADED -> {
                EpisodesListing(
                    episodes = uiState.episodes,
                    onEpisodeItemClick = onEpisodeItemClick,
                    onEpisodeItemLongClick = onEpisodeItemLongClick,
                )
            }
        }
        if (uiState.isConfirmationDialogOpen) {
            DeleteConfirmationDialog(
                onDismissRequest = onDismissDeleteDialog,
                onConfirmRequest = onConfirmDeleteDialog,
            )
        }
    }
}

@Composable
private fun EpisodesListing(
    episodes: List<Episode>,
    onEpisodeItemClick: (Long) -> Unit,
    onEpisodeItemLongClick: (idShow: Long) -> Unit,
) {
    LazyColumn(
        content = {
            items(episodes, key = { it.id }) { item ->
                EpisodeItem(
                    episode = item,
                    onClick = onEpisodeItemClick,
                    onLongClick = onEpisodeItemLongClick,
                    modifier = Modifier.animateItem()
                )
            }
        }
    )
}

@Composable
private fun EpisodeItem(
    episode: Episode,
    onClick: (Long) -> Unit,
    onLongClick: (idShow: Long) -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .padding(top = 4.dp)
            .background(color = Color(0xFFEAEAEA))
            .combinedClickable(
                onClick = { onClick(episode.show.id) },
                onLongClick = { onLongClick(episode.show.id) },
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f),
        ) {
            Text(
                episode.show.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.1).sp
            )
            if (episode.show.networkName.isNotEmpty()) {
                Text(episode.show.networkName, fontSize = 14.sp, fontStyle = FontStyle.Italic)
            }
            Row {
                Text(
                    episode.airTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = (-0.1).sp
                )
                Text(" | ", fontSize = 18.sp, letterSpacing = (-0.1).sp)
                Text(
                    episode.airDate,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (-0.1).sp
                )
            }
        }
        if (episode.show.imageMediumUrl.isNotEmpty()) {
            AsyncImage(
                model = episode.show.imageMediumUrl,
                contentDescription = "show image",
                modifier = Modifier
                    .height(128.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .border(
                        width = 3.dp,
                        color = Color(0xFFC9DF0A),
                        shape = RoundedCornerShape(12.dp)
                    ),
            )
        } else {
            Spacer(
                modifier = Modifier
                    .height(128.dp)
                    .width(91.dp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeleteConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
) {
    AlertDialog(
        title = { Text(text = stringResource(R.string.dialog_confirm_title)) },
        text = { Text(text = stringResource(R.string.dialog_confirm_message)) },
        confirmButton = {
            TextButton(
                onClick = onConfirmRequest,
            ) {
                Text(text = stringResource(R.string.dialog_confirm_button_ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
            ) {
                Text(text = stringResource(R.string.dialog_confirm_button_cancel))
            }
        },
        onDismissRequest = onDismissRequest,
    )
}

@Preview
@Composable
private fun MyPreview1() {
    EpisodeItem(
        Episode(
            id = 1,
            airDate = "2025-10-31",
            airTime = "20:00",
            show = ShowSummary(
                id = 11,
                name = "Mi Novela",
                imageMediumUrl = "https://static.wikia.nocookie.net/doblaje/images/9/9f/GarfieldCharacter.jpg/revision/latest/scale-to-width-down/1000?cb=20220926013827&path-prefix=es",
                networkName = "TV Azteca"
            ),
        ),
        {},
        {},
        Modifier,
    )
}

@Preview
@Composable
private fun MyPreview2() {
    DeleteConfirmationDialog({}, {})
}
