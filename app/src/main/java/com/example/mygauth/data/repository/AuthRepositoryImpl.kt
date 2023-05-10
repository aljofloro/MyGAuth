package com.example.mygauth.data.repository


import com.example.mygauth.domain.model.Response.Success
import com.example.mygauth.domain.model.Response.Failure
import com.example.mygauth.core.Constants.SIGN_IN_REQUEST
import com.example.mygauth.core.Constants.SIGN_UP_REQUEST
import com.example.mygauth.domain.repository.AuthRepository
import com.example.mygauth.domain.repository.OneTapSignInResponse
import com.example.mygauth.domain.repository.SignInWithGoogleResponse
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
  private val auth : FirebaseAuth,
  private var oneTapClient : SignInClient,
  @Named(SIGN_IN_REQUEST)
  private var signInRequest : BeginSignInRequest,
  @Named(SIGN_UP_REQUEST)
  private var signUpRequest : BeginSignInRequest,
  private val db: FirebaseFirestore
): AuthRepository {
  override val isUserAuthenticatedInFirebase = auth.currentUser != null

  override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse{
    return try{
      val signInResult = oneTapClient.beginSignIn(signInRequest).await()
      Success(signInResult)
    }catch (e:Exception){
      try{
        val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
        Success(signUpResult)
      }catch (e:Exception){
        Failure(e)
      }
    }
  }

  override suspend fun firebaseSignInWithGoogle
        (googleCredential: AuthCredential): SignInWithGoogleResponse {
    return try{
      val authResult = auth.signInWithCredential(googleCredential).await()
      val isNewUser = authResult.additionalUserInfo?.isNewUser?:false
      if(isNewUser){
        //TODO: Agregar usuario a Firestore
      }
      Success(true)
    }catch (e:Exception){
      Failure(e)
    }
  }
}