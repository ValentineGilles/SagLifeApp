package com.example.saglife.screen.navbars

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.saglife.ui.theme.SagLifeTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    BottomNavigation(backgroundColor = colors.surface) {

        BottomNavigationItem(
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Accueil", style = MaterialTheme.typography.bodySmall) }
        )
        BottomNavigationItem(
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) },
            icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar") },
            label = { Text(text = "Evenements", style = MaterialTheme.typography.bodySmall) }
        )
        BottomNavigationItem(
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) },
            icon = { Icon(imageVector = Icons.Default.Place, contentDescription = "Map") },
            label = { Text(text = "Carte", style = MaterialTheme.typography.bodySmall) }
        )
        BottomNavigationItem(
            selected = selectedItem == 3,
            onClick = { onItemSelected(3) },
            icon = { Icon(imageVector = Icons.Default.MailOutline, contentDescription = "Forum") },
            label = { Text(text = "Forum", style = MaterialTheme.typography.bodySmall) }
        )
    }
}