package com.skyesprung.inky.view

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.text.format.DateUtils
import android.widget.ImageView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.skyesprung.inky.R
import com.skyesprung.inky.viewmodel.AppListScreenViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Preview(showBackground = true)
@Composable
fun AppListScreen() {
    val context = LocalContext.current;
    val packageManager: PackageManager = context.packageManager;
    val viewModel: AppListScreenViewModel = viewModel()
    val apps by viewModel.apps.observeAsState(initial= emptyList())
    LaunchedEffect(true) {
        viewModel.loadApps(packageManager)
        while(true)
        {
            viewModel.syncTime()
            delay(1000)
        }
    }
    val time by viewModel.time.observeAsState()

    Column (modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            Text(formatter.format(time), style=MaterialTheme.typography.displayMedium)
        }
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


@Composable
fun AppList(apps: List<ApplicationInfo>) {
    for (app in apps) {
        AppListEntry(app)
    }
}

@Composable
fun AppListEntry(app: ApplicationInfo) {
    var roundedShape = RoundedCornerShape(15.dp)
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clip(roundedShape)
        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary), shape = roundedShape)
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(7.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            rememberDrawablePainter(app.loadIcon(LocalContext.current.packageManager)),
            "",
            modifier = Modifier
                .size(50.dp)
                .aspectRatio(1f)
        )

        Column (modifier = Modifier.padding(horizontal = 5.dp)) {
            Text(app.loadLabel(LocalContext.current.packageManager).toString(), style=MaterialTheme.typography.titleSmall)
        }
    }
}
    