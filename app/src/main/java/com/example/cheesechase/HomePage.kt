package com.example.cheesechase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cheesechase.ui.theme.HighScore
import java.nio.file.WatchEvent

@Composable
fun HomePage(navController: NavController){

    val font= Font(R.font.jungleadventurer)
    val cheese= painterResource(id = R.drawable.cheese)
    val mountain= painterResource(id = R.drawable.mountain)
    val highscore= painterResource(id = R.drawable.highscore)
    val font2= Font(R.font.mont)
    val context= LocalContext.current
    val preferenceManager=remember{HighScore(context)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xfffeeb23)
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        ) {
            Column() {
                Text(
                    text = "CHEESE",
                    fontSize = 110.sp,
                    fontFamily = FontFamily(font),
                    style = TextStyle(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xff5333ae), Color(0xff4a2b7a))
                        ),
                        shadow = Shadow(color = Color.Black, Offset(x=6f,y=6f), blurRadius = 8f)
                    )
                )
                Text(
                    text = "CHASE",
                    fontSize = 110.sp,
                    fontFamily = FontFamily(font),
                    modifier = Modifier.offset(x=16.dp),
                    style = TextStyle(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xff5333ae), Color(0xff4a2b7a))
                        ),
                        shadow = Shadow(color = Color.Black, Offset(x=6f,y=6f), blurRadius = 8f)
                    )
                )
            }
        }

        Box(modifier = Modifier
            .align(Alignment.Center)
            .offset(x = 5.dp, y = 85.dp)) {
            Image(
                painter = cheese,
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp)
                    .alpha(0.8f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xffed7014)
                        ),
                        startY = 1000f
                    )
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center),
        ) {
            Column {
                Button(
                    onClick = { navController.navigate("second_page") },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(x = 7.dp, y = 90.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffed7014)
                    ),
                    border = BorderStroke(6.dp, Color(0xffdd571c)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 53.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 20.dp)
                            .offset(x = 1.dp, y = 3.dp),
                        color = Color.Black,
                        fontFamily = FontFamily(font2)
                    )
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomStart)
            .padding(vertical = 40.dp, horizontal = 20.dp)
        ) {
            Button(
                onClick = {
                    ad=2
                },
                modifier = Modifier.size(85.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff299617)
                ),
                border = BorderStroke(4.dp, Color(0xff005c29))
            ) {}
            Image(painter = highscore, contentDescription = null, modifier = Modifier
                .size(60.dp)
                .offset(x = 11.dp, y = 11.dp))
            Button(
                onClick = {
                    ad=3
                },
                modifier = Modifier
                    .size(85.dp)
                    .offset(x = 287.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff299617)
                ),
                border = BorderStroke(4.dp, Color(0xff005c29))
            ) {}
            Text(text = "?", fontSize = 55.sp, modifier = Modifier.offset(x=314.dp,y=11.dp), fontWeight = FontWeight.Bold,fontFamily = FontFamily(font2), color = Color.Black)
        }
    }
}