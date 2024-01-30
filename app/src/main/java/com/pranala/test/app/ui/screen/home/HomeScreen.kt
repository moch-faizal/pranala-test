package com.pranala.test.app.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.pranala.test.app.R
import com.pranala.test.app.extensions.toDp
import com.pranala.test.app.extensions.toSp
import com.pranala.test.app.ui.theme.BlackText
import com.pranala.test.app.ui.theme.CamptonFontFamily
import com.pranala.test.app.ui.theme.NeutralMediumGray
import com.pranala.test.app.ui.theme.PrimaryColor
import kotlin.math.sqrt

@Composable
fun HomeScreen(navController: NavController, navBackStackEntry: NavBackStackEntry) {
    val decimalInputRegex by lazy { Regex("^\\d+\$") }

    val string = remember { mutableStateOf("") }
    var primeNumbers by remember { mutableStateOf(listOf<Int>()) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.toDp(), horizontal = 60.toDp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_coffee_cup),
                    contentDescription = "Icon Coffee Cup",
                    modifier = Modifier.size(38.toDp()),
                    colorFilter = ColorFilter.tint(PrimaryColor)
                )
                Spacer(modifier = Modifier.width(12.toDp()))
                Text(
                    text = "Test Apps",
                    color = BlackText,
                    fontSize = 42.toSp(),
                    fontFamily = CamptonFontFamily,
                    fontWeight = FontWeight(600)
                )
            }
            Spacer(modifier = Modifier.height(20.toDp()))
            Column(modifier = Modifier.padding(horizontal = 60.toDp())) {
                Text(
                    text = "Bilangan Prima",
                    color = BlackText,
                    fontSize = 38.toSp(),
                    fontFamily = CamptonFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.toDp()))
                Text(
                    text = "Masukkan Angka untuk menghasilkan \nbilangan prima",
                    fontFamily = CamptonFontFamily,
                    fontSize = 28.toSp(),
                    lineHeight = 30.toSp(),
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(60.toDp()))
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.toDp())
                        .border(
                            BorderStroke(width = 1.toDp(), color = NeutralMediumGray),
                            shape = RoundedCornerShape(12.toDp())
                        ),
                    value = string.value,
                    cursorBrush = SolidColor(PrimaryColor),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { value ->
                        string.value =
                            if (value.isEmpty() || value.matches(decimalInputRegex)) value else return@BasicTextField
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.toDp())
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 20.toDp())
                                    .align(Alignment.CenterVertically)
                            ) {
                                if (string.value.isEmpty()) {
                                    Text(
                                        text = "Masukkan Bilangan Prima",
                                        color = NeutralMediumGray,
                                        fontSize = 24.toSp(),
                                    )
                                }
                                innerTextField()
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(40.toDp()))
                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        disabledContainerColor = NeutralMediumGray
                    ),
                    enabled = string.value.isNotEmpty(),
                    onClick = {
                        val number = string.value.toIntOrNull()
                        if (number != null && number > 1) {
                            generatePrimes(number) { newPrime ->
                                primeNumbers = primeNumbers + newPrime
                            }
                        }
                    }) {
                    Text(
                        text = "Generate Bilangan Prima",
                        modifier = Modifier.padding(horizontal = 20.toDp(), vertical = 20.toDp())
                    )
                }
                Spacer(modifier = Modifier.height(80.toDp()))
                LazyColumn(content = {
                    items(primeNumbers.size) {
                        Text(primeNumbers[it].toString())
                    }
                })
            }
        }
    }
}

private fun generatePrimes(upToNumber: Int, onPrimeGenerated: (Int) -> Unit) {
    var num = 2
    while (num <= upToNumber) {
        var isPrime = true
        val maxDivider = sqrt(num.toDouble()).toInt()
        for (divider in 2..maxDivider) {
            if (num % divider == 0) {
                isPrime = false
                break
            }
        }
        if (isPrime) {
            onPrimeGenerated(num)
        }
        num++
    }
}