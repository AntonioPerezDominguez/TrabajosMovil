package com.example.blocdenotas.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blocdenotas.R
import com.example.blocdenotas.TextFieldExample

class CrearNota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            
        }
    }
}
@Preview
@Composable
fun crearNota() {
    Column(){
        Row(){
            TextFieldExample("Titulo")
        }
        Divider()
        Row(){
            TextFieldExample("Descripcion")
        }
        Row(){
            ButtonShapeExample()
        }


    }
    Text(text = "Titulo")
}

@Composable
fun ButtonShapeExample() {
    Row() {
        Spacer(Modifier.size(8.dp))
        Button(onClick = { }, shape = RoundedCornerShape(50)) {
            Text("media")
        }
        Spacer(Modifier.size(8.dp))
        Button(onClick = { }, shape = RoundedCornerShape(50)) {
            Text("crear")
        }
    }
}