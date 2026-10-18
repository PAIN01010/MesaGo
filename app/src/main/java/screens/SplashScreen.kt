package com.example.mesago.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.R
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoLightGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(2000)
        onFinish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MesaGoWhite),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.mesago_logo),
            contentDescription = "Logo MesaGo",
            modifier = Modifier.size(220.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LinearProgressIndicator(
            modifier = Modifier.size(
                width = 220.dp,
                height = 6.dp
            ),
            color = MesaGoRed,
            trackColor = MesaGoLightGray
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Cargando...",
            fontSize = 16.sp,
            color = MesaGoGray
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "“Descubre los sabores de la ULEAM”",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = MesaGoBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Universidad Laica Eloy Alfaro de Manabí",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MesaGoGray
        )
    }
}