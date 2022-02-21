package com.metehanbolat.implementlistspinner

import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import kotlinx.coroutines.coroutineScope

@Composable
fun CountryTextField(
    label: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    expanded: Boolean = false,
    selectedCountry: Country? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onExpandedChange: () -> Unit
) {
    OutlinedTextField(
        value = if (selectedCountry == null) "" else "${selectedCountry.dialCode} ${selectedCountry.name}",
        onValueChange = {},
        modifier = modifier
            .expandable(menuLabel = label, onExpandedChange = onExpandedChange),
        readOnly = true,
        isError = isError,
        label = {
            Text(text = label)
        },
        colors = colors,
        shape = shape,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Spinner arrow",
                modifier = Modifier
                    .rotate(if (expanded) 180f else 0f)
            )
        }
    )
}

@Composable
fun Modifier.expandable(
    onExpandedChange: () -> Unit,
    menuLabel: String
) = pointerInput(Unit) {
    forEachGesture {
        coroutineScope {
            awaitPointerEventScope {
                var event: PointerEvent
                do {
                    event = awaitPointerEvent(PointerEventPass.Initial)
                } while (!event.changes.all {
                        it.changedToUp()
                    })
                onExpandedChange.invoke()
            }
        }
    }
}.semantics {
    contentDescription = menuLabel
    onClick {
        onExpandedChange()
        true
    }
}