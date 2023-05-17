package com.example.mygauth.presentation.profile.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mygauth.core.Constants.PROFILE_SCREEN
import com.example.mygauth.core.Constants.REVOKE_ACCES
import com.example.mygauth.core.Constants.SIGN_OUT

@Composable
fun ProfileTopBar(
  signOut:()->Unit,
  revokeAccess:()->Unit
) {
  var openMenu by remember { mutableStateOf(false) }

  TopAppBar (
    title = { Text(text = PROFILE_SCREEN)},
    actions = {
      IconButton(onClick = { openMenu = !openMenu }) {
        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
      }
      DropdownMenu(expanded = openMenu, onDismissRequest = {
        openMenu = !openMenu}) {

        DropdownMenuItem(
          onClick = {
            signOut()
            openMenu = !openMenu
          }){Text(text = PROFILE_SCREEN)}

        DropdownMenuItem( onClick = {
          revokeAccess()
          openMenu = !openMenu
        }){ Text(text = REVOKE_ACCES)}
      }

    }
      )
}