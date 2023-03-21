package intense.pluto.wheelspark

import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import intense.pluto.wheelspark.ui.theme.WheelsParkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelsParkTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("intro") { IntroScreen{navController.navigate("login")} }
                    composable("login") { LoginScreen() }
                }
            }
        }
    }



    @Composable
    fun IntroScreen(onclick: () -> Unit) {
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
                    PWButton(modifier = Modifier.width(300.dp), text = "Explore",onclick)
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


@Preview
@Composable
fun LoginScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.bg2),
            contentDescription=null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Column(Modifier.fillMaxSize()) {
            ProviderButton(providername = "Google",R.drawable.google)
            ProviderButton(providername = "Facebook",R.drawable.fb,Color(0xFF485A96))
            ProviderButton(providername = "Twitter",R.drawable.twitter,color=Color(0xFF0CB6FF))
        }
    }
}
@Composable
fun ProviderButton(providername:String="Google",@DrawableRes id: Int,color: Color=Color.White){
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
            , modifier = Modifier.width(320.dp)
    ){
        Row(
            Modifier.fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(modifier= Modifier
                .width(30.dp),painter = painterResource(id = id),contentDescription = null)

            Text(modifier = Modifier.padding(start = 30.dp), text = "Continue with $providername", style = TextStyle(fontWeight = W700, fontSize = 18.sp))
        }
           }
}





