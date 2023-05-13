package com.example.mygauth.data.repository

import com.example.mygauth.core.Constants.USERS
import com.example.mygauth.domain.model.Response.Failure
import com.example.mygauth.domain.model.Response.Success
import com.example.mygauth.domain.repository.ProfileRepository
import com.example.mygauth.domain.repository.RevokeAccessResponse
import com.example.mygauth.domain.repository.SignOutResponse
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
  private val auth: FirebaseAuth,
  private val oneTapClient:SignInClient,
  private val signInClient: GoogleSignInClient,
  private val db:FirebaseFirestore
):ProfileRepository {
  override val displayName: String
    get() = auth.currentUser?.displayName.toString()
  override val photoUrl: String
    get() = auth.currentUser?.photoUrl.toString()

  override suspend fun signOut(): SignOutResponse {
    return try{
      oneTapClient.signOut().await()
      auth.signOut()
      Success(true)
    }catch (e:Exception){
      Failure(e)
    }
  }

  override suspend fun revokeAccess(): RevokeAccessResponse {
    return try{
      auth.currentUser?.apply {
        db.collection(USERS).document(uid).delete().await()
        signInClient.revokeAccess().await()
        oneTapClient.signOut().await()
        delete().await()
      }
      Success(true)
    }catch (e:Exception){
      Failure(e)
    }
  }
}