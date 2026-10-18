package com.example.mesago.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.R
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit
) {

    var correo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var mostrarContrasena by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MesaGoWhite)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.mesago_logo),
            contentDescription = "Logo MesaGo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Iniciar sesión",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MesaGoBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = {
                Text("Correo institucional")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MesaGoBlack,
                unfocusedTextColor = MesaGoBlack,
                focusedLabelColor = MesaGoRed,
                unfocusedLabelColor = MesaGoGray,
                cursorColor = MesaGoRed,
                focusedBorderColor = MesaGoRed,
                unfocusedBorderColor = MesaGoGray
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = {
                Text("Contraseña")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (mostrarContrasena) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        mostrarContrasena = !mostrarContrasena
                    }
                ) {
                    Icon(
                        imageVector = if (mostrarContrasena) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = if (mostrarContrasena) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        },
                        tint = MesaGoGray
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MesaGoBlack,
                unfocusedTextColor = MesaGoBlack,
                focusedLabelColor = MesaGoRed,
                unfocusedLabelColor = MesaGoGray,
                cursorColor = MesaGoRed,
                focusedBorderColor = MesaGoRed,
                unfocusedBorderColor = MesaGoGray
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = MesaGoRed,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MesaGoRed
            )
        ) {
            Text(
                text = "Iniciar sesión",
                color = MesaGoWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "o",
            color = MesaGoGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = { }
        ) {
            Text(
                text = "Continuar con Google (ULEAM)",
                color = MesaGoBlack
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "¿No tienes cuenta? Regístrate",
            fontSize = 14.sp,
            color = MesaGoRed,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                onRegisterClick()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}