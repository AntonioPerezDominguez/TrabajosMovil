package com.example.blocdenotas.pantalla

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blocdenotas.TextFieldExample
import com.example.blocdenotas.modelo.notas
import com.example.blocdenotas.modelo.notas.*


@Composable
fun pantallaPrincipal(){
    val lista_notas= listOf(
        nota1,
        nota2,
        nota3,
        nota4,
        nota5,
        nota6,
        nota7,
        nota8,
        nota9,
        nota10,
        nota11,
        nota12,
        nota13,
        nota14
    )
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ){
        item{
            Row{
                TextFieldExample("Buscar")
            }
        }
        items(lista_notas){item->
            itemRow(item)
            Divider()
        }
    }
}
@Composable
fun itemRow(item: notas){
    var masInfo = remember{
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .animateContentSize (
                animationSpec = tween(120,0, LinearEasing)
            )
            .padding(vertical = 8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = item.titulo,
                style=MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { masInfo.value=!masInfo.value}) {
                Icon(
                    if(masInfo.value) {
                        Icons.Default.Remove
                    } else {
                        Icons.Default.Add
                    },
                    contentDescription = "Mas informacion")
            }
        }
        if(masInfo.value){
            Row{
                Text(text = item.detalles)
            }
        }

    }

}