package co.init.info

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.init.core.R
import co.init.core.data.TopBarConfiguration

@Composable
fun InfoScreen(
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    onTopBarConfiguration(
        TopBarConfiguration(
            title = R.string.bottom_navigation_info
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val context = LocalContext.current
        val packageInfo = LocalContext.current.packageManager.getPackageInfo(context.packageName, 0)

        Text(stringResource(R.string.info_app_version, packageInfo.versionName))
        Text(stringResource(R.string.info_app_code, packageInfo.longVersionCode.toString()))

        Spacer(modifier = Modifier.height(30.dp))

        Text(stringResource(R.string.info_device_manufacturer, Build.MANUFACTURER))
        Text(stringResource(R.string.info_device_model, Build.MODEL))
        Text(stringResource(R.string.info_device_system_version, Build.VERSION.RELEASE, Build.VERSION.SDK_INT))

        Spacer(modifier = Modifier.height(30.dp))

        val packageNameParts = packageInfo.packageName.split(".")
        Text(stringResource(R.string.info_app_developer, packageNameParts[1].uppercase()))
    }
}