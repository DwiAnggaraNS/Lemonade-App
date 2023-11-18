package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        in 2..10 -> R.drawable.lemon_squeeze
        11 -> R.drawable.lemon_drink
        12 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    val TextResource = when(result) {
        1 -> R.string.lemon_tree_content_description
        in 2..10 -> R.string.lemon_content_description
        11 -> R.string.glass_oflemon_content_description
        12-> R.string.emptyglass_content_description
        else -> R.string.lemon_tree_content_description
    }

    CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor =  Color(red = 232, green = 235, blue = 52),
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text( text = "Lemonade App",
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                )
            }
        )
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(TextResource),
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color(red = 125, green = 200, blue = 160))
                .padding(50.dp)
                .clickable {
                    result++
                }
        )
        Text(text = stringResource(TextResource),
            modifier = Modifier.padding(20.dp).background(color = Color(red = 232, green = 235, blue = 52)),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp

        )
    }
    if (result>12){
        result = 0
    }
}

@Preview(showBackground = true)
@Composable
fun LemonAppPreview() {
    LemonadeTheme {
        LemonApp()
    }
}