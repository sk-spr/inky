package com.skyesprung.inky.view

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skyesprung.inky.R
import com.skyesprung.inky.viewmodel.AppListScreenViewModel

@Preview(showBackground = true)
@Composable
fun AppListScreen() {
    val context = LocalContext.current;
    val packageManager: PackageManager = context.packageManager;
    val viewModel: AppListScreenViewModel = viewModel()
    val apps by viewModel.apps.observeAsState(initial= emptyList())
    LaunchedEffect(true) {
        viewModel.loadApps(packageManager)
    }
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (apps.isEmpty()) {
            Text(
                stringResource(R.string.apps_loading),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
        } else {
            AppList(apps)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListPreview() {
    AppList(LocalContext.current.packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
}

@Composable
fun AppList(apps: List<ApplicationInfo>) {
    for (app in apps) {
        AppListEntry(app)
    }
}

@Composable
fun AppListEntry(app: ApplicationInfo) {
    Row {
        Text(app.splitNames.contentToString())
    }
}
