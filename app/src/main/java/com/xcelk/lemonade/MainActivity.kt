package com.xcelk.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xcelk.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonadePage {

                }
            }
        }
    }
}

@Composable
fun LemonadePage(modifier: Modifier = Modifier, onClick: () -> Unit) {
    var currentStep by remember { mutableStateOf(1) }
    val imageId = when(currentStep){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val strDesc = when(currentStep){
        1 -> R.string.lemon_tree_content_desc
        2 -> R.string.lemon_content_desc
        3 -> R.string.lemon_glass_content_desc
        else -> R.string.empty_glass_content_desc
    }
    val clickNbr = (2..4).random()
    var clickedTimes = 0
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Row(modifier.height(48.dp)) {
            Text(text = stringResource(id = R.string.app_name),
                modifier
                    .background(Color(0xFFf9e44c))
                    .fillMaxWidth()
                    .fillMaxHeight())
        }
        Image(painter = painterResource(id = imageId), contentDescription = stringResource(id = strDesc),
            modifier
                .wrapContentSize()
                .weight(0.75f)
                .background(Color(0xFFc3ecd2)).clickable {
                    clickedTimes++
                    if (currentStep == 4){
                        currentStep = 1
                    } else if(currentStep == 2){
                        if(clickNbr == clickedTimes) {
                            currentStep = 3
                        }
                    } else{
                        currentStep++
                    }
                })
        Text(text = stringResource(id = strDesc),
            modifier
                .weight(0.25f)
                .wrapContentSize(), )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
       Surface(modifier = Modifier.fillMaxSize()) {
           LemonadePage() {}
       }
    }
}