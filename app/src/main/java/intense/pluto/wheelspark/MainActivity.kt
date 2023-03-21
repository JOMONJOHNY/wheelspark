package intense.pluto.wheelspark

import android.os.Bundle
import androidx.compose.ui.res.painterResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import intense.pluto.wheelspark.ui.theme.WheelsParkTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WheelsParkTheme {
                Intro()
            }
        }
    }

    @Composable
    fun Intro() {
        val bgCar =painterResource(R.drawable.bgcar)
        val logo = painterResource(R.drawable.logo_large)

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White // Use a solid color instead of MaterialTheme for performance
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = bgCar,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = logo,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
