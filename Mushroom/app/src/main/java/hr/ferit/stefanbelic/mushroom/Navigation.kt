import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.ferit.stefanbelic.mushroom.presentation.HomeScreen.HomeScreen
import hr.ferit.stefanbelic.mushroom.Routes
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo
import hr.ferit.stefanbelic.mushroom.presentation.JestiveScreen.JestiveViewModel
import hr.ferit.stefanbelic.mushroom.presentation.OtrovneScreen.OtrovneViewModel
import hr.ferit.stefanbelic.mushroom.presentation.addMushroom.AddMushroomScreen
import hr.ferit.stefanbelic.mushroom.presentation.addMushroom.AddMushroomViewModel
import hr.ferit.stefanbelic.mushroom.ui.theme.MushroomTheme

@Composable
fun MushroomApp() {
    val navController = rememberNavController()

    val otrovneViewModel = hiltViewModel<OtrovneViewModel>()
    val jestiveViewModel = hiltViewModel<JestiveViewModel>()
    val addMushroomViewModel = hiltViewModel<AddMushroomViewModel>()

    MushroomTheme {
        NavHost(navController = navController, startDestination = Routes.HOME.routePath) {
            composable(Routes.HOME.routePath) {
                HomeScreen(navController)
            }
            composable(Routes.IZBORNIK.routePath) {
                Izbornik(navController)
            }
            composable(Routes.OTROVNE.routePath) {
                OtrovneScreen(otrovneViewModel.mushroomList, removeFromList = {
                    otrovneViewModel.removeFromLMushroomList(it)
                }, addMushroomItem = {
                    navController.navigate(
                        Routes.ADD_MAUHROOM.routePath.replace(
                            "{mushroomType}", "Otrovne"
                        )
                    )
                })
                otrovneViewModel.getOtrovneGljiveMushroomList()
            }
            composable(Routes.JESTIVE.routePath) {
                jestiveViewModel.getJestiveGljiveMushroomList()
                JestiveScreen(jestiveViewModel.jestiveMushroomList, removeFromList = {
                    jestiveViewModel.removeJestiveMushroom(it)
                }, addMushroomClicked = {
                    navController.navigate(
                        Routes.ADD_MAUHROOM.routePath.replace(
                            "{mushroomType}", "Jestive"
                        )
                    )
                })
            }

            composable(Routes.ADD_MAUHROOM.routePath) { backStackEntry ->

                val mushroomType = backStackEntry.arguments?.getString("mushroomType")
                addMushroomViewModel.addMoreData()

                AddMushroomScreen(addMushroomViewModel.addMushroomState, mushroomTitle = {
                    val mashroom = MashroomInfo(
                        title = it
                    )

                    if (mushroomType == "Jestive") {
                        addMushroomViewModel.addJestiveMushroom(mashroom)
                    } else if (mushroomType == "Otrovne") {
                        addMushroomViewModel.addOtrovneMushroom(mashroom)
                    }
                }, navBack = {
                    navController.popBackStack()
                })

            }

        }
    }
}