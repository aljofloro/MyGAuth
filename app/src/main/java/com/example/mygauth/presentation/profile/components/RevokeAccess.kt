package com.example.mygauth.presentation.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.mygauth.components.ProgressBar
import com.example.mygauth.domain.model.Response.Loading
import com.example.mygauth.domain.model.Response.Success
import com.example.mygauth.domain.model.Response.Failure
import com.example.mygauth.presentation.profile.ProfileViewModel

@Composable
fun RevokeAccess (
  viewModel: ProfileViewModel = hiltViewModel(),
  navigateToAuthScreen: (accessRevoked: Boolean) ->Unit,
  showSnackBar:()->Unit
) {
  when(val revokeAccessResponse = viewModel.revokeAccessResponse){
    is Loading -> ProgressBar()
    is Success -> revokeAccessResponse.data?.let{accessRevoked->
      LaunchedEffect(accessRevoked){
        navigateToAuthScreen(accessRevoked)
      }
    }
    is Failure -> LaunchedEffect(Unit){
      print(revokeAccessResponse.e)
      showSnackBar()
    }
  }
}