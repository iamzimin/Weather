package com.evg.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evg.resource.theme.WeatherTheme
import com.evg.welcome.presentation.WelcomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //TODO
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val sharedPreferences = LocalContext.current.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
    val latestCity = sharedPreferences.getInt("latestCity", -1)

    CompositionLocalProvider(/*LocalNavHostController provides navController*/) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = "welcome"
            ) {
                composable("welcome") {
                    if (latestCity != -1) {
                        WelcomeScreen()
                    } else {
                        WelcomeScreen()
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainScreenPreview() {
    WeatherTheme {
        MainScreen()
    }
}