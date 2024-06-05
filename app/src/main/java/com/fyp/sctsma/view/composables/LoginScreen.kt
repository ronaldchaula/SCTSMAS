package com.fyp.sctsma.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.fyp.sctsma.R
import com.fyp.sctsma.model.remote.LoginServiceImplementation
import com.fyp.sctsma.model.remote.networkServices.LoginService
import com.fyp.sctsma.viewModel.LoginState
import com.fyp.sctsma.viewModel.LoginViewModel
import components.BackgroundImage
import kotlinx.coroutines.Job
import tz.co.sctsma.android.Components.RegistrationScreen

class LoginScreen : Screen{
    @Composable
    override fun Content() {
        //navigation component
        val navigator = LocalNavigator.currentOrThrow
        val loginService: LoginService = LoginService(LoginServiceImplementation())
        //view model object
        val loginViewModel: LoginViewModel = LoginViewModel()
        //hoisting the states
        var phoneNumber by remember { mutableStateOf("") } //this remembers the phone input
        var password by remember { mutableStateOf("") } //this remembers the password input
        //login state check
        val loginState by loginViewModel.loginState.observeAsState()

        when (loginState) {
            is LoginState.Success -> navigator.navigate("HomeRoute")
            is LoginState.Error -> {/* Show error message */}
            null -> {/* Show loading indicator */}
        }

     Login(
         //passing the states
         navigator,
         phoneNumber = phoneNumber,
         onUserPhoneUpdated = { newPhoneNumber ->
             phoneNumber = newPhoneNumber
             loginViewModel.phoneNumber.value = newPhoneNumber
         },
         password = password,
         onPasswordUpdated = { newPassword ->
             password = newPassword
             loginViewModel.password.value = newPassword
         },
         loginViewModel,
         onLoginClicked = {
             loginViewModel.login()
         }
         )

    }
}

@Composable
fun Login(
    navigator: Navigator,
    phoneNumber: String,
    onUserPhoneUpdated: (String) -> Unit,
    password: String,
    onPasswordUpdated: (String) -> Unit,
    loginViewModel: LoginViewModel,
    onLoginClicked: () -> Job
) {

//Central Arrangement
    BackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
              ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            //App title text
            Text(text = stringResource(
                R.string.app_name),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp, top = 20.dp)
                ,
                style = TextStyle(
                    fontSize = 30.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFAFAFA),
                    textAlign = TextAlign.Center
                )

            )

            //Login page heading 2

            Text(text = stringResource(R.string.login_heading),
                modifier = Modifier
                    .width(294.dp)
                    .wrapContentSize(Alignment.CenterStart)
                    .padding(start = 8.dp, bottom = 52.dp),

                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2F4858),
                    textAlign = TextAlign.Start
                )

            )

            //Login form container

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(294.dp)
                    .height(365.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .background(Color(0xFFFAFAFA))
                    .border(
                        width = 1.dp,
                        Color(0x302F4858),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .shadow( // Add shadow properties here
                        elevation = .1.dp, // Adjust shadow elevation (distance)
                        ambientColor = Color.LightGray, // Color for ambient light
                        spotColor = Color.DarkGray // Color for light source
                    )
            ){

                //Phone number label
                Text(
                    text = "Phone Number",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp)
                )

                //Phone number input
                OutlinedTextField(
                    value = phoneNumber ,
                    onValueChange =onUserPhoneUpdated,
                    placeholder = {
                        Text(text = "(+255)07XXXXXXXX")
                    },
                    //styling

                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .border(
                            width = .1.dp,
                            color = Color(0xFF2F4858),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(Color(0xFFFAFAFA))
                )


                //Password
                Text(
                    text = "Password",
                    style = TextStyle(
                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(bottom = 5.dp, top = 5.dp)
                )

                //Password
                OutlinedTextField(
                    value = password ,
                    onValueChange =onPasswordUpdated,
                    placeholder = {Text(text = "Type password here")}
                    ,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    ,
                    //styling

                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF2F4858),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(Color(0xFFFAFAFA))
                )


                Button(
                    onClick =onLoginClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6FC07A)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(65.dp)
                        .padding(top = 10.dp)
                ) {
                    Text(text = "Login",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),
                            textAlign = TextAlign.Center,
                        ))
                }


                Text(
                    text = "Don’t have an account?",
                    style = TextStyle(
                        fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF2F4858)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(start = 4.dp, bottom = 5.dp, top = 5.dp)
                )

                Button(
                    onClick = {
                        navigator.push(RegistrationScreen())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F4858)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .height(55.dp)

                ) {
                    Text(
                        text = "Register",
                        modifier = Modifier.wrapContentSize(Alignment.Center),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFAFAFA),

                            textAlign = TextAlign.Center,
                        ))
                }

            }




        }
    }

//error message
@Composable
fun ErrorText() {
Text(
    modifier = Modifier
        .fillMaxWidth(.9f)
        .height(5.dp)
        .padding(top = 10.dp)
,
    text = "Either phone number or password is incorrect",
    style = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontWeight = FontWeight(500),
        color = Color.Red))}


//
//@Preview
//@Composable
//fun LoginPreview(){
//    Login(phoneNumber = phoneNumber, password = password)
//}
