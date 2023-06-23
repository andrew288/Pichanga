package com.teachboost.pichanga.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.teachboost.pichanga.R

@Composable
fun ButtonCustom(
    modifier: Modifier = Modifier,
    text: String,
    colorText: Color = Color.Black,
    onClick: () -> Unit,
){
    val customButtonColor = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.green_dark)
    )
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors =customButtonColor
    ) {
        Text(text = text, color = colorText)
    }
}