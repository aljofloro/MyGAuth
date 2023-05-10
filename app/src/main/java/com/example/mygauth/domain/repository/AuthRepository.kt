package com.example.mygauth.domain.repository

import com.example.mygauth.domain.model.Response
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepository {
  val isUserAuthenticatedInFirebase: Boolean

  suspend fun oneTapSignInWithGoogle():OneTapSignInResponse

  suspend fun firebaseSignInWithGoogle
        (googleCredential: AuthCredential):SignInWithGoogleResponse
}