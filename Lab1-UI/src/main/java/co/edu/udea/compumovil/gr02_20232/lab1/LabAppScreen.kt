package co.edu.udea.compumovil.gr02_20232.lab1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr02_20232.lab1.ui.screens.ContactDataScreen
import co.edu.udea.compumovil.gr02_20232.lab1.ui.screens.PersonalDataScreen

enum class LabScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    PersonalData(title = R.string.personal_data),
    ContactData(title = R.string.contact_data),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lab1App(
    navController: NavHostController = rememberNavController()
) {
    Scaffold() {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LabScreen.PersonalData.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LabScreen.PersonalData.name) {
                PersonalDataScreen()
            }
            composable(route = LabScreen.ContactData.name) {
                ContactDataScreen()
            }
        }
    }
}