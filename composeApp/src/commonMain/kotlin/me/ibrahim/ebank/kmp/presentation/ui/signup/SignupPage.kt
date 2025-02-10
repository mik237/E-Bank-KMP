package me.ibrahim.ebank.kmp.presentation.ui.signup

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import e_bank_kmp.composeapp.generated.resources.Res
import e_bank_kmp.composeapp.generated.resources.already_have_an_account
import e_bank_kmp.composeapp.generated.resources.cnic
import e_bank_kmp.composeapp.generated.resources.create_your_account
import e_bank_kmp.composeapp.generated.resources.email
import e_bank_kmp.composeapp.generated.resources.fingerprint
import e_bank_kmp.composeapp.generated.resources.login
import e_bank_kmp.composeapp.generated.resources.mobile_number
import e_bank_kmp.composeapp.generated.resources.sign_up
import e_bank_kmp.composeapp.generated.resources.uae_flag
import e_bank_kmp.composeapp.generated.resources.username
import me.ibrahim.ebank.kmp.presentation.composables.CustomButton
import me.ibrahim.ebank.kmp.presentation.composables.InteractionBlocker
import me.ibrahim.ebank.kmp.presentation.composables.PasswordTextField
import me.ibrahim.ebank.kmp.presentation.composables.SpannableText
import me.ibrahim.ebank.kmp.presentation.composables.TopEndCircle
import me.ibrahim.ebank.kmp.presentation.decompose.signup.SignupComponent
import me.ibrahim.ebank.kmp.utils.ThemeColor_Blue
import me.ibrahim.ebank.kmp.utils.ThemeColor_DarkGrey
import me.ibrahim.ebank.kmp.utils.ThemeColor_Grey
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignupPage(component: SignupComponent) {

    val state by component.state.subscribeAsState()

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = state.uiState is SignupUiState.SigningUp
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
                    text = stringResource(Res.string.create_your_account),
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = state.username,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        component.onAction(SignupUiAction.TypeUsername(username = it))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
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

                TextField(
                    value = state.email,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        component.onAction(SignupUiAction.TypeEmail(email = it))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.email),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.ThemeColor_Grey
                            )
                        )
                    }
                )

                TextField(
                    value = state.mobileNumber,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        component.onAction(SignupUiAction.TypeMobileNumber(mobile = it))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.mobile_number),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.ThemeColor_Grey
                            )
                        )
                    },
                    leadingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(Res.drawable.uae_flag),
                                contentDescription = null,
                                modifier = Modifier.width(25.dp)
                                    .padding(start = 5.dp)
                            )
                            Text(text = "+971", color = Color.ThemeColor_DarkGrey)
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone
                    )
                )

                TextField(
                    value = state.cnic,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onValueChange = {
                        component.onAction(SignupUiAction.TypeCNIC(cnic = it))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.cnic),
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.ThemeColor_Grey
                            )
                        )
                    }
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PasswordTextField(
                        modifier = Modifier.weight(1f),
                        password = state.password,
                        onPasswordChange = {
                            component.onAction(SignupUiAction.TypePassword(password = it))
                        }
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(54.dp)
                            .background(color = Color.White, shape = MaterialTheme.shapes.medium),
                    ) {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Fingerprint,
                                contentDescription = stringResource(Res.string.fingerprint),
                                tint = Color.ThemeColor_DarkGrey,
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = state.termsAndConditionAccepted,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.ThemeColor_Blue,
                            uncheckedColor = Color.White
                        ),
                        onCheckedChange = { checked ->
                            component.onAction(SignupUiAction.OnCheckBoxAction(checked))
                        })
                    SpannableText(
                        planeText = "I agree with ",
                        planeTextColor = Color.White,
                        annotatedText = "Terms & Condition",
                        annotatedTextColor = Color.ThemeColor_Blue,
                        onAnnotationClicked = {}
                    )
                }

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    text = stringResource(Res.string.sign_up),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    containerColor = Color.ThemeColor_Blue,
                    contentColor = Color.White
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 12.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.already_have_an_account),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.ThemeColor_Grey
                        )
                    )
                    TextButton(onClick = {
                        component.onAction(SignupUiAction.OnLoginClick)
                    }) {
                        Text(
                            text = stringResource(Res.string.login),
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