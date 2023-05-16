package com.example.mygauth.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygauth.components.ProgressBar
import com.example.mygauth.domain.model.Response.Success
import com.example.mygauth.domain.model.Response.Failure
import com.example.mygauth.domain.model.Response.Loading
import com.example.mygauth.domain.repository.SignInWithGoogleResponse
import com.example.mygauth.presentation.auth.AuthViewModel

@Composable
fun SignInWithGoogle(
  viewModel: AuthViewModel = hiltViewModel(),
  navigateToHomeScreen:(signedIn:Boolean)->Unit
) {
  when(val signInWithGoogleResponse = viewModel.signInWithGoogleResponse){
    is Loading -> ProgressBar()
    is Success -> signInWithGoogleResponse.data?.let{ signedIn ->
      LaunchedEffect(signedIn){
        navigateToHomeScreen(signedIn)
      }
    }
    is Failure -> LaunchedEffect(Unit){
      print(signInWithGoogleResponse.e)
    }
  }
}