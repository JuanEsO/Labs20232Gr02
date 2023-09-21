package co.edu.udea.compumovil.gr02_20232.lab1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr02_20232.lab1.R

@Composable
fun ContactDataScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            var phone by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var country by remember { mutableStateOf("") }
            var city by remember { mutableStateOf("") }

            Row(
                modifier = Modifier.padding(5.dp, 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .align(Alignment.CenterVertically)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                )
            }
            Row(
                modifier = Modifier.padding(5.dp, 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .align(Alignment.CenterVertically)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                )
            }
            Row(
                modifier = Modifier.padding(5.dp, 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .align(Alignment.CenterVertically)
                )

                OutlinedTextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),

                )
            }
            Row(
                modifier = Modifier.padding(5.dp, 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .align(Alignment.CenterVertically)
                )

                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("City") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                )
            }
            Row(
                modifier = Modifier.padding(5.dp, 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = stringResource(R.string.back_button),
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                        .align(Alignment.CenterVertically)
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    maxLines = 1,
                    textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                )
            }
        }
        Row(modifier = Modifier.weight(1f, false)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.padding_medium)
                        )
            ) {
                Button(
                    onClick = onNextButtonClicked,
                    modifier = modifier.widthIn(min = 150.dp)
                ) {
                    Text("Finalizar")
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderSummaryPreview() {
    ContactDataScreen(
        modifier = Modifier.fillMaxHeight(),
        onNextButtonClicked = {}
    )
}