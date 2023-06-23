package com.teachboost.pichanga.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teachboost.pichanga.R

@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    textFieldValue: MutableState<String>,
    textLabel: String,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    paddingCustom: Dp = 16.dp,
    readOnly: Boolean = false
) {
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        backgroundColor = Color.White,
        focusedIndicatorColor = colorResource(id = R.color.green_jade),
        cursorColor = colorResource(id = R.color.green_dark)
    )
    TextField(
        readOnly = readOnly,
        modifier = Modifier
            .padding(paddingCustom)
            .fillMaxWidth()
            .height(56.dp),
        value = textFieldValue.value,
        onValueChange = { textFieldValue.value = it },
        label = {
            Text(text = textLabel, color = colorResource(id = R.color.green_jade) )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType
        ),
        colors = customTextFieldColors,
        visualTransformation = visualTransformation
    )
}