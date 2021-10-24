package com.palatin.ihy.components

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

/**
 * Resolve permissions by launching [LaunchedEffect] with the key by [permissions]
 * invokes [result] with the related [PermissionsStatus]
 */
@Composable
fun PermissionResolver(
    permissions: Set<String>,
    result: (PermissionsStatus) -> Unit
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { res ->
            if (permissions.all { res[it] == true })
                result(PermissionsStatus.Granted)
            else {
                result(PermissionsStatus.Denied)
            }
        })

    LaunchedEffect(key1 = permissions) {
        if (permissions.all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }) {
            result(PermissionsStatus.WereGranted)
        }
        else {
            permissionLauncher.launch(permissions.toTypedArray())
        }
    }
}

/**
 * Represents status of permissions passed to [PermissionResolver]
 */
sealed class PermissionsStatus {

    /**
     * Permissions were already granted
     */
    object WereGranted : PermissionsStatus()

    /**
     * Granted by user
     */
    object Granted : PermissionsStatus()

    /**
     * At least one of permissions denied
     */
    object Denied : PermissionsStatus()
}