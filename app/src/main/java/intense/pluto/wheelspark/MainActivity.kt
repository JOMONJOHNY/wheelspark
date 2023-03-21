package intense.pluto.wheelspark

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import intense.pluto.wheelspark.ui.theme.WheelsParkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelsParkTheme {
                IntroScreen()
//                val navController = rememberNavController()
//                NavHost(
//                    navController = navController,
//                    startDestination = "first_screen"
//                ) {
//                    composable("first_screen") {
//                        // first screen
//                    }
//                    composable("second_screen") {
//                        // second screen
//                    }
//                }
            }
        }
    }

//    @Composable
//    fun MyApp() {
//        val navController = rememberNavController()
//        NavHost(navController = navController, startDestination = "home") {
//            composable("home") { HomeScreen() }
//            composable("details") { DetailsScreen() }
//        }
//    }


    @Composable
    fun IntroScreen() {
        val bgCar = remember { BitmapFactory.decodeResource(resources, R.drawable.bgcar).asImageBitmap() }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White // Use a solid color instead of MaterialTheme for performance
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                   bitmap = bgCar,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FadeInImagePreview()
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                   contentAlignment = Alignment.BottomCenter
                ){
                    PWButton(modifier = Modifier.width(300.dp), text = "Explore"){}
                }
            }
        }
    }
}

@Composable
fun PWButton(modifier:Modifier,text:String,onclick:()->Unit){

        Button(
            modifier = modifier
                .shadow(elevation = 10.dp)
        , onClick = onclick,
            shape = RoundedCornerShape(8.dp),
            elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 5.dp,
                disabledElevation = 0.dp
            ),
            contentPadding = PaddingValues(10.dp,20.dp)
           , colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9314))
        ) {
            Text(text, style = TextStyle(fontWeight = FontWeight.W700, fontSize = 20.sp))
    }

}

@Composable
fun FadeInImagePreview() {
    var isVisible by remember { mutableStateOf(false) }
    val fadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(2000),
            initialAlpha = 0.0f
        )
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn,
    ) {
        Image(
            modifier= Modifier
                .width(200.dp)
                .height(200.dp),
            painter =  painterResource(R.drawable.logo_large),
            contentDescription = null

        )
    }
    LaunchedEffect(Unit) {
        isVisible = true
    }
}





