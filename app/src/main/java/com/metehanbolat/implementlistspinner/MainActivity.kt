package com.metehanbolat.implementlistspinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehanbolat.implementlistspinner.ui.theme.ImplementListSpinnerTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImplementListSpinnerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Country list spinner",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        CountryList()
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CountryList() {
    Box {
        var expanded by remember { mutableStateOf(false) }
        var selectedCountry by remember { mutableStateOf<Country?>(null) }

        CountryPickerBottomSheet(
            title = {
                Text(
                    text = "Select Country",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            },
            onItemSelected = {
                selectedCountry = it
                expanded = false
            },
            show = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            CountryTextField(
                label = "Select Country",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.TopCenter),
                expanded = expanded,
                selectedCountry = selectedCountry
            ) {
                expanded = !expanded
            }
        }
    }
}

