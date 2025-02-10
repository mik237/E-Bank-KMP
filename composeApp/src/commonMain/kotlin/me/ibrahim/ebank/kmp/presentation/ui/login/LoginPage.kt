package me.ibrahim.ebank.kmp.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.dont_have_an_account
import e_bank_kmp.composeapp.generated.resources.fingerprint
import e_bank_kmp.composeapp.generated.resources.forgot_username_or_password
import e_bank_kmp.composeapp.generated.resources.login
import e_bank_kmp.composeapp.generated.resources.login_to_your_account
import e_bank_kmp.composeapp.generated.resources.password
import e_bank_kmp.composeapp.generated.resources.sign_up
import e_bank_kmp.composeapp.generated.resources.username
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.composables.PasswordTextField
import me.ibrahim.ebank.kmp.presentation.composables.TopEndCircle
import me.ibrahim.ebank.kmp.presentation.decompose.login.LoginComponent
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginPage(component: LoginComponent) {

    val state by component.state.subscribeAsState()
    val username by component.username.subscribeAsState()
    val password by component.password.subscribeAsState()

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = state is LoginUiState.Logging
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.ThemeColor_DarkGrey)
        ) {
            TopEndCircle(color = Color.ThemeColor_Grey)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.BottomCenter)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .statusBarsPadding()
            ) {
                Text(
                    text = stringResource(Res.string.login_to_your_account),
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = username,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        component.onAction(LoginUiAction.TypeUsername(username = it))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.username),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.ThemeColor_Grey
                            )
                        )
                    }
                )

                PasswordTextField(
                    password = password,
                    onPasswordChange = {
                        component.onAction(LoginUiAction.TypePassword(password = it))
                    })

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    text = stringResource(Res.string.login),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    containerColor = Color.ThemeColor_Blue,
                    contentColor = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(Res.string.forgot_username_or_password),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.ThemeColor_Grey
                    )
                )

                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Filled.Fingerprint,
                        contentDescription = stringResource(Res.string.fingerprint),
                        tint = Color.White,
                        modifier = Modifier.size(80.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 12.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.dont_have_an_account),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.ThemeColor_Grey
                        )
                    )
                    TextButton(onClick = {}) {
                        Text(
                            text = stringResource(Res.string.sign_up),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}