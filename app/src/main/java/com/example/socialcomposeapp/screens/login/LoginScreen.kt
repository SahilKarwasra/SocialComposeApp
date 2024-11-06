package com.example.socialcomposeapp.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.components.CustomTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigate: (DestinationScreen) -> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.navigationState.collect {
            when (it) {
                LoginNavigationEvent.NavigateToHome -> navigate(DestinationScreen.HomeScreenObj)
                LoginNavigationEvent.NavigateToSignUp -> navigate(DestinationScreen.SignUpScreenObj)
            }
        }
    }
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .background(Color(color = 0xff440E3F)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                color = Color(color = 0xffFDF5FE),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp),
                style = MaterialTheme.typography.displayLarge
            )
            when (uiState.value) {
                LoginEvent.Normal -> {
                    val email = viewModel.email.collectAsStateWithLifecycle()
                    val password = viewModel.password.collectAsStateWithLifecycle()
                    val isEnabled = viewModel.buttonEnabled.collectAsStateWithLifecycle()

                    CustomTextField(
                        value = email.value,
                        onValueChange = viewModel::onEmailChange,
                        isPassword = false,
                        placeholder = {
                            Text(
                                "Username/ Email",
                                color = Color(0xff440E3F).copy(alpha = 0.5f)
                            )
                        }
                    )

                    CustomTextField(
                        value = password.value,
                        onValueChange = viewModel::onPasswordChange,
                        isPassword = true,
                        placeholder = {
                            Text(
                                "Password",
                                color = Color(0xff440E3F).copy(alpha = 0.5f),
                            )
                        }
                    )
                    Button(
                        onClick = {
                            viewModel.onLoginClicked()
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(horizontal = 40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffE88AE8),
                            contentColor = Color(0xff440E3F),
                            disabledContainerColor = Color(0xffE88AE8),
                            disabledContentColor = Color(0xff440E3F)
                        ),
                        shape = MaterialTheme.shapes.medium,
                        enabled = isEnabled.value
                    ) {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xff440E3F)
                        )
                    }
                }

                LoginEvent.Loading -> {
                    CircularProgressIndicator()
                    Text(
                        text = "loading....."
                    )
                }

                is LoginEvent.Error -> {
                    Text(
                        text = (uiState.value as LoginEvent.Error).message,
                    )
                }

                is LoginEvent.Success -> {
                    Text(
                        "Success"
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't Have an Account? ",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(color = 0xffFDF5FE)
            )
            Text(
                text = "Register!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(color = 0xFFE88AE8),
                modifier = Modifier.clickable {
                    viewModel.onRegisterClicked()
                }
            )
        }
    }

}


