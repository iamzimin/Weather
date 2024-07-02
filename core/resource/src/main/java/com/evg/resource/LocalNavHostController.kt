package com.evg.resource

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    error("NavController not provided")
}