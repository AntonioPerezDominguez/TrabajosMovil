package com.example.blocdenotas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blocdenotas.pantalla.pantallaPrincipal
import com.example.blocdenotas.ui.theme.CrearNota

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            verContenido()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun verContenido(){
    Scaffold(
        topBar = { Toolbar()},
        content = { pantallaPrincipal() },
        floatingActionButton = {FAB()},
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun FAB(){
    val context= LocalContext.current
    FloatingActionButton(onClick = {
        //Toast.makeText(context,"Nueva nota",Toast.LENGTH_SHORT).show()
        val intent=Intent(context, CrearNota::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }){
        Text(text = "+")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldExample(nombre: String) {
    var name by remember {
        mutableStateOf("")
    }
    TextField(

        value = name,
        onValueChange = { name = it },
        modifier = Modifier.padding(vertical=35.dp)
            .fillMaxWidth(),
        label = { Text(nombre) },
        leadingIcon = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription="Boton para buscar nota"
                )
            }
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
){
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(title = { Text(text = "NOTAS") },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorito")
            }
            //IconButton(onClick = { }) {
              //  Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
            //}
            IconButton(onClick = {menuExpanded = !menuExpanded }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
            ) {
                // 6
                DropdownMenuItem(
                    text = {
                        Text("Ordenar por fecha asc ")
                    },
                    onClick = { /* TODO */ },
                )
                DropdownMenuItem(
                    text = {
                        Text("ordenar por fecha desc")
                    },
                    onClick = { /* TODO */ },
                )
                DropdownMenuItem(
                    text = {
                        Text("configuracion")
                    },
                    onClick = { /* TODO */ },
                )
            }
        },
        modifier = modifier
    )

}










