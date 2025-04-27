package stresslevelapp.tesside.belidesravan

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import stresslevelapp.tesside.belidesravan.sresstester.DatabaseProvider
import stresslevelapp.tesside.belidesravan.sresstester.StressTestEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StartTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartTestScreenOld()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartTestScreenP() {
    StartTestScreen()
}

@Composable
fun StartTestScreen() {
    var heartRate by remember { mutableStateOf("") }
    var bloodPressure by remember { mutableStateOf("") }
    var sleepDuration by remember { mutableStateOf("") }
    var copingMechanism by remember { mutableStateOf("") }
    var anxietyLevel by remember { mutableFloatStateOf(0f) }
    var energyLevel by remember { mutableFloatStateOf(5f) }
    val selectedMoods = remember { mutableStateListOf<String>() }
    val selectedBreathRates = remember { mutableStateListOf<String>() }

    var showDialog by remember { mutableStateOf(false) }
    var stressResult by remember { mutableStateOf("") }
    var preventionTips by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    Column(modifier = Modifier.fillMaxSize()) {
        // Existing input UI goes here (as you already have)...

        // Energy Level Slider
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                "Energy Level: ${energyLevel.toInt()} / 10",
                style = MaterialTheme.typography.titleMedium
            )
            Slider(
                value = energyLevel,
                onValueChange = { energyLevel = it },
                valueRange = 0f..10f,
                steps = 9
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val stressScore = calculateStressScore(
                    heartRate,
                    bloodPressure,
                    sleepDuration,
                    energyLevel,
                    anxietyLevel,
                    selectedMoods,
                    selectedBreathRates,
                    copingMechanism
                )
                stressResult = stressScore.first
                preventionTips = stressScore.second
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            )
        ) {
            Text("Start Test")
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
                    Button(
                        onClick = {
                            // Save result to database
                            val db = DatabaseProvider.getDatabase(context)
                            val test = StressTestEntity(
                                heartRate = heartRate,
                                bloodPressure = bloodPressure,
                                sleepDuration = sleepDuration,
                                energyLevel = energyLevel,
                                anxietyLevel = anxietyLevel,
                                moodChanges = selectedMoods.joinToString(),
                                breathingRate = selectedBreathRates.joinToString(),
                                copingMechanism = copingMechanism,
                                result = stressResult,
                                preventiveMeasures = preventionTips,
                                dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                                    Date()
                                )
                            )

                            CoroutineScope(Dispatchers.IO).launch {
                                db.stressTestDao().insertTest(test)
                            }

                            showDialog = false
                        }
                    ) {
                        Text("Save Result")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            },
            title = { Text(text = "Stress Result") },
            text = {
                Column {
                    Text(text = "Stress Level: $stressResult", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = preventionTips)
                }
            }
        )

    }
}

fun calculateStressScore(
    heartRate: String,
    bloodPressure: String,
    sleepDuration: String,
    energyLevel: Float,
    anxietyLevel: Float,
    moodChanges: List<String>,
    breathRates: List<String>,
    copingMechanism: String
): Pair<String, String> {
    val measures = mutableListOf<String>()
    var score = 0

    // Heart Rate
    val hr = heartRate.toIntOrNull() ?: 0
    if (hr > 100) {
        score += 2
        measures.add("• Heart rate is elevated. Try deep breathing, reduce caffeine, and include regular but moderate exercise.")
    }

    // Blood Pressure
    val bp = Regex("(\\d{2,3})\\D+(\\d{2,3})").find(bloodPressure)
    val sys = bp?.groups?.get(1)?.value?.toIntOrNull() ?: 0
    val dia = bp?.groups?.get(2)?.value?.toIntOrNull() ?: 0
    if (sys >= 140 || dia >= 90) {
        score += 2
        measures.add("• Blood pressure is high. Try reducing salt, using relaxation apps, and monitoring stress triggers.")
    }

    // Sleep Duration
    val sleep = sleepDuration.toFloatOrNull() ?: 0f
    if (sleep < 6f) {
        score += 2
        measures.add("• Poor sleep detected. Stick to a consistent sleep schedule, reduce screen time, and avoid caffeine late in the day.")
    }

    // Energy Level
    if (energyLevel < 5f) {
        score += 1
        measures.add("• Low energy. Try regular breaks, healthy meals, and self-care.")
    }

    // Anxiety Level
    if (anxietyLevel >= 7f) {
        score += 2
        measures.add("• High anxiety. Consider CBT, grounding exercises, and digital detox routines.")
    }

    // Mood
    if (moodChanges.isNotEmpty()) {
        score += 1
        measures.add("• Mood changes detected. Engage in journaling, light exercise, or talking to someone.")
    }

    // Breathing
    if (breathRates.isNotEmpty()) {
        score += 1
        measures.add("• Breathing irregularities found. Practice box breathing or muscle relaxation.")
    }

    // Coping
    if (copingMechanism.contains("alcohol", ignoreCase = true) ||
        copingMechanism.contains("binge", ignoreCase = true) ||
        copingMechanism.contains("screen", ignoreCase = true)
    ) {
        score += 1
        measures.add("• Unhealthy coping mechanisms. Try walks, music, and digital detox.")
    }

    val result = when {
        score <= 3 -> "Low Stress"
        score <= 6 -> "Moderate Stress"
        else -> "High Stress"
    }

    return Pair(result, measures.joinToString("\n"))
}


