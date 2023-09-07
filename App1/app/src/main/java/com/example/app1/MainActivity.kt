package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app1.ui.theme.App1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App1Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
/*Sirve para que la funcion tenga una interface*/
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    //Remember recordar el numero anterior y el mutablestateof el estado
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result){
        1->R.drawable.dice_1
        2->R.drawable.dice_2
        3->R.drawable.dice_3
        4->R.drawable.dice_4
        5->R.drawable.dice_5
        else ->R.drawable.dice_6
    }
    val imageResource1 = when(result){
        1->R.drawable.dice_2
        2->R.drawable.dice_3
        3->R.drawable.dice_4
        4->R.drawable.dice_5
        5->R.drawable.dice_6
        else ->R.drawable.dice_1
    }

    Column(modifier = modifier, horizontalAlignment =
    Alignment.CenterHorizontally){
        Image(painter = painterResource(id = imageResource),
            contentDescription = result.toString())
        Image(painter = painterResource(id = imageResource1),
            contentDescription = result.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { result=getRandomDiceImage()}) {
            Text(text = stringResource(R.string.Roll))
        }
    }
}

private fun getRandomDiceImage(): Int{
    return (1..6).random()
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Me gusta $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App1Theme {
        Greeting("Tania")
    }
}*/