package co.init.movieapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.init.movieapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, isConnectedToNetwork: Boolean) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            if (isConnectedToNetwork) {
                Text(text = stringResource(R.string.app_name))
            } else {
                NoInternetConnection()
            }
        },
        navigationIcon = {
            if (!showBottomNavigationBar(currentRoute)) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null)
                }
            }
        },
        actions = {

            // TODO Add DevTools when implemented
//            if (BuildConfig.DEBUG) {
//                IconButton(onClick = { /* Do something */ }) {
//                    Icon(Icons.Default.MoreVert, contentDescription = null)
//                }
//            }
        }
    )
}

@Composable
private fun NoInternetConnection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(co.init.core.R.string.common_no_internet_connection),
            fontSize = 16.sp
        )
        CircularProgressIndicator(
            modifier = Modifier.size(16.dp),
            strokeWidth = 2.dp
        )
    }
}