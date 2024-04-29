package com.navio.socialmedia

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onFinish: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd), onFinish)
        Spacer(modifier = Modifier.size(16.dp))
        Body(Modifier.align(Alignment.Center))
        Spacer(modifier = Modifier.size(16.dp))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier, onIconClick: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.icon_cross),
        contentDescription = "Close application",
        modifier = modifier
            .size(32.dp)
            .clickable { onIconClick() }
    )
}


@Composable
fun Body(modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var copy by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Logo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(32.dp))
        Email(email) { value ->
            email = value
            isLoginEnabled = enableLoginButton(email, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Password(password) { value ->
            password = value
            isLoginEnabled = enableLoginButton(email, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Copy("Value to copy...")
        Spacer(modifier = Modifier.size(8.dp))
        //The modifier of this column which is the parent...
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(32.dp))
        LoginButton(isLoginEnabled)
        Spacer(modifier = Modifier.size(16.dp))
        DividerLine()
        Spacer(modifier = Modifier.size(16.dp))
        SocialLogin()
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.size(8.dp))
        Row() {
            Text(
                text = "Don't have an account? ",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "Sign up",
                color = Color.Cyan,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_aplitop),
        contentDescription = "Logo from Aplitop",
        modifier = modifier
    )
}

@Composable
fun Email(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { value -> onEmailChange(value) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Email") },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = Color.DarkGray,
            focusedTextColor = Color.DarkGray,
            focusedContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.DarkGray,
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        //Field trailing icon
        trailingIcon = {
            IconButton(onClick = { onEmailChange("") }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_delete),
                    contentDescription = "Clear",
                )
            }
        },
    )
}

@Composable
fun Password(password: String, onPasswordChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { value -> onPasswordChange(value) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Password") },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = Color.DarkGray,
            focusedTextColor = Color.DarkGray,
            focusedContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.DarkGray
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        //Field trailing icon
        trailingIcon = {
            val eyeIcon = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = eyeIcon,
                    contentDescription = "Password visibility",
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Copy(text: String) {
    //Class to access the clipboard and copy the text
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    TextField(
        value = text,
        enabled = false,
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            disabledPlaceholderColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            disabledTrailingIconColor = Color.DarkGray
        ),
        singleLine = true,
        maxLines = 1,
        //Field trailing icon
        trailingIcon = {
            IconButton(onClick = {
                val clip = ClipData.newPlainText("copy", text)
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_save),
                    contentDescription = "Copy text to clipboard",
                )
            }
        },
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        "Forgot password?",
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Cyan
    )
}

@Composable
fun LoginButton(isLoginEnabled: Boolean) {
    Button(
        onClick = { /*TODO*/ },
        enabled = isLoginEnabled,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Cyan,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        )
    ) {
        Text("Log in")
    }
}

private fun enableLoginButton(email: String, password: String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6

@Composable
fun DividerLine() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.weight(1f))
        Text(
            text = "OR",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.weight(1f))
    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_google),
            contentDescription = "Google icon", Modifier.size(14.dp),
            tint = Color.Cyan
        )
        Text(
            text = "Continue as Jose Navío",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp),
            color = Color.Cyan
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    LoginScreen(){}
}