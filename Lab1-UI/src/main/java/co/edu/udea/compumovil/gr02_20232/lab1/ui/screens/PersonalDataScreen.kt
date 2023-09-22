package co.edu.udea.compumovil.gr02_20232.lab1.ui.screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr02_20232.lab1.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Date

@Composable
fun PersonalDataScreen(
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 22.dp)
        .height(1000.dp)
        .verticalScroll(rememberScrollState())
) {
    val options = listOf("Primaria", "Secundaria", "Universidad", "Otro")
    val items = listOf<String>("ninguno", "Hombre", "Mujer")

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var lastname by remember { mutableStateOf(TextFieldValue("")) }
    var selectedGender by remember { mutableStateOf(0) }
    val mBirthDate = remember { mutableStateOf("") }
    var selectedEducation by remember { mutableStateOf(options[0]) }
    
    

    var keyboardImmeActionCustom by remember { mutableStateOf( ImeAction.Next )}

    keyboardImmeActionCustom = if (validateForm(name = name.text, lastName = lastname.text, birthDate = mBirthDate.value, education = selectedEducation )) {
        ImeAction.Done
    } else {
        ImeAction.Next
    }

    fun onNext() {
        var isFormFill = validateForm(name = name.text, lastName = lastname.text, birthDate = mBirthDate.value, education = selectedEducation )
        if (isFormFill) {
            var genderValue : String? = null
            if (selectedGender != 0) {
                genderValue = items[selectedGender]
            }
            logData(name = name.text, lastName = lastname.text, gender = genderValue, birthDate = mBirthDate.value, education = selectedEducation)
            onNextButtonClicked()
        }
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            OutlinedTextField(
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "personIcon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Nombres") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 20.dp)
            )
            OutlinedTextField(
                value = lastname,
                onValueChange = { newText ->
                    lastname = newText
                },
                leadingIcon = { Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "personIcon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                ) },
                label = { Text(text = "Apellidos") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = keyboardImmeActionCustom,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                ),
                keyboardActions = KeyboardActions (
                    onDone = { onNext() }
                ),
                shape = shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Row( modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_group_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Sexo: ", modifier = Modifier.padding(end = 20.dp))
                items.forEachIndexed { index, item ->
                    if (index != 0) {
                        CircleSelect(
                            label = item,
                            checked = items[selectedGender] == item,
                            onCheckedChange = { selectedGender = index }
                        )
                    }
                }
            }
            DatePickerInput(mDate = mBirthDate)


            
            Row(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropdownMenu(
                    options = options,
                    selectedOption = selectedEducation,
                    onChangeOption = { newOption -> selectedEducation = newOption },
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
        Row( horizontalArrangement = Arrangement.End, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 50.dp)) {
            Button(onClick = { onNext() }, modifier = Modifier.defaultMinSize(100.dp)) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun CircleSelect(
    label: String,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier.padding(horizontal = 10.dp)) {
        Text(text = label, modifier = Modifier.padding(end = 5.dp))
        Box(
            modifier = modifier
                .size(24.dp)
                .background(
                    color = if (checked) MaterialTheme.colorScheme.primary else Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = if (!checked) MaterialTheme.colorScheme.primary else Color.White,
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onCheckedChange() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }    
    }
    
}

@Composable
fun DatePickerInput(mDate: MutableState<String>) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
    val label = if (mDate.value.isNotEmpty()) {
        "${mDate.value}"
    } else {
        "Fecha de nacimiento"
    }
    Row(
        modifier = Modifier
            .padding(start = 12.dp)
            .fillMaxWidth()
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(end = 12.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = label)
        }
        Button(onClick = { mDatePickerDialog.show() }, modifier = Modifier.padding(end = 20.dp)) {
            Text(text = "Cambiar")
        }
    }
}


@Composable
fun DropdownMenu(options: List<String>, selectedOption: String, onChangeOption: (option: String) -> Unit, modifier: Modifier) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOption,
            onValueChange = { },
            label = { Text("Educacion", Modifier.background(Color.Transparent)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_book_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(25.dp)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
//            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            shape = shapes.medium,
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
//            modifier = Modifier.fillMaxWidth().exposedDropdownSize()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {Text(text = selectionOption)},
                    onClick = {
                        onChangeOption(selectionOption)
                        expanded = false
                    },
//                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


fun validateForm(name: String, lastName: String, birthDate: String, education: String): Boolean {
    return name.isNotEmpty() && lastName.isNotEmpty() && birthDate.isNotEmpty() && education.isNotEmpty()
}

fun logData(name: String, lastName: String, gender: String? = null, birthDate: String, education: String) {
    val tag = "[RESULT]"
    Log.i(tag, "Información personal: ")
    Log.i(tag, "Nombre: $name")
    Log.i(tag, "Apellido: $lastName")
    if (gender != null) Log.i(tag, "Genero: $gender")
    Log.i(tag, "Fecha de Nacimiento: $birthDate")
    Log.i(tag, "Educacion: $education")
}

