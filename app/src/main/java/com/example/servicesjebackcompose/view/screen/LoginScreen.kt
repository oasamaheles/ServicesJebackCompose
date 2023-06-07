package com.example.servicesjebackcompose.view.screen
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.servicesjebackcompose.R
import com.example.servicesjebackcompose.model.PreferenceManager
import com.example.servicesjebackcompose.viewModel.LoginViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold() {
                val tabTitles = listOf("Service Provider", "Customer")
                val selectedTab = remember { mutableStateOf(0) }
                val loginViewModel: LoginViewModel = viewModel()

                val context = LocalContext.current
                val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
                val error by loginViewModel.error.collectAsState()
                val token by loginViewModel.theToken.collectAsState()
                val userId by loginViewModel.userId.collectAsState()
                val userPhone by loginViewModel.userPhone.collectAsState()


                val preferenceManager = PreferenceManager(context)




                if (isLoggedIn) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)

                    navigateToHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }
                Login(loginViewModel = loginViewModel)


            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Login(loginViewModel: LoginViewModel) {
    val startColor = Color(0xC7346EDF) // Green color
    val endColor = Color(0xFF6FC8FB)
    val remember by remember { mutableStateOf(1) }
    val selectedTab = remember { mutableStateOf(0) }
    val loginViewModel: LoginViewModel = viewModel()

    val context = LocalContext.current
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
    val error by loginViewModel.error.collectAsState()
    val token by loginViewModel.theToken.collectAsState()
    val userId by loginViewModel.userId.collectAsState()
    val userPhone by loginViewModel.userPhone.collectAsState()


    val preferenceManager = PreferenceManager(context)

    if (isLoggedIn) {
        preferenceManager.saveToken(token = token)
        preferenceManager.saveUserId(userId = userId)
        preferenceManager.saveUserPhone(userPhone = userPhone)

        navigateToHomeScreen(context)
    }

    error?.let { errorMessage ->
        // Display the error message
        Toast.makeText(
            context,
            errorMessage,
            Toast.LENGTH_LONG
        ).show()

    }


    Box(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        // Add your image here
//        Image(
//            painter = painterResource(R.drawable.app_icon),
//            contentDescription = "Image",
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
    }

    val tabRowItemsLogin = listOf(
        TabRowItems(
            title = "Customer",
            screen = {
                val btnSignIn = Color(0xFF0E4DFB) // Green color
                val btnStartColor = Color(0xFF346EDF) // Green color
                val btnEndColor = Color(0xFF6FC8FB)
                val buttonWidth = 164.dp
                val buttonHeight = 55.dp
                var rememberMe by remember { mutableStateOf(false) }
                // Forget password click event
                val onForgetPasswordClick: () -> Unit = {
                    // Handle forget password click
                }
                var emailState by remember { mutableStateOf("") }
                var passwordState by remember { mutableStateOf("") }
                val rememberMeState = remember { mutableStateOf(false) }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    TextFieldIcons(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = "Email",
                        placeholder = "Enter your Email address",
                        Icon = Icons.Default.Email,
                        isError = !isValidEmail(emailState)
                    )

                    TextFieldIcons(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        label = "Password",
                        placeholder = "Enter your password",
                        Icon = Icons.Default.Lock,
                        isError = !isValidPassword(passwordState)
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { checked -> rememberMe = checked },
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = Color.White,
                                checkedColor = Color.Blue // Change to desired color
                            ),
                            modifier = Modifier.clip(CircleShape)
                        )
                        Text(
                            text = "Remember me",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Forget password ?",
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.clickable { onForgetPasswordClick() }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Have Account?",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 5.dp)
                            )

                            TextButton(modifier = Modifier
                                .padding(bottom = 1.dp)
                                .align(alignment = Alignment.Start),
                                onClick = {
                                    navigateToRegisterScreen(
                                        context = context
                                    )

                                }
                            ) {
                                Text(fontSize = 16.sp, modifier = Modifier.padding(bottom = 1.dp),
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                textDecoration = TextDecoration.Underline,
                                                color = btnSignIn
                                            )
                                        ) {
                                            append("Sign In")
                                        }
                                    }
                                )
                            }
                        }

                        TextButton(
                            modifier = Modifier
                                .size(width = buttonWidth, height = buttonHeight)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(btnStartColor, btnEndColor)
                                    )
                                ),
                            onClick = {
                                val email = emailState
                                val password = passwordState
                                loginViewModel.login(
                                    email = email,
                                    password = password
                                )


                            }
                        ) {
                            Text(text = "Sign Up", color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(130.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                     navigateToHomeScreen(context = context)
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Get Start Now",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Arrow Icon",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 5.dp, top = 5.dp),
                                tint = Color.Black
                            )
                        }
                    }

                }


            },
        ),
        TabRowItems(
            title = "Service provider",
            screen = {
                val btnSignIn = Color(0xFF0E4DFB) // Green color
                val btnStartColor = Color(0xFF346EDF) // Green color
                val btnEndColor = Color(0xFF6FC8FB)
                val buttonWidth = 164.dp
                val buttonHeight = 55.dp
                var rememberMe by remember { mutableStateOf(false) }
                // Forget password click event
                val onForgetPasswordClick: () -> Unit = {
                    // Handle forget password click
                }
                var emailState by remember { mutableStateOf("") }
                var passwordState by remember { mutableStateOf("") }



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextFieldIcons(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = "Email",
                        placeholder = "Enter your Email address",
                        Icon = Icons.Default.Email,
                        isError = !isValidEmail(emailState)
                    )

                    TextFieldIcons(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        label = "Password",
                        placeholder = "Enter your password",
                        Icon = Icons.Default.Lock,
                        isError = false
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { checked -> rememberMe = checked },
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = Color.White,
                                checkedColor = Color.Blue // Change to desired color
                            ),
                            modifier = Modifier.clip(CircleShape)
                        )
                        Text(
                            text = "Remember me",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Forget password ?",
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
                            modifier = Modifier.clickable { onForgetPasswordClick() }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "Have Account?",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                            val context = LocalContext.current

                            TextButton(modifier = Modifier
                                .padding(bottom = 1.dp)
                                .align(alignment = Alignment.Start),
                                onClick = {
                                    navigateToRegisterScreen(
                                        context = context
                                    )
                                }
                            ) {
                                Text(fontSize = 16.sp, modifier = Modifier.padding(bottom = 1.dp),
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                textDecoration = TextDecoration.Underline,
                                                color = btnSignIn
                                            )
                                        ) {
                                            append("Sign up")
                                        }
                                    }
                                )
                            }
                        }

                        TextButton(
                            modifier = Modifier
                                .size(width = buttonWidth, height = buttonHeight)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(btnStartColor, btnEndColor)
                                    )
                                ),
                            onClick = {
                                val email = emailState
                                val password = passwordState


                                loginViewModel.login(
                                    email = email,
                                    password = password
                                )

                            }
                        ) {
                            Text(text = "LOGIN", color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(130.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    navigateToHomeScreen(context = context)
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            val context = LocalContext.current

                            TextButton(onClick = {
                                navigateToHomeScreen(
                                    context = context
                                )
                            }) {
                                Text(
                                    text = "Get Start Now",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.SansSerif,
                                )

                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Arrow Icon",
                                    modifier = Modifier
                                        .size(25.dp)
                                        .padding(start = 5.dp, top = 5.dp),
                                    tint = Color.Black
                                )

                            }



                        }
                    }


                }
            },
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = 60.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "Image",
                modifier = Modifier.align(Alignment.TopCenter).width(133.91.dp).height(90.03.dp)
            )
        }
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            TabRow(
                selectedTabIndex = remember,
                backgroundColor = Color.Transparent,
                contentColor = Color.Blue,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        color = Color(0xFF0E4DFB)
                    )
                },
            ) {

                tabRowItemsLogin.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        selectedContentColor = Color(0xFF0E4DFB),
                        unselectedContentColor = Color(0xFFAFAFAF),
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = item.title,
                                maxLines = 2,
                                fontSize = 15.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabRowItemsLogin.size,
                state = pagerState,

                ) {
                tabRowItemsLogin[pagerState.currentPage].screen()
            }

        }
    }

}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateToRegisterScreen(context: Context) {
    val intent = Intent(context, RegisterScreen::class.java).apply {}
    context.startActivity(intent)
}

data class TabRowItems(
    val title: String,
    val screen: @Composable () -> Unit,
)


@Composable
fun TextFieldIcons(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    Icon: ImageVector,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = { Icon(imageVector = Icon, contentDescription = "emailIcon") },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start
        ),
    )
}


fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    return email.matches(emailPattern)
}
fun isValidPassword(password: String): Boolean {
    // Define your password validation criteria here
    val minLength = 8
    val maxLength = 20
    return password.length in minLength..maxLength
}



