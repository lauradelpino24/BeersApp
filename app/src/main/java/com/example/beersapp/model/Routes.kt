package com.example.beersapp.model

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object DashBoard : Routes("dashboard")
    object BeerDetail : Routes("beerDetail/{beerName}/{description}/{abv}/{imageUrl}") {
        fun createRoute(
            beerName: String,
            description: String,
            abv: Float,
            imageUrl: String
            ): String {
            return "beerDetail/$beerName/$description/$abv/$imageUrl"
        }
    }
}