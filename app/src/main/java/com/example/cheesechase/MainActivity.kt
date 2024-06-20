package com.example.cheesechase

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cheesechase.ui.theme.CheeseChaseTheme
import com.example.cheesechase.ui.theme.HighScore
import kotlinx.coroutines.delay

var choice by mutableStateOf(0)
var win by mutableStateOf(0)
var distance by mutableStateOf(0)
var ad by mutableStateOf(0)
var score by mutableStateOf(0)
var dummyscore by mutableStateOf(0)
var highscore by mutableStateOf(0)
var highscore2 by mutableStateOf(0)
var reward by mutableStateOf(0)
val font4= Font(R.font.timerfont)
var cheeses by mutableStateOf(0)
var end3 by mutableStateOf(false)
var catch3 by mutableStateOf(0)
var revive by mutableStateOf(false)
var tilt by mutableStateOf(0f)

class MainActivity : ComponentActivity() {

//    private lateinit var sensorManager: SensorManager
//    private lateinit var gyroscopeSensor: Sensor
//    private lateinit var gyroscopeEventListener: SensorEventListener

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!!

        setContent {

//            DisposableEffect(Unit) {
//                gyroscopeEventListener = object : SensorEventListener {
//                    override fun onSensorChanged(event: SensorEvent?) {
//                        event?.let {
//                            tilt = it.values[2]
//                        }
//                    }
//
//                    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
//                }
//
//                sensorManager.registerListener(
//                    gyroscopeEventListener,
//                    gyroscopeSensor,
//                    SensorManager.SENSOR_DELAY_NORMAL
//                )
//
//                onDispose {
//                    sensorManager.unregisterListener(gyroscopeEventListener)
//                }
//            }

            remember {
                choice
                win
                distance
                ad
                score
                highscore
                reward
                cheeses
                end3
                catch3
                revive
            }

            CheeseChaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController= rememberNavController()
                    val context= LocalContext.current
                    val preferenceManager=remember{ HighScore(context) }
                    val highscore_background= painterResource(id = R.drawable.burnt_paper)
                    val font2= Font(R.font.mont)
                    val font3= Font(R.font.typewriter)
                    var timeLeft by remember { mutableStateOf(5) }
                    val cheese= painterResource(id = R.drawable.cheese2)
                    val tomandjerry= painterResource(id = R.drawable.tomjerry)
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.tap))

                    NavHost(navController = navController, startDestination = "home_page") {
                        composable(
                            "home_page",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left ,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            }
                        ){
                            HomePage(navController)
                        }
                        composable(
                            "second_page",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            }
                        ){
                            SecondPage(navController)
                        }
                        composable(
                            "game_page",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            }
                        ){
                            GamePage(navController)
                        }
                        composable(
                            "game_page2",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            }
                        ){
                            GamePage2(navController)
                        }
                        composable(
                            "game_page3",
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            }
                        ){
                            GamePage3(navController)
                        }
                    }

                    if(ad==1){

                        val play_again= painterResource(id = R.drawable.replay)
                        val game_over= painterResource(id = R.drawable.game_done)
                        val home= painterResource(id = R.drawable.home)
                        val font1= Font(R.font.guavacandy)
                        val font2= Font(R.font.mont)
                        val font3= Font(R.font.merich)
                        val ribbon= painterResource(id = R.drawable.textribbon)

                        AlertDialog(
                            onDismissRequest = { /*TODO*/ },
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(60.dp))
                                        .border(
                                            width = 5.dp,
                                            color = Color.Black,
                                            RoundedCornerShape(60.dp)
                                        )
                                        .alpha(0.95f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xff4a2b7a)
                                    )
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp)
                                            .clip(RoundedCornerShape(40.dp))
                                            .border(
                                                width = 5.dp,
                                                color = Color.Black,
                                                RoundedCornerShape(40.dp)
                                            ),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(0xffffd200)
                                        ),
                                        elevation = CardDefaults.cardElevation(
                                            defaultElevation = 5.dp
                                        ),
                                    ) {
                                        Box {
                                            Image(painter = tomandjerry, contentDescription = null, modifier = Modifier
                                                .size(350.dp)
                                                .alpha(0.8f))
                                            Column {
                                                Text(
                                                    text = if(win==1) "VICTORY" else if(win==0) "DEFEAT" else "",
                                                    fontSize = 65.sp,
                                                    fontFamily = FontFamily(font1),
                                                    modifier = Modifier
                                                        .padding(top = 26.dp)
                                                        .offset(x = if (win == 1) 29.dp else if (win == 0) 39.dp else 0.dp),
                                                    style = TextStyle(
                                                        brush = Brush.verticalGradient(
                                                            listOf(Color.Red, Color(0xffed7014))
                                                        ),
                                                        shadow = Shadow(color = Color.Black, Offset(x=6f,y=6f), blurRadius = 6f)
                                                    )
                                                )
                                                if(choice==3){
                                                    Box(
                                                        contentAlignment = Alignment.TopEnd
                                                    ) {
                                                        Button(
                                                            onClick = { /*TODO*/ },
                                                            modifier = Modifier
                                                                .padding(end = 20.dp, bottom = 7.dp)
                                                                .offset(x = 185.dp, y = 10.dp),
                                                            colors = ButtonDefaults.buttonColors(
                                                                containerColor = Color.White
                                                            ),
                                                            border = BorderStroke(3.5.dp, Color.DarkGray)
                                                        ) {
                                                            Image(painter = cheese, contentDescription = null, modifier = Modifier
                                                                .size(20.dp)
                                                                .offset(x = -(8).dp))
                                                            Text(text = "${cheeses}", fontSize = 20.sp, fontFamily = FontFamily(font2), color = Color.Black)
                                                        }
                                                    }
                                                }
                                                Column(
                                                    modifier = Modifier
                                                        .offset(y = 20.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Text(
                                                        text = "YOUR SCORE",
                                                        fontSize = 20.sp,
                                                        fontFamily = FontFamily(font2),
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.Black,
                                                        modifier = Modifier
                                                            .offset(x = 36.dp)
                                                            .alpha(0.9f)
                                                    )
                                                    Row {
                                                        Button(
                                                            onClick = { /*TODO*/ },
                                                            modifier = Modifier
                                                                .size(
                                                                    width = 200.dp,
                                                                    height = 65.dp
                                                                )
                                                                .offset(x = 37.dp)
                                                                .padding(bottom = 15.dp)
                                                                .alpha(0.6f),
                                                            elevation = ButtonDefaults.buttonElevation(
                                                                defaultElevation = 3.dp
                                                            ),
                                                            colors = ButtonDefaults.buttonColors(
                                                                containerColor = Color.White
                                                            )
                                                        ){}
                                                    }
                                                    Text(
                                                        text = "${score}",
                                                        fontSize = 30.sp,
                                                        fontFamily = FontFamily(font2),
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.Black,
                                                        modifier = Modifier
                                                            .offset(x=38.dp,y=-(58).dp)
                                                    )
                                                }
                                                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(x=34.dp)) {
                                                    if(choice==3){
                                                        Button(
                                                            onClick = {
                                                                if(cheeses!=0){
                                                                    cheeses-=1
                                                                    catch3=0
                                                                    end3=false
                                                                    revive=true
                                                                    ad=0
                                                                }
                                                            },
                                                            elevation = ButtonDefaults.buttonElevation(
                                                                defaultElevation = 8.dp
                                                            ),
                                                            modifier = Modifier.padding(bottom = 12.dp),
                                                            colors = ButtonDefaults.buttonColors(
                                                                containerColor = if(cheeses==0) Color.Gray else Color(0xff3e8ede)
                                                            ),
                                                            border = BorderStroke(4.dp, if(cheeses==0) Color.DarkGray else Color(0xff1c05b3))
                                                        ) {
                                                            Text(
                                                                text = "REVIVE",
                                                                fontSize = 25.sp,
                                                                fontFamily = FontFamily(font2),
                                                                modifier = Modifier.padding(vertical = 2.dp, horizontal = 35.dp),
                                                                color = Color.Black
                                                            )
                                                        }
                                                    }
                                                    Button(
                                                        onClick = {
                                                            ad=0
                                                            win=-1
                                                            score=0
                                                            dummyscore=0
                                                            reward=0
                                                            cheeses=0
                                                            end3=false
                                                            revive=false
                                                            catch3=0
                                                            if(choice==1){
                                                                navController.navigate("game_page")
                                                            }else if(choice==2){
                                                                navController.navigate("game_page2")
                                                            }else if(choice==3){
                                                                navController.navigate("game_page3")
                                                            }
                                                        },
                                                        elevation = ButtonDefaults.buttonElevation(
                                                            defaultElevation = 8.dp
                                                        ),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = Color(0xff299617)
                                                        ),
                                                        border = BorderStroke(4.dp, Color(0xff005c29))
                                                    ) {
                                                        Text(
                                                            text = "PLAY AGAIN",
                                                            fontSize = 25.sp,
                                                            fontFamily = FontFamily(font2),
                                                            modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
                                                            color = Color.Black
                                                        )
                                                    }
                                                    Button(
                                                        onClick = {
                                                            ad=0
                                                            win=-1
                                                            score=0
                                                            dummyscore=0
                                                            distance=0
                                                            choice=0
                                                            reward=0
                                                            cheeses=0
                                                            end3=false
                                                            revive=false
                                                            catch3=0
                                                            navController.navigate("home_page")
                                                        },
                                                        modifier = Modifier.padding(top = 12.dp,bottom = 30.dp),
                                                        elevation = ButtonDefaults.buttonElevation(
                                                            defaultElevation = 8.dp
                                                        ),
                                                        colors = ButtonDefaults.buttonColors(
                                                            containerColor = Color(0xff299617)
                                                        ),
                                                        border = BorderStroke(4.dp, Color(0xff005c29))
                                                    ) {
                                                        Text(
                                                            text = "HOME",
                                                            fontSize = 25.sp,
                                                            fontFamily = FontFamily(font2),
                                                            modifier = Modifier.padding(horizontal = 40.dp),
                                                            color = Color.Black
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else if(ad==2){
                        AlertDialog(
                            onDismissRequest = { ad=0 }
                        ){
                            Box( contentAlignment = Alignment.Center, modifier = Modifier.size(width=350.dp, height = 263.dp)) {
                                Image(painter = highscore_background, contentDescription = null, modifier = Modifier.fillMaxSize())
                                Text(
                                    text = "HIGHSCORE",
                                    fontSize = 36.sp,
                                    fontFamily = FontFamily(font3),
                                    modifier = Modifier
                                        .offset(y = -(90).dp)
                                        .padding(top = 15.dp),
                                    color = Color.Black
                                )
                                Column {
                                    Text(
                                        text = "NORMAL MODE :${preferenceManager.getData("normal","")}",
                                        fontSize = 23.sp,
                                        fontFamily = FontFamily(font3),
                                        modifier = Modifier.padding(top=55.dp,bottom=8.dp),
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "HACKER MODE :${preferenceManager.getData("hacker","")}",
                                        fontSize = 23.sp,
                                        fontFamily = FontFamily(font3),
                                        modifier = Modifier.padding(8.dp),
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "HACKER ++ MODE :${preferenceManager.getData("hacker++","")}",
                                        fontSize = 23.sp,
                                        fontFamily = FontFamily(font3),
                                        modifier = Modifier.padding(8.dp),
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }else if(ad==3){
                        AlertDialog(
                            onDismissRequest = { ad=0 }
                        ) {
                            val jump= painterResource(id = R.drawable.jump)
                            val cone= painterResource(id = R.drawable.cone)
                            val barrier= painterResource(id = R.drawable.barrier)
                            val wood= painterResource(id = R.drawable.wood_block)
                            val powerup= painterResource(id = R.drawable.powerup)
                            val trap= painterResource(id = R.drawable.mouse_trap)
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(width=400.dp, height = 710.dp)
                            ){
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(40.dp))
                                        .border(width = 8.dp, color = Color.Black, shape = RoundedCornerShape(40.dp)),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xff4a2b7a)
                                    )
                                ) {
                                    Column(modifier=Modifier.offset(y=70.dp)) {
                                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                                            Text(
                                                text = "INSTRUCTIONS",
                                                fontSize = 35.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.ExtraBold,
                                                modifier= Modifier
                                                    .offset(y=-(40).dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            LottieAnimation(composition = composition, modifier = Modifier.size(105.dp).offset(y=20.dp), iterations = LottieConstants.IterateForever)
                                            Text(
                                                text = "TAP TO NAVIGATE, TAP ON THE SAME LANE TO",
                                                fontSize = 18.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(x = 15.dp, y = 110.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Image(painter = jump, contentDescription = null,modifier= Modifier
                                                .size(95.dp)
                                                .offset(x = 95.dp, y = 115.dp))
                                            Text(
                                                text = "---------------------",
                                                fontSize = 22.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 170.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Text(
                                                text = "OBSTACLES",
                                                fontSize = 22.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 195.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Row(modifier=Modifier.offset(x=92.dp,y=-(15).dp)) {
                                                Image(
                                                    painter = cone,
                                                    contentDescription = null,
                                                    modifier= Modifier
                                                        .size(65.dp)
                                                        .offset(x = -(95).dp, y = 260.dp)
                                                )
                                                Spacer(modifier = Modifier.padding(15.dp))
                                                Image(
                                                    painter = barrier,
                                                    contentDescription = null,
                                                    modifier= Modifier
                                                        .size(70.dp)
                                                        .offset(x = -(95).dp, y = 260.dp)
                                                )
                                                Spacer(modifier = Modifier.padding(15.dp))
                                                Image(
                                                    painter = wood,
                                                    contentDescription = null,
                                                    modifier= Modifier
                                                        .size(70.dp)
                                                        .offset(x = -(95).dp, y = 260.dp)
                                                )
                                            }
                                            Text(
                                                text = "---------------------",
                                                fontSize = 22.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 303.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Text(
                                                text = "TRAP",
                                                fontSize = 23.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 330.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Image(
                                                painter = trap,
                                                contentDescription = null,
                                                modifier= Modifier
                                                    .size(60.dp)
                                                    .offset(y = 385.dp)
                                            )
                                            Text(
                                                text = "---------------------",
                                                fontSize = 22.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 435.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Text(
                                                text = "POWER-UP",
                                                fontSize = 23.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold,
                                                modifier= Modifier
                                                    .offset(y = 465.dp)
                                                    .padding(10.dp),
                                                fontFamily = FontFamily.Monospace
                                            )
                                            Image(
                                                painter = powerup,
                                                contentDescription = null,
                                                modifier= Modifier
                                                    .size(65.dp)
                                                    .offset(y = 520.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (reward==1){
                        LaunchedEffect(key1 = timeLeft) {
                            while (timeLeft>0){
                                delay(1000L)
                                timeLeft--
                            }
                            timeLeft=5
                            reward=0
                        }
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd){
                            if(timeLeft!=0){
                                Text(
                                    text = "$timeLeft",
                                    fontFamily = FontFamily(font4),
                                    color = Color.White,
                                    fontSize = 60.sp,
                                    modifier = Modifier.offset(x=-(10).dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}