// NavGraph.kt
package com.example.explorebostonapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object LocationList : Screen("locations/{category}") {
        fun createRoute(category: String) = "locations/$category"
    }
    object LocationDetail : Screen("detail/{category}/{locationId}") {
        fun createRoute(category: String, locationId: Int) = "detail/$category/$locationId"
    }
}


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartTour = {
                    navController.navigate(Screen.Categories.route)
                }
            )
        }


        composable(Screen.Categories.route) {
            CategoriesScreen(
                onCategoryClick = { category ->
                    navController.navigate(Screen.LocationList.createRoute(category))
                },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }


        composable(
            route = Screen.LocationList.route,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            LocationListScreen(
                category = category,
                onLocationClick = { locationId ->
                    navController.navigate(
                        Screen.LocationDetail.createRoute(category, locationId)
                    )
                },
                onNavigateBack = { navController.navigateUp() },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }


        composable(
            route = Screen.LocationDetail.route,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("locationId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val locationId = backStackEntry.arguments?.getInt("locationId") ?: 0
            LocationDetailScreen(
                category = category,
                locationId = locationId,
                onNavigateBack = { navController.navigateUp() },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}