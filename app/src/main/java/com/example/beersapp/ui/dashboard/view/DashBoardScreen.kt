package com.example.beersapp.ui.dashboard.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.beersapp.data.network.model.PunkResponse
import com.example.beersapp.domain.common.Status
import com.example.beersapp.model.Routes
import com.example.beersapp.ui.dashboard.viewmodel.DashBoardViewModel

@Composable
fun DashBoardScreen(dashBoardViewModel: DashBoardViewModel, navController: NavController) {

    Column {
        SearchBar(dashBoardViewModel, navController)
    }
}

@Composable
fun SearchBar(dashBoardViewModel: DashBoardViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                dashBoardViewModel.search(query = searchQuery, 1, 80)
            },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, "Search Icon") },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colors.primary,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            )
        )

    }

    val searchResultsState by dashBoardViewModel.stateList.collectAsState()

    when (searchResultsState.responseType) {
        Status.LOADING -> {
            CircularProgressIndicator(modifier = Modifier.padding(horizontal = 16.dp))
        }

        Status.ERROR -> {
            val error = searchResultsState.error
            Text("Error: $error ", modifier = Modifier.padding(horizontal = 16.dp))
        }

        else -> {
            val results = searchResultsState.data ?: emptyList()
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(results) { beer ->
                    BeerItem(beer = beer) {
                        navController.navigate(
                            Routes.BeerDetail.createRoute(
                                beer.name,
                                beer.description,
                                beer.abv
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BeerItem(beer: PunkResponse, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = beer.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = beer.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
