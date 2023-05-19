package com.example.mygauth.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.mygauth.presentation.auth.AuthScreen
import com.example.mygauth.presentation.profile.ProfileScreen
import com.example.mygauth.navigation.Screen.AuthScreen
import com.example.mygauth.navigation.Screen.ProfileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
  navHostController: NavHostController
) {
  AnimatedNavHost(
          navController = navHostController,
          startDestination = AuthScreen.route,
          enterTransition = { EnterTransition.None},
          exitTransition = { ExitTransition.None}){
    composable(
      route = AuthScreen.route
    ){

      AuthScreen(
      navigateToProfileScreen = {navHostController.navigate(ProfileScreen.route)}
      )
    }
    composable(route = ProfileScreen.route){

      ProfileScreen (
        navigateToAuthScreen = {
          navHostController.popBackStack()
          navHostController.navigate(AuthScreen.route)}
          )
      }
    }
  }
