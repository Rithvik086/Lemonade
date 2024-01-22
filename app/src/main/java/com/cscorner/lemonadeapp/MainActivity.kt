package com.cscorner.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cscorner.lemonadeapp.ui.theme.LemonadeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(), // Fill the available height
                        verticalArrangement = Arrangement.Center, // Center the content vertically
                        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
                    ) {
                        Lemonade()
                    }
                }
            }
        }
    }

    @Composable
    fun Lemonade() {
        var currentStep by remember { mutableStateOf(1) }

        var squeezeCount by remember { mutableStateOf(0) }
        squeezeCount = if (currentStep == 1) (2..4).random() else 0
        when(currentStep){

            1 -> { LemonTree(info = stringResource(R.string.lemonTree)) { newStep ->
                currentStep = newStep
            }
            }
            2-> {Lemon(info = stringResource(R.string.squeezeLemon)) { newStep ->
                currentStep = newStep
            }
            }
            3 ->{ LemonDrink(info = stringResource(R.string.LemonDrink)) { newStep ->
                currentStep = newStep
            }
            }
            4 ->{LemonRestart(info = stringResource(R.string.LemonRestart)) { newStep ->
                currentStep = newStep
            }
            }
        }






    }

    @Composable
    fun LemonTree(info: String, onStepChange: (Int) -> Unit) {
        val image = painterResource(id = R.drawable.lemon_tree)
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = info,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center

            )

            Button(onClick = { onStepChange(2)},) {
                Column {
                    Text(stringResource(id = R.string.tap))



                }

            }
        }
    }


    @Composable
    fun Lemon(info: String, onStepChange: (Int) -> Unit) {
        val image = painterResource(id = R.drawable.lemon_squeeze)
        var tapCount by remember { mutableIntStateOf(0) }
        var squeezeCount by remember { mutableIntStateOf(0) }

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)

            )
            Text(
                text = info,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center
            )
            Button(onClick = {
                tapCount++
                // Check if the tap count matches the randomly generated squeeze count
                if (tapCount == squeezeCount) {
                    onStepChange(3) // Move to step 3
                }
            }) {
                Text(stringResource(id = R.string.tap))
            }
        }

        // If the step changes to 2, generate a new random squeeze count
        DisposableEffect(Unit) {
            if (tapCount == 0) {
                squeezeCount = (2..4).random()
            }
            onDispose { }
        }
    }

// ... (Remaining code remains unchanged)

    @Composable
    fun LemonDrink(info: String, onStepChange: (Int) -> Unit) {
        val image = painterResource(id = R.drawable.lemon_drink,)
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(start = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = info,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center

            )
            Button(onClick = { onStepChange(4)},) {
                Column {
                    Text(stringResource(id = R.string.tap))

                }

            }

        }

    }
    @Composable
    fun LemonRestart(info: String, onStepChange: (Int) -> Unit){
        val image = painterResource(id = R.drawable.lemon_restart)

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(start = 27.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = info,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center

            )
            Button(onClick = { onStepChange(1)},) {
                Column {
                    Text(stringResource(id = R.string.tap)
                    )

                }

            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        LemonadeAppTheme {
            Lemonade()
        }
    }
}