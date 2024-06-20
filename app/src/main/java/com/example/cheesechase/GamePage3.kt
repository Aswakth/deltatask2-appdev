package com.example.cheesechase

import android.content.Context
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.cheesechase.ui.theme.HighScore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GamePage3(navController: NavController) {

    var move by remember { mutableStateOf(0) }
    val tom = ImageBitmap.imageResource(id = R.drawable.tom)
    val jerry = ImageBitmap.imageResource(id = R.drawable.jerry)
    var jerryX by remember { mutableStateOf(404) }
    var tomX by remember { mutableStateOf(348) }
    var road by remember { mutableStateOf(2) }
    val coroutinescope = rememberCoroutineScope()
    var positions by remember { mutableStateOf(listOf<IntOffset>()) }
    val x = listOf(185f, 540f, 895f)
    val a = listOf(70, 425, 780)
    val b = listOf(0, 350, 700, 1050)
    var c = listOf(IntOffset(70, 0), IntOffset(425, 0), IntOffset(780, 0))
    var jcX by remember { mutableStateOf(540f) }
    var tcX by remember { mutableStateOf(540f) }
    val trap1 = ImageBitmap.imageResource(id = R.drawable.cone)
    val trap2 = ImageBitmap.imageResource(id = R.drawable.wood_block)
    val trap3 = ImageBitmap.imageResource(id = R.drawable.barrier)
    val trap = ImageBitmap.imageResource(id = R.drawable.mouse_trap)
    val power_up = ImageBitmap.imageResource(id = R.drawable.powerup)
    val cheese = ImageBitmap.imageResource(id = R.drawable.cheese2)
    var start by remember { mutableStateOf(true) }
    var Ytracker by remember { mutableStateOf(0) }
    var tracker by remember { mutableStateOf(0) }
    var counter by remember { mutableStateOf(0f) }
    var count by remember { mutableStateOf(-1) }
    var checkedList by remember { mutableStateOf(listOf<IntOffset>()) }
    var checkedList2 by remember { mutableStateOf(listOf<IntOffset>()) }
    var trapList by remember { mutableStateOf(listOf<IntOffset>()) }
    var powerList by remember { mutableStateOf(listOf<IntOffset>()) }
    var cheeseList by remember { mutableStateOf(listOf<IntOffset>()) }
    var removeList by remember { mutableStateOf(listOf<IntOffset>()) }
    var imageList by remember { mutableStateOf(listOf<ImageBitmap>()) }
    var imageList2 by remember { mutableStateOf(listOf<ImageBitmap>()) }
    val images by remember { mutableStateOf(listOf(trap1, trap2, trap3, trap)) }
    val images2 by remember { mutableStateOf(listOf(power_up, cheese)) }
    val winnerFlag = ImageBitmap.imageResource(id = R.drawable.finish)
    var m by remember { mutableStateOf(0) }
    val font2 = Font(R.font.mont)
    val star = painterResource(id = R.drawable.star)
    val context = LocalContext.current
    val preferenceManager = remember { HighScore(context) }
    var tomY by remember { mutableStateOf(0f) }
    var y1 by remember { mutableStateOf(0f) }
    var y2 by remember { mutableStateOf(0) }
    var yi by remember { mutableStateOf(1915f) }
    var yj by remember { mutableStateOf(1820) }
    var jump by remember { mutableStateOf(false) }
    val jumpicon = ImageBitmap.imageResource(id = R.drawable.jump)
    var s by remember { mutableStateOf(false) }
    var stopCase by remember { mutableStateOf(false) }
    var stop by remember { mutableStateOf(false) }
    val jumpsound = MediaPlayer.create(context, R.raw.jump)
    val gameoversound = MediaPlayer.create(context, R.raw.gameover)
    val slidesound = MediaPlayer.create(context, R.raw.move)
    val powerupsound = MediaPlayer.create(context, R.raw.powerup)
    val revivesound = MediaPlayer.create(context, R.raw.revive)
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val cheese2 = painterResource(id = R.drawable.cheese2)
    val gun = ImageBitmap.imageResource(id = R.drawable.armour)
    val bullet = ImageBitmap.imageResource(id = R.drawable.bullet)
    val shooting = painterResource(id = R.drawable.shooting)
    var shoot by remember { mutableStateOf(false) }
    var bulletmove by remember { mutableStateOf(0) }
    var shootX by remember { mutableStateOf(474) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    if (((offset.x < ((size.width) / 3).toFloat()) && (offset.x > 0) && road == 2)) {
                        slidesound.start()
                        coroutinescope.launch {
                            jerryX = 49
                            jcX = 185f
                            delay(250)
                            tomX = -(8)
                            tcX = 185f
                            road = 1
                        }
                    } else if (((offset.x < ((size.width) * 1).toFloat()) && (offset.x > ((size.width) / 3) * 2.toFloat()) && road == 2)) {
                        slidesound.start()
                        coroutinescope.launch {
                            jerryX = 759
                            jcX = 895f
                            delay(250)
                            tomX = 703
                            tcX = 895f
                            road = 3
                        }
                    } else if (((offset.x < ((size.width) * 1).toFloat()) && (offset.x > ((size.width) / 3).toFloat()) && road == 1)) {
                        slidesound.start()
                        coroutinescope.launch {
                            jerryX = 404
                            jcX = 540f
                            delay(250)
                            tomX = 348
                            tcX = 540f
                            road = 2
                        }
                    } else if (((offset.x < ((size.width) / 3) * 2.toFloat()) && (offset.x > 0) && road == 3)) {
                        slidesound.start()
                        coroutinescope.launch {
                            jerryX = 404
                            jcX = 540f
                            delay(250)
                            tomX = 348
                            tcX = 540f
                            road = 2
                        }
                    } else if ((offset.x < ((size.width) / 3).toFloat()) && (offset.x > 0) && road == 1) {
                        jumpsound.start()
                        jump = true
                        coroutinescope.launch {
                            delay(500L)
                            jump = false
                        }
                    } else if ((offset.x < ((size.width) * 1).toFloat()) && (offset.x > ((size.width) / 3) * 2.toFloat()) && road == 3) {
                        jumpsound.start()
                        jump = true
                        coroutinescope.launch {
                            delay(500L)
                            jump = false
                        }
                    } else if ((offset.x < ((size.width) / 3) * 2.toFloat()) && (offset.x > ((size.width) / 3).toFloat()) && road == 2) {
                        jumpsound.start()
                        jump = true
                        coroutinescope.launch {
                            delay(500L)
                            jump = false
                        }
                    }
                }
            }
    ) {

        val remThickness = 13f
        val roadThickness = (size.width - 4 * remThickness) / 3
        val height = size.height

        drawRect(
            topLeft = Offset.Zero,
            color = Color(0xff084724)
        )

        for (i in 1..3) {
            drawLine(
                start = Offset((remThickness * i) + (roadThickness * (i - 1)), 0f),
                end = Offset((remThickness * i) + (roadThickness * (i - 1)), height),
                color = Color(0xfff7d654),
                strokeWidth = 12.dp.toPx()
            )
            drawLine(
                start = Offset((remThickness * i) + (roadThickness * i), 0f),
                end = Offset((remThickness * i) + (roadThickness * i), height),
                color = Color(0xfff7d654),
                strokeWidth = 12.dp.toPx()
            )
        }

        drawRect(
            topLeft = Offset(remThickness, 0f),
            color = Color.Black,
            size = Size(width = roadThickness, height = height)
        )
        drawLine(
            start = Offset(remThickness + (roadThickness / 2), 0f),
            end = Offset(remThickness + (roadThickness / 2), height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f, 120f), -move.toFloat())
        )

        drawRect(
            topLeft = Offset((remThickness * 2) + roadThickness, 0f),
            color = Color.Black,
            size = Size(width = roadThickness, height = height)
        )
        drawLine(
            start = Offset((remThickness * 2) + roadThickness + (roadThickness / 2), 0f),
            end = Offset((remThickness * 2) + roadThickness + (roadThickness / 2), height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f, 120f), 150f - move.toFloat())
        )

        drawRect(
            topLeft = Offset((remThickness * 3) + (roadThickness * 2), 0f),
            color = Color.Black,
            size = Size(width = roadThickness, height = height)
        )
        drawLine(
            start = Offset((remThickness * 3) + (roadThickness * 2) + (roadThickness / 2), 0f),
            end = Offset((remThickness * 3) + (roadThickness * 2) + (roadThickness / 2), height),
            color = Color.White,
            strokeWidth = 9.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(200f, 120f), 75f - move.toFloat())
        )
        val cWidth = cheese.width
        val cHeight = cheese.height
        val pWidth = power_up.width
        val pHeight = power_up.height
        val trWidth = trap.width
        val trHeight = trap.height
        val t1Width = trap1.width
        val t1Height = trap1.height
        val t2Width = trap2.width
        val t2Height = trap2.height
        val t3Width = trap3.width
        val t3Height = trap3.height
        for (i in positions) {
            var a: ImageBitmap = trap1
            if (i !in checkedList) {
                a = images.random()
                imageList += a
                checkedList += i
                if (a == trap) {
                    trapList += i
                }
                if (a == power_up) {
                    powerList += i
                }
            } else {
                for (j in 0..((checkedList.size) - 1)) {
                    if (checkedList[j] == i) {
                        a = imageList[j]
                    }
                }
            }
            if (a == trap1) {
                drawImage(
                    image = trap1,
                    srcOffset = IntOffset(0, 0),
                    dstOffset = i + IntOffset(0, move),
                    srcSize = IntSize(t1Width, t1Height),
                    dstSize = IntSize(200, 200)
                )
            } else if (a == trap2) {
                drawImage(
                    image = trap2,
                    srcOffset = IntOffset(0, 0),
                    dstOffset = i + IntOffset(0, move),
                    srcSize = IntSize(t2Width, t2Height),
                    dstSize = IntSize(250, 240)
                )
            } else if (a == trap3) {
                drawImage(
                    image = trap3,
                    srcOffset = IntOffset(0, 0),
                    dstOffset = i + IntOffset(0, move),
                    srcSize = IntSize(t3Width, t3Height),
                    dstSize = IntSize(230, 230)
                )
            } else if (a == trap) {
                drawImage(
                    image = trap,
                    srcOffset = IntOffset(0, 0),
                    dstOffset = i + IntOffset(0, move),
                    srcSize = IntSize(trWidth, trHeight),
                    dstSize = IntSize(200, 200)
                )
            }
            if (i in powerList) {
                if (i !in checkedList2) {
                    a = images2.random()
                    imageList2 += a
                    checkedList2 += i
                    if (a == cheese) {
                        cheeseList += i
                    }
                } else {
                    for (j in 0..((checkedList2.size) - 1)) {
                        if (checkedList2[j] == i) {
                            a = imageList2[j]
                        }
                    }
                }
                if (a == power_up) {
                    drawImage(
                        image = power_up,
                        srcOffset = IntOffset(0, 0),
                        dstOffset = i + IntOffset(0, move + 260),
                        srcSize = IntSize(pWidth, pHeight),
                        dstSize = IntSize(200, 200)
                    )
                } else if (a == cheese) {
                    drawImage(
                        image = cheese,
                        srcOffset = IntOffset(0, 0),
                        dstOffset = i + IntOffset(0, move + 260),
                        srcSize = IntSize(cWidth, cHeight),
                        dstSize = IntSize(200, 200)
                    )
                }
            }
        }
        val juWidth = jumpicon.width
        val juHeight = jumpicon.height
        if (jump) {
            drawImage(
                image = jumpicon,
                srcOffset = IntOffset(0, 0),
                dstOffset = IntOffset(340, 0),
                srcSize = IntSize(juWidth, juHeight),
                dstSize = IntSize(400, 400)
            )
        }
        drawCircle(
            color = Color(0xff00563f),
            radius = 37.dp.toPx(),
            center = Offset(jcX, 1427f)
        )
        drawCircle(
            color = Color(0xff004225),
            radius = 37.dp.toPx(),
            center = Offset(jcX, 1427f),
            style = Stroke(5.dp.toPx())
        )
        val jWidth = trap1.width
        val jHeight = trap1.height
        drawImage(
            image = jerry,
            srcOffset = IntOffset(0, 0),
            dstOffset = IntOffset(jerryX, 1370),
            srcSize = IntSize(jWidth, jHeight),
            dstSize = IntSize(240, 240)
        )
        val gWidth = gun.width
        val gHeight = gun.height
        drawImage(
            image = gun,
            srcOffset = IntOffset(0, 0),
            dstOffset = IntOffset(jerryX+90, 1270),
            srcSize = IntSize(gWidth, gHeight),
            dstSize = IntSize(150, 150)
        )
        val bWidth = bullet.width
        val bHeight = bullet.height
        if(shoot){
            drawImage(
                image = bullet,
                srcOffset = IntOffset(0, 0),
                dstOffset = IntOffset(shootX, 1140)+IntOffset(0,-bulletmove),
                srcSize = IntSize(bWidth, bHeight),
                dstSize = IntSize(150, 150)
            )
        }
        drawCircle(
            color = Color(0xff5333ae),
            radius = 50.dp.toPx(),
            center = Offset(
                tcX, if (catch3 == 2) 1585f else y1
            )
        )
        drawCircle(
            color = Color(0xff4a006a),
            radius = 50.dp.toPx(),
            center = Offset(
                tcX, if (catch3 == 2) 1585f else y1
            ),
            style = Stroke(5.dp.toPx())
        )
        val tWidth = trap1.width
        val tHeight = trap1.height
        drawImage(
            image = tom,
            srcOffset = IntOffset(0, 0),
            dstOffset = IntOffset(
                tomX, if (catch3 == 2) 1490 else y2
            ),
            srcSize = IntSize(tWidth, tHeight),
            dstSize = IntSize(325, 325)
        )
    }
    var r: Int
    LaunchedEffect(key1 = end3, key2 = road, key3 = tilt) {
        if ((road == 2 && tilt > 1f)) {
            slidesound.start()
            coroutinescope.launch {
                jerryX = 49
                jcX = 185f
                delay(250)
                tomX = -(8)
                tcX = 185f
                road = 1
            }
        } else if ((road == 2 && tilt < -1f)) {
            slidesound.start()
            coroutinescope.launch {
                jerryX = 759
                jcX = 895f
                delay(250)
                tomX = 703
                tcX = 895f
                road = 3
            }
        } else if ((road == 1 && tilt < -1f)) {
            slidesound.start()
            coroutinescope.launch {
                jerryX = 404
                jcX = 540f
                delay(250)
                tomX = 348
                tcX = 540f
                road = 2
            }
        } else if ((road == 3 && tilt > 1f)) {
            slidesound.start()
            coroutinescope.launch {
                jerryX = 404
                jcX = 540f
                delay(250)
                tomX = 348
                tcX = 540f
                road = 2
            }
        }
        while(!end3) {
            tomY += 0.4f
            tracker += 1
            move += 20
            if(shoot){
                bulletmove+=20
            }
            Ytracker += 20
            y1 = yi + tomY.toFloat()
            y2 = yj + tomY.toInt()
            dummyscore += 1
            score = dummyscore / 10
            if (tracker < 25) {
                delay((50 - counter).toLong())
            } else if (tracker == 25) {
                tracker = 0
                if (counter < 30) counter += 2f else counter += 0.1f
            }
            if (start) {
                r = (1..2).random()
                if (r == 1) {
                    positions += IntOffset(a.random(), 0)
                } else {
                    val q = a.random()
                    val p = IntOffset(q, 0)
                    positions += p
                    val d = a - q
                    positions += IntOffset(d.random(), 0)
                }
                start = false
            } else if (Ytracker == 500) {
                Ytracker = 0
                r = (1..2).random()
                if (r == 1) {
                    var n: Int
                    n = (0..4).random()
                    positions += IntOffset(a.random(), count * 500)
                    if (n == 1) powerList += IntOffset(a.random(), count * 500)
                } else {
                    val q = a.random()
                    val p = IntOffset(q, count * 500)
                    var n: Int
                    n = (0..4).random()
                    positions += p
                    if (n == 1) powerList += p
                    val d = a - q
                    var m: Int
                    m = (0..4).random()
                    positions += IntOffset(d.random(), count * 500)
                    if (m == 1) powerList += IntOffset(d.random(), count * 500)
                }
                count -= 1
            }
            for (i in positions) {
                if (reward != 1) {
                    if(shoot){
                        if(
                            IntOffset(shootX, 1140)+IntOffset(0,-bulletmove)==(i + IntOffset(49,move)) ||
                            IntOffset(shootX, 1140)+IntOffset(0,-bulletmove+20)==(i + IntOffset(49,move))
                            ){
                            positions-=i
                            bulletmove=0
                            shoot=false
                        }
                    }
                    if ((i + IntOffset(-21, move) == IntOffset(
                            jerryX,
                            +1200
                        )) && (i !in trapList) && (i !in powerList)
                    ) {
                        if (!jump) {
                            if (counter > 15) counter -= 15f else counter = 0f
                            yi -= 250f
                            yj -= 250
                            if ((y1 - 1427f) < 456f) {
                                catch3 = 2
                            }
                            if ((y2 - 1370) < 380) {
                                catch3 = 2
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(
                                    VibrationEffect.createOneShot(
                                        500,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                )
                            } else {
                                @Suppress("DEPRECATION")
                                vibrator.vibrate(500)
                            }
                        } else {
                            jump = false
                        }
                    } else if ((i + IntOffset(-21, move) == IntOffset(
                            jerryX,
                            +1200
                        )) && (i in trapList) && (i !in powerList)
                    ) {
                        if (!jump) {
                            var p: Int
                            var q: Int
                            p = (1..2).random()
                            if (p == 1) {
                                q = (1..2).random()
                                if (q == 1) {
                                    reward = 1
                                } else {
                                    yi += 200f
                                    yj += 200
                                }
                                powerupsound.start()
                            } else {
                                q = (1..2).random()
                                if (q == 1) {
                                    win = -1
                                    score = 0
                                    dummyscore = 0
                                    navController.navigate("game_page3")
                                    delay(10L)
                                } else {
                                    if (score < 30) {
                                        score = 0
                                        dummyscore = 0
                                    } else {
                                        score -= 30
                                        dummyscore -= 300
                                    }
                                }
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    vibrator.vibrate(
                                        VibrationEffect.createOneShot(
                                            500,
                                            VibrationEffect.DEFAULT_AMPLITUDE
                                        )
                                    )
                                } else {
                                    @Suppress("DEPRECATION")
                                    vibrator.vibrate(500)
                                }
                            }
                        } else {
                            jump = false
                        }
                    }
                    if ((i in powerList && i !in cheeseList)) {
                        if ((i + IntOffset(-21, move + 260) == IntOffset(jerryX, +1200))) {
                            if (!jump) {
                                var m: Int
                                m = (1..2).random()
                                if (m == 1) {
                                    positions -= positions.last()
                                    if (i in positions) positions -= i
                                    powerList -= i
                                } else {
                                    score += 30
                                    dummyscore += 300
                                    powerList -= i
                                }
                                powerupsound.start()
                            } else {
                                jump = false
                            }
                        }
                    } else if (i in cheeseList) {
                        if ((i + IntOffset(-21, move + 260) == IntOffset(jerryX, +1200))) {
                            if (!jump) {
                                cheeses += 1
                                cheeseList -= i
                                powerList -= i
                                powerupsound.start()
                            } else {
                                jump = false
                            }
                        }
                    }
                }
            }
            if (catch3 == 2) {
                win = 0
                end3 = true
                if (preferenceManager.getData("hacker++", "") != "") {
                    if (score > preferenceManager.getData("hacker++", "").toInt()) {
                        preferenceManager.saveData("hacker++", "$score")
                    }
                } else {
                    preferenceManager.saveData("hacker++", "$score")
                }
                gameoversound.start()
                ad = 1
            }
            if (revive) {
                yi += 500f
                yj += 500
                revivesound.start()
                for(i in 1..5){
                    positions-=positions.last()
                }
                start=true
                revive = false
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            border = BorderStroke(3.5.dp, Color.DarkGray)
        ) {
            Image(painter = cheese2, contentDescription = null, modifier = Modifier
                .size(20.dp)
                .offset(x = -(8).dp))
            Text(text = "${cheeses}", fontSize = 20.sp, fontFamily = FontFamily(font2), color = Color.Black)
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
            Image(painter = star, contentDescription = null, modifier = Modifier
                .size(30.dp)
                .offset(x = -(8).dp))
            Text(text = "SCORE:  ${score}", fontSize = 22.sp, fontFamily = FontFamily(font2), color = Color.Black)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Button(
            onClick = {
                      if(cheeses != 0){
                          shootX= if(road==1) 119 else if(road==2) 474 else 829
                          shoot=true
                          bulletmove=0
                          cheeses-=1
                      }
            },
            modifier = Modifier
                .padding(start = 20.dp, bottom = 7.dp)
                .size(90.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(cheeses==0) Color.Gray else Color(0xff3e8ede)
            ),
            border = BorderStroke(4.dp, if(cheeses==0) Color.DarkGray else Color(0xff1c05b3))
        ) {}
        Image(painter = shooting, contentDescription = null, modifier = Modifier
            .size(70.dp)
            .offset(x = 30.dp, y = -(20).dp))
    }
}
