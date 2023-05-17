package com.example.mygauth.presentation.auth.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.mygauth.core.Constants.AUTH_SCREEN

@Composable
fun AuthTopBar() {
  TopAppBar(
    title = {
      Text(text = AUTH_SCREEN)
    })
}