@Composable
fun StartTestScreenOld() {
    var heartRate by remember { mutableStateOf("") }
    var bloodPressure by remember { mutableStateOf("") }
    var sleepDuration by remember { mutableStateOf("") }
    var copingMechanism by remember { mutableStateOf("") }
    var anxietyLevel by remember { mutableFloatStateOf(0f) }
    var energyLevel by remember { mutableFloatStateOf(5f) }
    val selectedMoods = remember { mutableStateListOf<String>() }
    val selectedBreathRates = remember { mutableStateListOf<String>() }

    var showDialog by remember { mutableStateOf(false) }
    var stressResult by remember { mutableStateOf("") }
    var preventionTips by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

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
                modifier = Modifier.size(36.dp)
                    .clickable {
                        context.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "back"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Stress Test",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = heartRate,
            onValueChange = { heartRate = it },
            label = { Text("Enter Heart Rate") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = bloodPressure,
            onValueChange = { bloodPressure = it },
            label = { Text("Enter Blood Pressure") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = sleepDuration,
            onValueChange = { sleepDuration = it },
            label = { Text("Enter Sleep Duration (Hrs)") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Anxiety Level: ${anxietyLevel.toInt()} / 10",
                style = MaterialTheme.typography.titleMedium
            )
            Slider(
                value = anxietyLevel,
                onValueChange = { anxietyLevel = it },
                valueRange = 0f..10f,
                steps = 9
            )

            Text(
                "Energy Level: ${energyLevel.toInt()} / 10",
                style = MaterialTheme.typography.titleMedium
            )
            Slider(
                value = energyLevel,
                onValueChange = { energyLevel = it },
                valueRange = 0f..10f,
                steps = 9
            )
        }

        MoodChangeChips(selectedMoods)
        BreatheRateChips(selectedBreathRates)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = copingMechanism,
            onValueChange = { copingMechanism = it },
            label = { Text("Coping Mechanisms") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val stressScore = calculateStressScore(
                    heartRate,
                    bloodPressure,
                    sleepDuration,
                    energyLevel,
                    anxietyLevel,
                    selectedMoods,
                    selectedBreathRates,
                    copingMechanism
                )
                stressResult = stressScore.first
                preventionTips = stressScore.second
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2),
                contentColor = Color.White
            )
        ) {
            Text("Start Test")
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
                    Button(
                        onClick = {
                            // Save result to database
                            val db = DatabaseProvider.getDatabase(context)
                            val test = StressTestEntity(
                                heartRate = heartRate,
                                bloodPressure = bloodPressure,
                                sleepDuration = sleepDuration,
                                energyLevel = energyLevel,
                                anxietyLevel = anxietyLevel,
                                moodChanges = selectedMoods.joinToString(),
                                breathingRate = selectedBreathRates.joinToString(),
                                copingMechanism = copingMechanism,
                                result = stressResult,
                                preventiveMeasures = preventionTips,
                                dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                                    Date()
                                )
                            )

                            CoroutineScope(Dispatchers.IO).launch {
                                db.stressTestDao().insertTest(test)
                            }

                            showDialog = false

                            Toast.makeText(context,"Result Saved",Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Save Result")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            },
            title = { Text(text = "Stress Result") },
            text = {
                Column {
                    Text(text = "Stress Level: $stressResult", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = preventionTips)
                }
            }
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoodChangeChips(selectedMoods: MutableList<String>) {
    val moods = listOf("Irritability", "Sadness", "Anxiety")

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Mood Changes", style = MaterialTheme.typography.titleMedium)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            moods.forEach { mood ->
                FilterChip(
                    selected = mood in selectedMoods,
                    onClick = {
                        if (mood in selectedMoods) selectedMoods.remove(mood)
                        else selectedMoods.add(mood)
                    },
                    label = { Text(mood) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreatheRateChips(selectedBreathRates: MutableList<String>) {
    val breaths = listOf("Shallow", "Rapid Breathing")

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Breathing Rate", style = MaterialTheme.typography.titleMedium)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            breaths.forEach { mood ->
                FilterChip(
                    selected = mood in selectedBreathRates,
                    onClick = {
                        if (mood in selectedBreathRates) selectedBreathRates.remove(mood)
                        else selectedBreathRates.add(mood)
                    },
                    label = { Text(mood) }
                )
            }
        }
    }
}

//////////////////////////////////////////////////////////////////


@Composable
fun AnxietyLevelSlider() {
    var anxietyLevel by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Anxiety Level: ${anxietyLevel.toInt()} / 10",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Slider(
            value = anxietyLevel,
            onValueChange = { anxietyLevel = it },
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoodChangeChips() {
    val moods = listOf("Irritability", "Sadness", "Anxiety")
    val selectedMoods = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Mood Changes",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            moods.forEach { mood ->
                FilterChip(
                    selected = mood in selectedMoods,
                    onClick = {
                        if (mood in selectedMoods) {
                            selectedMoods.remove(mood)
                        } else {
                            selectedMoods.add(mood)
                        }
                    },
                    label = { Text(mood) }
                )
            }
        }
    }
}



