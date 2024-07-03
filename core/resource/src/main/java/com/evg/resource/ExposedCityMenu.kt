package com.evg.resource

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evg.resource.model.CityUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedCityMenu(
    modifier: Modifier,
    listCities: List<CityUI>?,
    onSelect: (CityUI) -> Unit,
    onEdit: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedText by rememberSaveable { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedText,
            onValueChange = { newText ->
                selectedText = newText
                onEdit(newText)
            },
            label = { Text(text = "City") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .menuAnchor()
        )

        listCities?.let { cities ->
            if (selectedText.length < 3 && listCities.size > 100) return@ExposedDropdownMenuBox

            val filteredOptions =
                cities.filter { it.name.contains(selectedText, ignoreCase = true) }
            if (filteredOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { }
                ) {
                    filteredOptions.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item.name) },
                            onClick = {
                                selectedText = item.name
                                expanded = false
                                onSelect(item)
                            }
                        )
                    }
                }
            }
        }
    }

}