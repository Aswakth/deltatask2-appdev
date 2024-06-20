package com.example.cheesechase

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cheesechase.ui.theme.HighScore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GamePage(navController: NavController){

    var move by remember{ mutableStateOf(0) }
    val tom= ImageBitmap.imageResource(id = R.drawable.tom)
    val jerry= ImageBitmap.imageResource(id = R.drawable.jerry)
    var jerryX by remember { mutableStateOf(404) }
    var tomX by remember { mutableStateOf(348) }
    var road by remember { mutableStateOf(2) }
    val coroutinescope= rememberCoroutineScope()
    var positions by remember { mutableStateOf(listOf<IntOffset>()) }
    val x= listOf(185f,540f,895f)
    val a= listOf(70,425,780)
    val b= listOf(0,350,700,1050)
    var c= listOf(IntOffset(70,0),IntOffset(425,0),IntOffset(780,0))
    var jcX by remember { mutableStateOf(540f) }
    var tcX by remember { mutableStateOf(540f) }
    val trap1= ImageBitmap.imageResource(id = R.drawable.cone)
    val trap2= ImageBitmap.imageResource(id = R.drawable.wood_block)
    val trap3= ImageBitmap.imageResource(id = R.drawable.barrier)
    var start by remember { mutableStateOf(true) }
    var Ytracker by remember { mutableStateOf(0) }
    var count=-1
    var checkedList by remember { mutableStateOf(listOf<IntOffset>()) }
    var imageList by remember { mutableStateOf(listOf<ImageBitmap>()) }
    val images by remember { mutableStateOf(listOf(trap1,trap2,trap3)) }
    var catch by remember { mutableStateOf(0) }
    val winnerFlag= ImageBitmap.imageResource(id = R.drawable.finish)
    var m by remember { mutableStateOf(0) }
    val font2= Font(R.font.mont)
    val star= painterResource(id = R.drawable.star)
    val context= LocalContext.current
    val preferenceManager=remember{ HighScore(context) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    if ((offset.x < ((size.width) / 3).toFloat()) && (offset.x > 0) && road == 2) {
                        coroutinescope.launch {
                            jerryX = 49
                            jcX = 185f
                            delay(250)
                            tomX = -(8)
                            tcX = 185f
                            road = 1
                        }
                    } else if ((offset.x < ((size.width) * 1).toFloat()) && (offset.x > ((size.width) / 3) * 2.toFloat()) && road == 2) {
                        coroutinescope.launch {
                            jerryX = 759
                            jcX = 895f
                            delay(250)
                            tomX = 703
                            tcX = 895f
                            road = 3
                        }
                    } else if ((offset.x < ((size.width) * 1).toFloat()) && (offset.x > ((size.width) / 3).toFloat()) && road == 1) {
                        coroutinescope.launch {
                            jerryX = 404
                            jcX = 540f
                            delay(250)
                            tomX = 348
                            tcX = 540f
                            road = 2
                        }
                    } else if ((offset.x < ((size.width) / 3) * 2.toFloat()) && (offset.x > 0) && road == 3) {
                        coroutinescope.launch {
                            jerryX = 404
                            jcX = 540f
                            delay(250)
                            tomX = 348
                            tcX = 540f
                            road = 2
                        }
                    }
                }
            }
    ) {

        val remThickness=13f
        val roadThickness=(size.width-4*remThickness)/3
        val height=size.height

        drawRect(
            topLeft = Offset.Zero,
            color = Color(0xff084724)
        )

        for(i in 1..3){
            drawLine(
                start = Offset((remThickness*i)+(roadThickness*(i-1)),0f),
                end = Offset((remThickness*i)+(roadThickness*(i-1)),height),
                color = Color(0xfff7d654),
                strokeWidth = 12.dp.toPx()
            )
            drawLine(
                start = Offset((remThickness*i)+(roadThickness*i),0f),
                end = Offset((remThickness*i)+(roadThickness*i),height),
                color = Color(0xfff7d654),
                strokeWidth = 12.dp.toPx()
            )
        }

        drawRect(
            topLeft = Offset(remThickness,0f),
            color = Color.Black,
            size = Size(width = roadThickness,height=height)
        )
        drawLine(
            start = Offset(remThickness+(roadThickness/2),0f),
            end = Offset(remThickness+(roadThickness/2),height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f,120f),-move.toFloat())
        )

        drawRect(
            topLeft = Offset((remThickness*2)+roadThickness,0f),
            color = Color.Black,
            size = Size(width = roadThickness,height=height)
        )
        drawLine(
            start = Offset((remThickness*2)+roadThickness+(roadThickness/2),0f),
            end = Offset((remThickness*2)+roadThickness+(roadThickness/2),height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f,120f),150f-move.toFloat())
        )

        drawRect(
            topLeft = Offset((remThickness*3)+(roadThickness*2),0f),
            color = Color.Black,
            size = Size(width = roadThickness,height=height)
        )
        drawLine(
            start = Offset((remThickness*3)+(roadThickness*2)+(roadThickness/2),0f),
            end = Offset((remThickness*3)+(roadThickness*2)+(roadThickness/2),height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f,120f),75f-move.toFloat())
        )

        val t1Width = trap1.width
        val t1Height = trap1.height
        val t2Width = trap2.width
        val t2Height = trap2.height
        val t3Width = trap3.width
        val t3Height = trap3.height
        for(i in positions){
            var a: ImageBitmap=trap1
            if(i !in checkedList){
                a=images.random()
                imageList+=a
                checkedList+=i
            }else{
                for(j in 0..((checkedList.size)-1)){
                    if(checkedList[j]==i){
                        a=imageList[j]
                    }
                }
            }
            if(a==trap1){
                drawImage(
                    image = trap1,
                    srcOffset = IntOffset(0,0),
                    dstOffset = i+ IntOffset(0,move),
                    srcSize = IntSize(t1Width, t1Height),
                    dstSize = IntSize(200,200)
                )
            }else if(a==trap2){
                drawImage(
                    image = trap2,
                    srcOffset = IntOffset(0,0),
                    dstOffset = i+ IntOffset(0,move),
                    srcSize = IntSize(t2Width, t2Height),
                    dstSize = IntSize(250,240)
                )
            }else{
                drawImage(
                    image = trap3,
                    srcOffset = IntOffset(0,0),
                    dstOffset = i+ IntOffset(0,move),
                    srcSize = IntSize(t3Width, t3Height),
                    dstSize = IntSize(230,230)
                )
            }

            if(move> distance-1452){
                m=1
                val fWidth = winnerFlag.width
                val fHeight = winnerFlag.height
                drawImage(
                    image = winnerFlag,
                    srcOffset = IntOffset(0,0),
                    dstOffset = IntOffset(0,-(distance)+900)+IntOffset(0,move),
                    srcSize = IntSize(fWidth, fHeight),
                    dstSize = IntSize(1080,650)
                )
            }
        }

        drawCircle(
            color = Color(0xff00563f),
            radius = 37.dp.toPx(),
            center = Offset(jcX,1427f)
        )
        drawCircle(
            color = Color(0xff004225),
            radius = 37.dp.toPx(),
            center = Offset(jcX,1427f),
            style = Stroke(5.dp.toPx())
        )
        val jWidth = trap1.width
        val jHeight = trap1.height
        drawImage(
            image = jerry,
            srcOffset = IntOffset(0,0),
            dstOffset = IntOffset(jerryX,1370),
            srcSize = IntSize(jWidth, jHeight),
            dstSize = IntSize(240,240)
        )
        drawCircle(
            color = Color(0xff5333ae),
            radius = 50.dp.toPx(),
            center = Offset(
                tcX,if(catch==2) 1585f else if(catch==1) 1720f else 1915f
            )
        )
        drawCircle(
            color = Color(0xff4a006a),
            radius = 50.dp.toPx(),
            center = Offset(
                tcX,if(catch==2) 1585f else if(catch==1) 1720f else 1915f
            ),
            style = Stroke(5.dp.toPx())
        )
        val tWidth = trap1.width
        val tHeight = trap1.height
        drawImage(
            image = tom,
            srcOffset = IntOffset(0,0),
            dstOffset = IntOffset(
                tomX, if(catch==2) 1490 else if(catch==1) 1625 else 1820
            ),
            srcSize = IntSize(tWidth, tHeight),
            dstSize = IntSize(325,325)
        )
    }
    var r: Int
    LaunchedEffect(Unit) {
       for (i in 1..(distance)+5000){
           move+=1
           Ytracker+=1
           if(i<= distance){
               dummyscore+=1
               score= dummyscore/100
           }
           delay(1L)
           if(move< distance-1452){
              if(start){
                  r=(1..2).random()
                  if(r==1){
                      positions+=IntOffset(a.random(),0)
                  }else{
                      val q=a.random()
                      val p=IntOffset(q,0)
                      positions+=p
                      val d=a-q
                      positions+=IntOffset(d.random(),0)
                  }
                  start=false
              }else if(Ytracker==500){
                  Ytracker=0
                  r=(1..2).random()
                  if(r==1){
                      positions+=IntOffset(a.random(),count*500)
                  }else{
                      val q=a.random()
                      val p=IntOffset(q,count*500)
                      positions+=p
                      val d=a-q
                      positions+=IntOffset(d.random(),count*500)
                  }
                  count-=1
              }
          }
          for(i in positions){
              if(i+ IntOffset(-21,move)== IntOffset(jerryX,+1200)){
                  catch+=1
              }
          }
          if(catch==2){
              win=0
              if(preferenceManager.getData("normal","")!= ""){
                  if(score > preferenceManager.getData("normal","").toInt()){
                      preferenceManager.saveData("normal","$score")
                  }
              }else{
                  preferenceManager.saveData("normal","$score")
              }
              ad=1
              break
          }else if(
              (move==((distance))+400) ||
              (move==((distance))+401) ||
              (move==((distance))+402)
              ){
              win=1
              if(score> highscore){
                  highscore= score
                  preferenceManager.saveData("normal","$highscore")
              }
              ad=1
              break
          }
      }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = 20.dp, bottom = 7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            border = BorderStroke(4.dp, Color.DarkGray)
        ) {
            Image(painter = star, contentDescription = null, modifier = Modifier.size(30.dp).offset(x=-(8).dp))
            Text(text = "SCORE:  ${score}", fontSize = 22.sp, fontFamily = FontFamily(font2), color = Color.Black)
        }
    }
}
