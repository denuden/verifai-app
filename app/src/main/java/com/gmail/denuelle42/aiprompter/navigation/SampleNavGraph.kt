package com.gmail.denuelle42.aiprompter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.gmail.denuelle42.aiprompter.data.remote.models.SampleModel
import com.gmail.denuelle42.aiprompter.utils.parcelableType
import kotlin.reflect.typeOf


/**
 * Sample Navigation Graph
 */
fun NavGraphBuilder.addSampleNavGraph(
    navController: NavController
){
    navigation<RootGraphs.SampleGraph>(startDestination = SampleScreens.SampleNavigation){
//        Old-school NavType / parcelableType approach
        composable<SampleScreens.SampleDetailsNavigation>(typeMap = mapOf(typeOf<SampleModel>() to parcelableType<SampleModel>())) {
            val args = it.toRoute<SampleScreens.SampleDetailsNavigation>()
            val model = args.sampleModel
        }

        //Type-Safe Navigation (new Compose API)
//        composable<SampleScreens.SampleDetailsNavigation> {
//            val args = it.toRoute<SampleScreens.SampleDetailsNavigation>()
//            val model = args.sampleModel
//        }
        composable<SampleScreens.SampleNavigation> {
//            Screen(
//                onPopBackStack = { navController.popBackStack() },
//                onNavigate = { navController.navigate(it) },
//            )
        }
    }
}