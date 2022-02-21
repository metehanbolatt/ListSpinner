package com.metehanbolat.implementlistspinner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun CountryPickerBottomSheet(
    title: @Composable () -> Unit,
    show: Boolean,
    onItemSelected: (country: Country) -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val countries = remember { countryList(context) }
    var selectedCountry by remember { mutableStateOf(countries[0]) }
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(key1 = show) {
        if (show) modalBottomSheetState.show() else modalBottomSheetState.hide()
    }

    LaunchedEffect(key1 = modalBottomSheetState.currentValue) {
        if (modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            onDismissRequest()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            title()
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(countries.size) { index ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                selectedCountry = countries[index]
                                onItemSelected(selectedCountry)
                            }
                            .padding(10.dp)
                    ) {
                        Text(
                            text = localeToEmoji(countryCode = countries[index].code)
                        )
                        Text(
                            text = countries[index].name,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .weight(2f)
                        )

                        Text(
                            text = countries[index].dialCode,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        content()
    }
}