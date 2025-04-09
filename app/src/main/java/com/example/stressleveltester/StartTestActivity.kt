package com.example.stressleveltester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Preview(showBackground = true)
@Composable
fun StartTestScreenP()
{
    StartTestScreen()
}

@Composable
fun StartTestScreen()
{
    var heartRate by remember { mutableStateOf("") }

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
                    .size(36.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "back"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
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
            leadingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.Email, // Replace with desired icon
                        contentDescription = "Email Icon"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(3.dp) // Width of the line
                            .height(24.dp) // Adjust height as needed
                            .background(Color.Gray) // Color of the line
                    )
                }
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = heartRate,
            onValueChange = { heartRate = it },
            label = { Text("Enter Blood Pressure") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.Email, // Replace with desired icon
                        contentDescription = "Email Icon"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(3.dp) // Width of the line
                            .height(24.dp) // Adjust height as needed
                            .background(Color.Gray) // Color of the line
                    )
                }
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = heartRate,
            onValueChange = { heartRate = it },
            label = { Text("Enter Sleep Duration (Hrs)") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.Email, // Replace with desired icon
                        contentDescription = "Email Icon"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(3.dp) // Width of the line
                            .height(24.dp) // Adjust height as needed
                            .background(Color.Gray) // Color of the line
                    )
                }
            },
        )

        AnxietyLevelSlider()

        MoodChangeChips()

        BreatheRateChips()

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = heartRate,
            onValueChange = { heartRate = it },
            label = { Text("Copying Mechanisms") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.Email, // Replace with desired icon
                        contentDescription = "Email Icon"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(
                        modifier = Modifier
                            .width(3.dp) // Width of the line
                            .height(24.dp) // Adjust height as needed
                            .background(Color.Gray) // Color of the line
                    )
                }
            },
        )

    }
}

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BreatheRateChips() {
    val moods = listOf("Shallow", "Rapid Breathing")
    val selectedMoods = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Breathe Rate",
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

