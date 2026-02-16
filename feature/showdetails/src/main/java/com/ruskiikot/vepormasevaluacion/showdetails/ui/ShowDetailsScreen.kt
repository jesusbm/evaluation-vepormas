package com.ruskiikot.vepormasevaluacion.showdetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails
import com.ruskiikot.vepormasevaluacion.showdetails.presentation.ShowDetailsIntent
import com.ruskiikot.vepormasevaluacion.showdetails.presentation.ShowDetailsViewModel

@Composable
fun ShowDetailsScreen(
    viewModel: ShowDetailsViewModel = hiltViewModel(),
    showId: Long,
    isLandscape: Boolean,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ShowDetailsScreenContent(
        uiState.showDetails,
        isLandscape,
    )
    LaunchedEffect(Unit) {
        viewModel.sendIntent(ShowDetailsIntent.LoadShowDetails(showId))
    }
}

@Composable
private fun ShowDetailsScreenContent(
    showDetails: ShowDetails,
    isLandscape: Boolean,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .padding(vertical = 6.dp),
        ) {
            if (isLandscape) {
                LandscapeLayout(
                    showDetails = showDetails,
                )
            } else {
                PortraitLayout(
                    showDetails = showDetails,
                )
            }
        }
    }
}

@Composable
private fun PortraitLayout(
    showDetails: ShowDetails,
) {
    Column(
        modifier = Modifier
            .background(Color.Transparent),
    ) {
        ShowImage(
            imageUrl = showDetails.imageMediumUrl ?: "",
            modifier = Modifier
                .fillMaxWidth(),
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp),
        ) {
            ShowBasicInfo(
                showDetails = showDetails,
                modifier = Modifier.padding(top = 12.dp),
            )
            ShowSummary(
                summary = showDetails.summary,
                modifier = Modifier.padding(top = 12.dp),
            )
            ShowGenres(
                genres = showDetails.genres,
                modifier = Modifier.padding(top = 12.dp),
            )
            ShowSchedule(
                scheduleTime = showDetails.scheduleTime,
                scheduleDays = showDetails.scheduleDays,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}

@Composable
private fun LandscapeLayout(
    showDetails: ShowDetails,
) {
    Row {
        Column {
            ShowImage(
                imageUrl = showDetails.imageMediumUrl ?: "",
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            ShowGenres(showDetails.genres)
            ShowSchedule(showDetails.scheduleTime, showDetails.scheduleDays)
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp),
        ) {
            ShowBasicInfo(
                showDetails = showDetails,
                modifier = Modifier.height(128.dp),
            )
            ShowSummary(showDetails.summary)
        }
    }
}

@Composable
private fun ShowBasicInfo(
    showDetails: ShowDetails,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            showDetails.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.1).sp
        )
        Text(showDetails.networkName, fontSize = 14.sp, fontStyle = FontStyle.Italic)
        Row {
            Text(
                "rating: ",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.1).sp
            )
            Text(
                showDetails.averageRating.ifEmpty { " - " },
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.1).sp
            )
        }
    }
}

@Composable
private fun ShowImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "show image",
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
        loading = {
            ImagePlaceholder(modifier)
        },
        error = {
            ImagePlaceholder(modifier)
        },
    )
}

@Composable
private fun ShowSummary(
    summary: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SectionTitle(
            title = "Sinópsis: ",
            modifier = Modifier.padding(bottom = 2.dp),
        )
        Text(
            text = summary,
            letterSpacing = -(0.1).sp,
        )
    }
}

@Composable
private fun ShowGenres(
    genres: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        SectionTitle("Géneros: ")
        Text(
            text = genres.joinToString().ifEmpty { " - " },
            letterSpacing = -(0.1).sp,
        )
    }
}

@Composable
private fun ShowSchedule(
    scheduleTime: String,
    scheduleDays: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        SectionTitle("Horario: ")
        Text(
            text = "$scheduleTime | ${scheduleDays.joinToString()}",
            letterSpacing = -(0.1).sp,
        )
    }
}

@Composable
private fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = -(0.1).sp,
    )
}

@Composable
private fun ImagePlaceholder(modifier: Modifier) {
    Spacer(
        modifier = modifier
            .width(100.dp)
            .aspectRatio(1f)
            .background(color = Color.LightGray)
    )
}

@Preview
@Composable
private fun MyPreview1() {
    ShowDetailsScreenContent(
        ShowDetails(
            id = 0,
            name = "The Simpsons",
            imageMediumUrl = "",
            networkName = "Disney",
            averageRating = "5.0",
            summary = "Duh! ".repeat(100),
            genres = listOf("Comedy", "Drama", "Terror"),
            scheduleTime = "20:00",
            scheduleDays = listOf("Tuesday", "Thursday"),
        ),
        false,
    )
}

@Preview(
    widthDp = 800,
    heightDp = 360,
)
@Composable
private fun MyPreview2() {
    ShowDetailsScreenContent(
        ShowDetails(
            id = 0,
            name = "The Simpsons",
            imageMediumUrl = "",
            networkName = "Disney",
            averageRating = "5.0",
            summary = "Duh! ".repeat(50),
            genres = listOf("Comedy", "Drama", "Terror"),
            scheduleTime = "20:00",
            scheduleDays = listOf("Tuesday", "Thursday"),
        ),
        true,
    )
}
