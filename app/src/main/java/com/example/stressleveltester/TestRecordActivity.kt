package com.example.stressleveltester

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stressleveltester.sresstester.DatabaseProvider
import com.example.stressleveltester.sresstester.StressTestEntity
import kotlinx.coroutines.launch

class TestRecordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestRecordActivity(this)
        }
    }
}


@Composable
fun TestRecordActivity(context: Context = LocalContext.current) {
    val coroutineScope = rememberCoroutineScope()
    val db = remember { DatabaseProvider.getDatabase(context) }
    var testList by remember { mutableStateOf(listOf<StressTestEntity>()) }

    var showDialog by remember { mutableStateOf(false) }
    var stressResult by remember { mutableStateOf("") }
    var preventionTips by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        coroutineScope.launch {
            testList = db.stressTestDao().getAllTests()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bttm_navbar))
                .padding(horizontal = 12.dp, vertical = 4.dp),
        ) {

            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        (context as Activity).finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "back"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Test Records",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        if (testList.isNotEmpty()) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                items(testList.size) { test ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Row {

                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Image(
                                        modifier = Modifier.size(48.dp),
                                        painter = painterResource(id = R.drawable.ic_stresslevel),
                                        contentDescription = "Stress Level"
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        "Stress Level"
                                    )

                                    Text(
                                        "${testList[test].result}",
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        modifier = Modifier
                                            .clickable {
                                                showDialog = true
                                                stressResult = testList[test].result
                                                preventionTips = testList[test].preventiveMeasures

                                            }
                                            .background(
                                                color = Color.Black,
                                                shape = RoundedCornerShape(
                                                    10.dp
                                                )
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = colorResource(id = R.color.black),
                                                shape = RoundedCornerShape(
                                                    10.dp
                                                )
                                            )
                                            .padding(vertical = 4.dp, horizontal = 6.dp)
                                            .align(Alignment.CenterHorizontally),
                                        text = "Preventions",
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = colorResource(id = R.color.white),
                                        )
                                    )

                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(
                                    modifier = Modifier.weight(2f)
                                ) {


                                    Row(modifier = Modifier.fillMaxWidth()) {

                                        Text("Heart Rate", modifier = Modifier.weight(1f))
                                        Text(": ")
                                        Text(
                                            testList[test].heartRate,
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Row(modifier = Modifier.fillMaxWidth()) {

                                        Text("BP", modifier = Modifier.weight(1f))
                                        Text(": ")
                                        Text(
                                            testList[test].bloodPressure,
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Row(modifier = Modifier.fillMaxWidth()) {

                                        Text("Anxiety", modifier = Modifier.weight(1f))
                                        Text(": ")
                                        Text(
                                            testList[test].anxietyLevel.toString(),
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Row(modifier = Modifier.fillMaxWidth()) {

                                        Text("Mood", modifier = Modifier.weight(1f))
                                        Text(": ")
                                        Text(
                                            testList[test].moodChanges.toString(),
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

//                                    Text("Heart Rate: ${testList[test].heartRate}")
//                                    Text("BP: ${testList[test].bloodPressure}")
//                                    Text("Anxiety Level: ${testList[test].anxietyLevel}")
//                                    Text("Mood: ${testList[test].moodChanges}")

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .background(
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(
                                                    10.dp
                                                )
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(
                                                    10.dp
                                                )
                                            )
                                            .padding(vertical = 4.dp, horizontal = 6.dp)
                                    ) {

                                        Text(
                                            modifier = Modifier.align(Alignment.CenterHorizontally),
                                            text = "Tested On",
                                            fontSize = 14.sp
                                        )

                                        Text(
                                            modifier = Modifier.align(Alignment.CenterHorizontally),
                                            text = testList[test].dateTime,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
//                            Text(
//                                "Prevention: ${testList[test].preventiveMeasures}",
//                                fontSize = 12.sp
//                            )
                        }
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = { showDialog = false }) {
                                Text("OK")
                            }
                        }
                    },
                    title = { Text(text = "Prevention Measures") },
                    text = {
                        Column {
                            Text(text = "Stress Level: $stressResult", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = preventionTips)
                        }
                    }
                )
            }

        } else {
            Text(
                modifier = Modifier
                    .fillMaxSize(),
                text = "No Test Found!\nStart testing your stress levels",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}
