package com.example.mygauth.presentation.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mygauth.core.Constants.REVOKE_ACCESS_MESSAGE
import com.example.mygauth.core.Constants.SIGN_OUT
import com.example.mygauth.presentation.profile.components.ProfileContent
import com.example.mygauth.presentation.profile.components.ProfileTopBar
import com.example.mygauth.presentation.profile.components.RevokeAccess
import com.example.mygauth.presentation.profile.components.SignOut
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
  viewModel: ProfileViewModel = hiltViewModel(),
  navigateToAuthScreen:()->Unit
) {
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    topBar = {
      ProfileTopBar(signOut = { viewModel.signOut() }
        , revokeAccess ={viewModel.revokeAccess()})
    },
   content = {padding->
     ProfileContent(padding = padding
       , displayName = viewModel.displayName
       , photoUrl = viewModel.photoUrl)
   },
    scaffoldState = scaffoldState
  )

  SignOut(navigateToAuthScreen = {signedOut ->
    if(signedOut){
      navigateToAuthScreen()
    }
  })

  fun showSnackBar() = coroutineScope.launch {
    val result = scaffoldState.snackbarHostState.showSnackbar(
      message = REVOKE_ACCESS_MESSAGE,
      actionLabel = SIGN_OUT
    )
    if(result == SnackbarResult.ActionPerformed){
      viewModel.signOut()
    }
  }

  RevokeAccess(navigateToAuthScreen = {accessRevoked ->
    if(accessRevoked){
      navigateToAuthScreen()
    }
  },
    showSnackBar = {showSnackBar()}
  )
}