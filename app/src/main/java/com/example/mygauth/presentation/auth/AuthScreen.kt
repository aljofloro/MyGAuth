package com.example.mygauth.presentation.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygauth.presentation.auth.components.AuthContent
import com.example.mygauth.presentation.auth.components.AuthTopBar
import com.example.mygauth.presentation.auth.components.OneTapSignIn
import com.example.mygauth.presentation.auth.components.SignInWithGoogle
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.GoogleAuthProvider.getCredential

@Composable
fun AuthScreen(
  viewModel: AuthViewModel = hiltViewModel(),
  navigateToProfileScreen: () ->Unit
) {
  Scaffold(
    topBar = { AuthTopBar() },
    content = { padding ->
      AuthContent(padding = padding,
      oneTapSignIn = {viewModel.oneTapSignIn()})
    }
  )

  val launcher = rememberLauncherForActivityResult(ActivityResultContracts
    .StartIntentSenderForResult()){result ->
    if(result.resultCode == RESULT_OK){
      try{
        val credentials = viewModel.oneTapClient
          .getSignInCredentialFromIntent((result.data))
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = getCredential(googleIdToken,null)
        viewModel.signInWithGoogle(googleCredentials)
      }catch (e:Exception){
        print(e)
      }
    }
  }

  fun launch(signInResult: BeginSignInResult){
    val intent = IntentSenderRequest
      .Builder(signInResult.pendingIntent.intentSender).build()
    launcher.launch(intent)
  }

  OneTapSignIn(launch = {launch(it)})

  SignInWithGoogle(navigateToHomeScreen = { signedIn ->
    if(signedIn){ navigateToProfileScreen()}
  })
}