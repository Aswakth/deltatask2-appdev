package com.example.cheesechase

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPage(navController: NavController){

    val wood= painterResource(id = R.drawable.wood)
    val font2= Font(R.font.mont)
    var isExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var isSelected by remember { mutableStateOf(false) }
    val tom= painterResource(id = R.drawable.tom)
    val jerry= painterResource(id = R.drawable.jerry)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xfffeeb23)
            )
    ){

        if(!isSelected){
            Image(painter = wood, contentDescription = null, modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .offset(y = -(320).dp))
            Text(
                text = "SELECT A MODE",
                fontSize = 35.sp,
                modifier = Modifier.offset(x=64.dp,y=77.dp),
                fontFamily = FontFamily(font2),
                style = TextStyle(
                    shadow = Shadow(color = Color.Black, Offset(x=2f,y=2f), blurRadius = 2f)
                ),
                color = Color.Black
            )
        }

        if(isSelected){
            Box(modifier = Modifier.padding(20.dp)) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(85.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff5333ae)
                    ),
                    border = BorderStroke(4.dp, Color(0xff4a006a))
                ) {}
                Image(painter = tom, contentDescription = null, modifier = Modifier
                    .size(118.dp)
                    .offset(x = -(17).dp, y = -(16).dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(85.dp)
                        .offset(x = 286.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff00563f)
                    ),
                    border = BorderStroke(4.dp, Color(0xff004225))
                ) {}
                Image(painter = jerry, contentDescription = null, modifier = Modifier
                    .size(118.dp)
                    .offset(x = 270.dp, y = -(14).dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -(80).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        choice = 1
                        isExpanded=true
                        isSelected=true
                    },
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(choice==1) Color.LightGray else Color(0xff299617)
                    ),
                    border = BorderStroke(4.dp, if(choice==1) Color.DarkGray else Color(0xff005c29))
                ) {
                    Text(text = "NORMAL", fontSize = 30.sp, fontFamily = FontFamily(font2), color = Color.Black, modifier = Modifier
                        .offset(x = 1.dp, y = 2.dp)
                        .padding(vertical = 2.dp, horizontal = 35.dp))
                }
                if (isExpanded && choice==1){
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                }
                AnimatedVisibility(visible = (choice==1 && isExpanded), enter = expandVertically(expandFrom = Alignment.Top), exit = shrinkVertically(shrinkTowards =  Alignment.Top)) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xffed7014)
                        ),
                        border = BorderStroke(6.dp, Color(0xffdd571c)),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Enter the desired distance (min:2000)", fontSize = 17.sp, fontFamily = FontFamily.SansSerif, color = Color.Black, fontWeight = FontWeight.Bold)
                            TextField(
                                value = text,
                                onValueChange = {
                                    text=it
                                },
                                singleLine = true,
                                placeholder = {
                                    Text(text = "Preferrable : 15000", color = Color.Black)
                                },
                                modifier = Modifier
                                    .padding(top = 2.dp, bottom = 25.dp)
                                    .size(width = 200.dp, height = 60.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xfffeeb23)
                                ),
                                textStyle = TextStyle(color = Color.Black)
                            )
                            Text(
                                text = "NOTE : The distance you entered will be used to calculate the score and the highscore eventually" ,
                                fontSize = 15.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            if (isExpanded && choice==1) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }else{
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
            }

            Button(
                onClick = {
                    choice = 2
                    isSelected=true
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(choice==2) Color.LightGray else Color(0xff299617)
                ),
                border = BorderStroke(4.dp, if(choice==2) Color.DarkGray else Color(0xff005c29))
            ) {
                Text(text = "HACKER", fontSize = 30.sp, fontFamily = FontFamily(font2), color = Color.Black, modifier = Modifier
                    .offset(x = 1.dp, y = 2.dp)
                    .padding(vertical = 2.dp, horizontal = 40.dp))
            }
            if (isExpanded && choice==1) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }else{
                Spacer(modifier = Modifier.padding(vertical = 15.dp))
            }
            Button(
                onClick = {
                    choice = 3
                    isSelected=true
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(choice==3) Color.LightGray else Color(0xff299617)
                ),
                border = BorderStroke(4.dp, if(choice==3) Color.DarkGray else Color(0xff005c29))
            ) {
                Text(text = "HACKER ++", fontSize = 30.sp, fontFamily = FontFamily(font2), color = Color.Black, modifier = Modifier
                    .offset(x = 7.dp, y = 2.dp)
                    .padding(vertical = 2.dp, horizontal = 20.dp))
            }

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
                    onClick = {
                        if(choice==1){
                            if(text==""){
                                distance=10000
                            }else{
                                distance=text.toInt()
                            }
                            navController.navigate("game_page")
                        }else if(choice==2){
                            navController.navigate("game_page2")
                        }else if (choice==3){
                            navController.navigate("game_page3")
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 290.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffed7014)
                    ),
                    border = BorderStroke(6.dp, Color(0xffdd571c)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = "START",
                        fontSize = 48.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 20.dp)
                            .offset(x = 1.dp, y = 3.dp),
                        color = Color.Black,
                        fontFamily = FontFamily(font2)
                    )
                }
            }
        }
    }
}