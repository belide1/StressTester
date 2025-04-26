package com.example.stressleveltester

import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.stressleveltester.sresstester.AppDatabase
import com.example.stressleveltester.sresstester.DatabaseProvider
import com.example.stressleveltester.sresstester.StressTestEntity
import com.example.stressleveltester.ui.theme.StressLevelTesterTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class TestResultFragment : Fragment(R.layout.fragment_test_result) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.testResult).setContent {
            StressLevelTesterTheme {
                TestHistoryScreen()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TestResultFragmentPreview() {
    TestHistoryScreen()
}


@Composable
fun TestHistoryScreen(context: Context = LocalContext.current) {
    val coroutineScope = rememberCoroutineScope()
    val db = remember { DatabaseProvider.getDatabase(context) }
    var testList by remember { mutableStateOf(listOf<StressTestEntity>()) }

    LaunchedEffect(true) {
        coroutineScope.launch {
            testList = db.stressTestDao().getAllTests()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.bttm_navbar))
                .padding(horizontal = 12.dp, vertical = 4.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Graph Analysis",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

        }

        StressChartScreen(context)

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

fun mapResultToScore(result: String): Float {
    return when (result.lowercase()) {
        "low stress" -> 1f
        "moderate stress" -> 2f
        "high stress" -> 3f
        else -> 0f
    }
}


@Composable
fun StressChartScreen(context: Context = LocalContext.current) {
    val chartEntries = remember { mutableStateListOf<Entry>() }
    val dateLabels = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        val dao = Room.databaseBuilder(
            context,
            AppDatabase::class.java,  // Replace with your actual DB class
            "stress_app_db"
        ).build().stressTestDao()

        val data = withContext(Dispatchers.IO) { dao.getAllTests() }

        chartEntries.clear()
        dateLabels.clear()

        data.reversed().forEachIndexed { index, test ->
            chartEntries.add(Entry(index.toFloat(), mapResultToScore(test.result)))
            dateLabels.add((formatDateTime(test.dateTime)))
//            dateLabels.add(test.dateTime.take(10) + "/n" + test.dateTime.substring(11)) // Show only date
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Stress Level Trend",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (chartEntries.isNotEmpty()) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                factory = { ctx ->
                    val chart = LineChart(ctx)

                    val dataSet = LineDataSet(chartEntries, "Stress Level").apply {
                        color = Color.Blue.hashCode()
                        valueTextColor = Color.Black.hashCode()
                        lineWidth = 2f
                        circleRadius = 4f
                        setDrawCircleHole(false)
                        setDrawValues(false)
                        mode = LineDataSet.Mode.CUBIC_BEZIER  // Wave-like effect
                        cubicIntensity = 0.2f // Controls curve smoothness (0.05 - 1.0)
                    }

                    val lineData = LineData(dataSet)
                    chart.data = lineData

                    chart.xAxis.apply {
                        position = XAxis.XAxisPosition.BOTTOM
                        granularity = 1f
                        setDrawGridLines(false)
                        valueFormatter = IndexAxisValueFormatter(dateLabels)
                    }

                    chart.axisRight.isEnabled = false
                    chart.axisLeft.setDrawGridLines(true)
                    chart.description.isEnabled = false
                    chart.legend.isEnabled = true
                    chart.invalidate()

                    chart
                }
            )

//            AndroidView(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp),
//                factory = { ctx ->
//                    val chart = LineChart(ctx)
//
//                    val dataSet = LineDataSet(chartEntries, "Stress Level").apply {
//                        color = Color.Blue.hashCode()
//                        valueTextColor = Color.Black.hashCode()
//                        lineWidth = 2f
//                        circleRadius = 4f
//                        setDrawCircleHole(false)
//                        setDrawValues(false)
//                    }
//
//                    val lineData = LineData(dataSet)
//                    chart.data = lineData
//
//                    chart.xAxis.apply {
//                        position = XAxis.XAxisPosition.BOTTOM
//                        granularity = 1f
//                        setDrawGridLines(false)
//                        valueFormatter = IndexAxisValueFormatter(dateLabels)
//                    }
//
//                    chart.axisRight.isEnabled = false
//                    chart.description.isEnabled = false
//                    chart.legend.isEnabled = true
//                    chart.invalidate()
//
//                    chart
//                }
//            )
        } else {
            Text("No data found", style = MaterialTheme.typography.bodyMedium)
        }
    }
}


fun formatDateTime(input: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM d\nh:mm a", Locale.getDefault())
        val date = inputFormat.parse(input)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        input // return original if parsing fails
    }
}