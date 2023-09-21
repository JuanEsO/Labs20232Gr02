package co.edu.udea.compumovil.gr02_20232.lab1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr02_20232.lab1.ui.screens.ContactDataScreen
import co.edu.udea.compumovil.gr02_20232.lab1.ui.screens.PersonalDataScreen

enum class LabScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    PersonalData(title = R.string.personal_data),
    ContactData(title = R.string.contact_data),
}


@Composable
fun AppBar(
    currentScreen: LabScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Composable
fun Lab1App(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LabScreen.valueOf(
        backStackEntry?.destination?.route ?: LabScreen.Start.name
    )
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {innerPadding ->
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