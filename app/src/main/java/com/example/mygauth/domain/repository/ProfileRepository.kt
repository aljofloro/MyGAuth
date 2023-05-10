package com.example.mygauth.domain.repository

import com.example.mygauth.domain.model.Response

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
  val displayName: String
  val photoUrl: String

  suspend fun signOut(): SignOutResponse

  suspend fun revokeAccess(): RevokeAccessResponse
}