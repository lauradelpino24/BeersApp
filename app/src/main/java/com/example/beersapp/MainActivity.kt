package com.example.beersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beersapp.model.Routes
import com.example.beersapp.ui.dashboard.view.DashBoardScreen
import com.example.beersapp.ui.dashboard.viewmodel.DashBoardViewModel
import com.example.beersapp.ui.details.BeerDetailsScreen
import com.example.beersapp.ui.login.view.LoginScreen
import com.example.beersapp.ui.login.viewmodel.LoginViewModel
import com.example.beersapp.ui.theme.BeersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val dashBoardViewModel: DashBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeersAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(loginViewModel, dashBoardViewModel)
                }
            }
        }
    }

}

@Composable
fun AppNavigation(loginViewModel: LoginViewModel, dashBoardViewModel: DashBoardViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(loginViewModel = loginViewModel, navController)
        }
        composable(Routes.DashBoard.route) {
            DashBoardScreen(dashBoardViewModel = dashBoardViewModel, navController)
        }

        composable(
            Routes.BeerDetail.route, arguments = listOf(
                navArgument("beerName") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("abv") { type = NavType.FloatType },

                )
        ) { backStackEntry ->
            val beerName = backStackEntry.arguments?.getString("beerName") ?: "beerName"
            val description = backStackEntry.arguments?.getString("description") ?: "description"
            val abv = backStackEntry.arguments?.getFloat("abv") ?: 0f


            BeerDetailsScreen(beerName = beerName, description = description, abv = abv)
        }
    }
}
