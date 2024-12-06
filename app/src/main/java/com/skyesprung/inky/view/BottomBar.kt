package com.skyesprung.inky.view

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skyesprung.inky.R
import com.skyesprung.inky.model.TopLevelRoutes
import com.skyesprung.inky.viewmodel.BottomBarViewModel

@Preview
@Composable
fun BottomBar(navController: NavHostController = rememberNavController()) {
    val viewModel : BottomBarViewModel = viewModel()
    val page by viewModel.currentPage.observeAsState()
    NavigationBar {
        NavigationBarItem(page == 0, onClick = {navController.navigate(TopLevelRoutes.AppScreen)}, icon= {
            Icon(painterResource(R.drawable.outline_apps_24), "Apps icon")
        }, label = {
            Text(stringResource(R.string.app_screen_name), style= MaterialTheme.typography.labelMedium)
        })
    }
}
