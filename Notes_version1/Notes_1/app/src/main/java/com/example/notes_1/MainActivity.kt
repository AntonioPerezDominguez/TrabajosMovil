package com.example.notes_1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.utils.widget.MotionLabel
import com.example.notes_1.ui.theme.Notes_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Notes_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Notes()
                }
            }
        }
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notes(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar()
        },
        content = {
            LazyColumn{
                items(100){
                    CenterAlignedTopAppBar(title = {Text(text = "Hola")})
                }
            }
        },
        floatingActionButton = {
            FloatButton()
        },
        bottomBar = {
            BottomAppBar()
        }
    )
}

@Composable
fun FloatButton(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(R.drawable.notes),
        contentDescription = null,
        modifier.size(60.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "NOTES",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                color = Color.Green
            )
    },
        modifier=modifier
    )
}
/*
@Composable
fun TextInput(
    @StringRes label: Int,
    value: String,
    onValueChanged:(String)->Unit,
    modifier: Modifier = Modifier
){
    TextField(
        label = { Text(text = stringResource(id = ))}
    )
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title ={
            Row {
                Button(onClick = { }) {
                    Image(
                        painter = painterResource(R.drawable.notes),
                        contentDescription = null,
                        Modifier.size(30.dp),
                    )
                }
                Spacer(modifier = Modifier.size(50.dp))
                Button(onClick = { }) {
                    Image(
                        painter = painterResource(R.drawable.pendientes),
                        contentDescription = null,
                        Modifier.size(30.dp),
                    )
                }
            }
        },
        modifier = modifier,
    )
}
