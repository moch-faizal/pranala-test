package com.pranala.test.app.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.pranala.test.app.R
import com.pranala.test.app.extensions.navigateTo
import com.pranala.test.app.extensions.toDp
import com.pranala.test.app.extensions.toSp
import com.pranala.test.app.navigation.Routes
import com.pranala.test.app.ui.theme.BlackText
import com.pranala.test.app.ui.theme.CamptonFontFamily
import com.pranala.test.app.ui.theme.PrimaryColor

@Composable
fun WelcomeScreen(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .padding(horizontal = 40.toDp()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_welcome),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .size(420.toDp())
            )
            Spacer(modifier = Modifier.height(40.toDp()))
            Text(
                text = "Let's meet our summer \n coffee drinks",
                textAlign = TextAlign.Center,
                fontFamily = CamptonFontFamily,
                fontWeight = FontWeight(600),
                fontSize = 42.toSp(),
                color = BlackText
            )
            Spacer(modifier = Modifier.height(80.toDp()))
            Text(
                text = stringResource(R.string.desc),
                fontWeight = FontWeight(400),
                fontSize = 28.toSp(),
                lineHeight = 30.toSp(),
                fontFamily = CamptonFontFamily,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(80.toDp()))
            Button(modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                onClick = {
                    navController.navigateTo(
                        Routes.HomeScreen.routes,
                        navBackStackEntry
                    )
                }) {
                Text(
                    text = "Get Started",
                    modifier = Modifier.padding(horizontal = 20.toDp(), vertical = 20.toDp())
                )
            }
        }
    }
}