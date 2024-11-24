package com.example.socialcomposeapp.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.socialcomposeapp.navigation.DestinationScreen


data class BottomNavigationItem(
    val route: DestinationScreen,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun CustomBottomNavigationBar(
    navController: NavHostController,
    onCenterClick : () -> Unit = {}
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val navigationItems by remember { mutableStateOf(listOf(
        BottomNavigationItem(
            route = DestinationScreen.HomeScreenObj,
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            route = DestinationScreen.SearchScreenObj,
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unSelectedIcon = Icons.Outlined.Search
        ),
        BottomNavigationItem(
            route = DestinationScreen.MessageScreenObj,
            title = "Message",
            selectedIcon = Icons.AutoMirrored.Filled.Chat,
            unSelectedIcon = Icons.AutoMirrored.Outlined.Chat
        ),
        BottomNavigationItem(
            route = DestinationScreen.ProfileScreenObj,
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unSelectedIcon = Icons.Outlined.Person
        )
    )) }


    Box(
        modifier = Modifier
            .height(80.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            color = Color.White,
            border = BorderStroke(2.dp, Color(0xff440E3F)),
            shape = RoundedCornerShape(34.dp),
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                navigationItems.forEachIndexed { index, item ->
                    val currentDestination = navBackStackEntry?.destination
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.route == item.route::class.qualifiedName
                    } == true

                    BadgedBox(
                        badge = {
                            if(item.badgeCount != null){
                                Badge{
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        },
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = remember{
                                {
                                    if (item.route::class.qualifiedName != navController.currentDestination?.route) {
                                        navController.navigate(item.route) {
                                            launchSingleTop = true
                                            restoreState = true
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    ) {
                        Icon(
                            imageVector = if(isSelected) item.selectedIcon else item.unSelectedIcon,
                            contentDescription = item.title,
                            tint = Color(0xFF692663),
                            modifier = Modifier.size(28.dp)
                        )

                    }
                    if(index == 1){
                        Spacer(modifier = Modifier.width(60.dp))
                    }
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(2.dp, Color(0xFFEFEFEF)),
            modifier = Modifier
                .size(64.dp)
                .offset(y = (-14).dp),
            color = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            FloatingActionButton(
                onClick = onCenterClick,
                contentColor = Color(0xFF692663),
                modifier = Modifier.size(80.dp),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFF692663) // Icon color
                )
            }
        }
    }
}
