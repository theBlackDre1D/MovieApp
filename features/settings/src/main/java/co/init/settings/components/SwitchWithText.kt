package co.init.settings.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithText(
    checked: Boolean,
    text: String,
    enabled: Boolean = true,
    onChanged: (Boolean) -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
    ){
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 100.dp)
            ,
            text = text
        )

        Switch(
            checked = checked,
            enabled = enabled,
            onCheckedChange = onChanged
        )
    }
}