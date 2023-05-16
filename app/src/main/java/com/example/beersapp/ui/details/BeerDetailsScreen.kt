package com.example.beersapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun BeerDetailsScreen(
    beerName: String,
    description: String,
    abv: Float,
    imageUrl: String
    ) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = beerName,
            modifier = Modifier.size(200.dp)
        )
        Text(text = beerName, style = MaterialTheme.typography.h4)
        Text(text = description, style = MaterialTheme.typography.body1)
        Text(text = "Grados: $abv", style = MaterialTheme.typography.body1)
    }
}