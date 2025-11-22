package dev.esan.pc02moviles2220014222200168.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.esan.pc02moviles2220014222200168.presentation.screens.ListadoScreen
import dev.esan.pc02moviles2220014222200168.presentation.screens.RegistroScreen

@Composable
fun AppNavGraph(){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "registro"){
        composable("registro") { RegistroScreen(navController) }
        composable ("listado") { ListadoScreen(navController) }

    }
}

