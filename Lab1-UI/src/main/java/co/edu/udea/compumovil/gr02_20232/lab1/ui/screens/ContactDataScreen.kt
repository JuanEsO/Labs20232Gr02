package co.edu.udea.compumovil.gr02_20232.lab1.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr02_20232.lab1.R

@Composable
fun ContactDataScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 22.dp)
        .height(1000.dp)
        .verticalScroll(rememberScrollState()),
    onNextButtonClicked: () -> Unit = {}
) {

    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var keyboardImmeActionCustom by remember { mutableStateOf( ImeAction.Next )}
    keyboardImmeActionCustom = if (validateContactForm(phone = phone.toLongOrNull() ?: 0L, email = email, country = country)) {
        ImeAction.Done
    } else {
        ImeAction.Next
    }
    var isEmailValid by remember { mutableStateOf(true) }
    var isFormFill = validateContactForm(phone = phone.toLongOrNull() ?: 0L, email = email, country = country) && isEmailValid
    fun onNext() {

        if (isFormFill) {
            var genderValue : String? = null

            logPersonalData(phone = phone.toLongOrNull() ?: 0L,
                email = email,
                country = country,
                city = city,
                address = address)
            onNextButtonClicked()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "iconCall",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Phone") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { newText ->
                    email = newText
                    isEmailValid = isEmailValidEmail(newText)

                },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                isError = !isEmailValid,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )
            OutlinedTextField(
                value = country,
                onValueChange = { newText ->
                    country = newText
                },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "country",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Pais") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )



            OutlinedTextField(
                value = city,
                onValueChange = { newText ->
                    city = newText
                },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "ciudad",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Ciudad") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )
            OutlinedTextField(
                value = address,
                onValueChange = { newText ->
                    address = newText
                },
                leadingIcon = { Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "address",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Direccion") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )


        }
        Row(modifier = Modifier.weight(1f, false)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    enabled = isFormFill,
                    onClick = {onNext()}
                ) {
                    Text("Finalizar")
                }
                Spacer(modifier = Modifier.height(450.dp))

            }
        }
    }
}

fun validateContactForm(phone: Number, email: String, country: String): Boolean {
    return phone != null && email.isNotEmpty() && country.isNotEmpty()
}

fun isEmailValidEmail(email: String): Boolean {
    // Regular expression for a simple email validation
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")

    return email.matches(emailRegex)
}

fun logPersonalData(phone: Number, email: String, country: String, city : String? = null, address: String? = null) {
    val tag = "[RESULT]"
    Log.i(tag, "Datos personales: ")
    Log.i(tag, "Telefono: $phone")
    Log.i(tag, "Email: $email")
    Log.i(tag, "pais: $country")
    Log.i(tag, "Ciudad: $city")
    Log.i(tag, "Direccion: $address")
}

@Preview
@Composable
fun OrderSummaryPreview() {
    ContactDataScreen(
        modifier = Modifier.fillMaxHeight(),
        onNextButtonClicked = {}
    )
}