package com.example.mygauth.navigation

import com.example.mygauth.core.Constants.AUTH_SCREEN
import com.example.mygauth.core.Constants.PROFILE_SCREEN

sealed class Screen (val route:String){
  object AuthScreen:Screen(AUTH_SCREEN)
  object ProfileScreen:Screen(PROFILE_SCREEN)
}