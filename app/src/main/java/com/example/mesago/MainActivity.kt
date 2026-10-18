package com.example.mesago

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mesago.navigation.AppNavigation
import com.example.mesago.ui.theme.MesaGoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MesaGoTheme {
                AppNavigation()
            }
        }
    }
}