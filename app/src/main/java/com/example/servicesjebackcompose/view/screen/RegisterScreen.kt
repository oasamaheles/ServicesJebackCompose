package com.example.servicesjebackcompose.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicesjebackcompose.model.PreferenceManager
import com.example.servicesjebackcompose.view.view_helper.ButtonApp
import com.example.servicesjebackcompose.view.view_helper.CustomDropdownMenu
import com.example.servicesjebackcompose.viewModel.AllWorkViewModel
import com.example.servicesjebackcompose.viewModel.RegisterViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


class RegisterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val registerViewModel: RegisterViewModel = viewModel()
                val isRegistered by registerViewModel.isRegistered.collectAsState()
                val error by registerViewModel.error.collectAsState()
                val token by registerViewModel.theToken.collectAsState()
                val userId by registerViewModel.userId.collectAsState()
                val userPhone by registerViewModel.userPhone.collectAsState()


                val context = LocalContext.current

                val preferenceManager = PreferenceManager(context)


                if (isRegistered) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)

                    preferenceManager.saveToken(token)
                    navigateTHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }

                RegisterScreenView(registerViewModel)
        }
    }

    override fun onBackPressed() {

    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegisterScreenView(viewModel: RegisterViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
    ) {


        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start
        ) {


            Box(modifier = Modifier.fillMaxWidth()) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    text = "Register",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp

                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    val context = LocalContext.current

                    IconButton(onClick = { navigateToLoginScreen(context) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.padding(start = 6.dp, bottom = 14.dp, top = 20.dp)
                        )
                    }

                }
            }
            // Define the TabItem class
            data class TabItem(val title: String, val screen: @Composable () -> Unit)

            val startColor = Color(0xFF346EDF)
            val endColor = Color(0xFF6FC8FB)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(startColor, endColor)
                        )
                    )
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                // Create the tabRowItemsLogin list
                val tabRowItemsLogin = listOf(
                    TabItem(title = "Service Provider") { ServiceRegisterView(viewModel) },
                    TabItem(title = "Customer") { CustomerRegisterView(viewModel) }
                )

                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Color.White)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))

                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
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
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(
                                            index
                                        )
                                    }
                                },
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
                    ) { page ->
                        tabRowItemsLogin[page].screen()
                    }
                }
            }
        }
    }

}

