package com.example.stressleveltester

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stressleveltester.ui.theme.StressLevelTesterTheme


class StressHomeFragment : Fragment(R.layout.fragment_stress_home) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.stressHome).setContent {
            StressLevelTesterTheme {
                StressLevelTesterHomeScreen()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StressLevelTesterHomeScreenPreview()
{
    StressLevelTesterHomeScreen()
}

@Composable
fun StressLevelTesterHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF5E6)) // Light beige background color
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp) // Padding inside the bar
        ) {
            Text(
                text = "Home",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center) // Center the text vertically
            )
        }

        // Content Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Map/Image Card
            Card(
                shape = RoundedCornerShape(12.dp),

                elevation = CardDefaults.cardElevation(4.dp),

                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    // Replace with your map or illustration
                    Image(
                        painter = painterResource(id = R.drawable.stress_level_tester), // Replace with your map/illustration
                        contentDescription = "Stress",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Divider(color = Color(0xFF4CAF50), thickness = 2.dp)
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Stress Level Tester",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Start testing your stress levels and evaluate the current status",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))



            @Composable
            fun ResourceItem(title: String) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 22.dp,horizontal = 16.dp)
                        .clickable { /* Handle click */ },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title, fontSize = 16.sp,fontWeight = FontWeight.Bold)
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24), // Replace with your arrow icon
                        contentDescription = "Arrow",
                        modifier = Modifier.size(16.dp)
                            .clickable { /* Handle click */ }
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                Column {


                    // Title with Divider touching the ends
                    Text(
                        text = "Manage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider(color = Color.Green, thickness = 2.dp)

                    // Resource Items
                    ResourceItem("Start Test",)
                    Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    ResourceItem("Add Information")
                    Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    ResourceItem("Manage Details")
                    Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    ResourceItem("Delete Test")
                }
            }
        }
    }
}