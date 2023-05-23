package com.example.mygauth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.mygauth.navigation.NavGraph
import com.example.mygauth.navigation.Screen
import com.example.mygauth.presentation.auth.AuthViewModel
import com.example.mygauth.ui.theme.MyGAuthTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private lateinit var navHostController: NavHostController
  private val viewModel by viewModels<AuthViewModel>()
  @OptIn(ExperimentalAnimationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      navHostController = rememberAnimatedNavController()
      NavGraph(navHostController = navHostController)
      checkAuthState()
    }
  }

  private fun checkAuthState() {
    if(viewModel.isUserAuthenticated){
      navigateToProfileScreen()
    }
  }

  private fun navigateToProfileScreen()
  = navHostController.navigate(Screen.ProfileScreen.route)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  MyGAuthTheme {
    Greeting("Android")
  }
}