@Composable
fun CustomerRegisterView(viewModel: RegisterViewModel) {
    val MIN_USERNAME_LENGTH = 6
    val MIN_PASSWORD_LENGTH = 8
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }


    val btnSignIn = Color(0xFF0E4DFB)

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }
    var isUsernameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPhoneNumberError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldIcons(
            label = "Full Name",
            placeholder = "",
            text = fullNameState.value.text,
            Icon = Icons.Default.Person,
            isError = isUsernameError,
            onValueChange = { value ->
                fullNameState.value = TextFieldValue(value)
                isUsernameError = value.isEmpty() || value.length < MIN_USERNAME_LENGTH
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Email",
            placeholder = "",
            text = emailState.value.text,
            Icon = Icons.Default.Email,
            isError = isEmailError,
            onValueChange = { value ->
                emailState.value = TextFieldValue(value)
                isEmailError = !isValidEmail(value)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Phone Number",
            placeholder = "",
            text = phoneNumberState.value.text,
            Icon = Icons.Default.Phone,
            isError = isPhoneNumberError,
            onValueChange = { value ->
                phoneNumberState.value = TextFieldValue(value)
                isPhoneNumberError = !isValidPhoneNumber(value)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Password",
            placeholder = "",
            text = passwordState.value.text,
            Icon = Icons.Default.Lock,
            isError = isPasswordError,
            onValueChange = { value ->
                passwordState.value = TextFieldValue(value)
                isPasswordError = value.isEmpty() || value.length < MIN_PASSWORD_LENGTH
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = { rememberMeState.value = it },
            )

            Text(
                text = "I Read and Accept",
                color = Color.Black,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )

            Text(
                text = "  Home Service Terms & Conditions",
                color = Color.Blue,
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Have Account? ",
                    color = Color.Black,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                )

                val context = LocalContext.current

                TextButton(onClick = {
                    val name = fullNameState.value.text
                    val email = emailState.value.text
                    val password = passwordState.value.text
                    val phone = phoneNumberState.value.text

                    viewModel.register(name, email, password, phone)
                }) {
                    Text(
                        fontSize = 16.sp,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 6.dp),
                        textAlign = TextAlign.Start,
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = btnSignIn
                                )
                            ) {
                                append("Login")
                            }
                        }
                    )
                }
            }

            val context = LocalContext.current

            ButtonApp(
                onClick = { navigateToLoginScreen(context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF),
                    Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ServiceRegisterView(registerViewModel: RegisterViewModel) {
    val MIN_USERNAME_LENGTH = 5
    val MIN_PASSWORD_LENGTH = 8
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 10
    }

    fun isValidEmail(email: String): Boolean {
        // Implement your email validation logic here
        // Return true if the email is valid, false otherwise
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }


    val btnSignIn = Color(0xFF0E4DFB) // Green color

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    var isUsernameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPhoneNumberError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }


    val viewModel: AllWorkViewModel = viewModel()
    val listModel = viewModel.listAllWorkLiveData


    val dropDownItems = mutableListOf<String>()
    viewModel.getAllWork()

    listModel.forEach { item ->
        dropDownItems.add(item.name.toString())
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldIcons(
            label = "Full Name",
            placeholder = "",
            text = fullNameState.value.text,
            Icon = Icons.Default.Person,
            isError = isUsernameError,
            onValueChange = { value ->
                fullNameState.value = TextFieldValue(value)
                isUsernameError = value.isEmpty() || value.length < MIN_USERNAME_LENGTH
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Email",
            placeholder = "",
            text = emailState.value.text,
            Icon = Icons.Default.Email,
            isError = isEmailError,
            onValueChange = { value ->
                emailState.value = TextFieldValue(value)
                isEmailError = !isValidEmail(value)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Phone Number",
            placeholder = "",
            text = phoneNumberState.value.text,
            Icon = Icons.Default.Phone,
            isError = isPhoneNumberError,
            onValueChange = { value ->
                phoneNumberState.value = TextFieldValue(value)
                isPhoneNumberError = !isValidPhoneNumber(value)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextFieldIcons(
            label = "Password",
            placeholder = "",
            text = passwordState.value.text,
            Icon = Icons.Default.Lock,
            isError = isPasswordError,
            onValueChange = { value ->
                passwordState.value = TextFieldValue(value)
                isPasswordError = value.isEmpty() || value.length < MIN_PASSWORD_LENGTH
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        CustomDropdownMenu(dropDownItems)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "I Read and Accept",
                color = Color.Black,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )

            Text(
                text = "  Home Service Terms & Conditions",
                color = Color.Blue,
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Have Account? ",
                    color = Color.Black,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.body1,
                )

                val context = LocalContext.current
                TextButton(onClick = { navigateTHomeScreen(context) }) {
                    Text(
                        fontSize = 16.sp,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 6.dp),
                        textAlign = TextAlign.Start,
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = btnSignIn
                                )
                            ) {
                                append("Login")
                            }
                        }
                    )
                }
            }

            val context = LocalContext.current

            ButtonApp(
                onClick = {

                    val name = fullNameState.value.text
                    val email = emailState.value.text
                    val password = passwordState.value.text
                    val phone = phoneNumberState.value.text

                    registerViewModel.register(name, email, password, phone)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF),
                    Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateTHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}

@Composable
fun TextFieldIcons(
    label: String,
    placeholder: String,
    text: String,
    Icon: ImageVector,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(4.dp)
            )
        },
        placeholder = { Text(text = placeholder, fontSize = 14.sp) },
        leadingIcon = { Icon(imageVector = Icon, contentDescription = "emailIcon") },
        isError = isError,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Start
        ),
        modifier = Modifier
            .fillMaxWidth()

    )
}

