package com.example.imageapp.ui.presentance.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TextField(query:String,
              onValueChange :(String) ->Unit,
              onActionSearch :(String) -> Unit,
              onClose :() -> Unit,
              modifier: Modifier = Modifier) {

    androidx.compose.material3.TextField(
        value =query,
        onValueChange ={
            onValueChange(it)
        },
        placeholder = {
            Text(text = "Search image",
                style = MaterialTheme.typography.labelMedium)
        },
        trailingIcon = {
            IconButton(onClick = {
                if(query.isNotEmpty()){
                    onValueChange("")
                }
                onClose()

            }) {
                Icon(imageVector = Icons.Filled.Close,
                    contentDescription = "close")
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onActionSearch(query) }
        ),modifier=modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF0F0F0)
        )
    )

}