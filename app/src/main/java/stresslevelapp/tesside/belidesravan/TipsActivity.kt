package stresslevelapp.tesside.belidesravan

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class TipsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StressManagementTipsScreen()
        }
    }
}

@Composable
fun StressManagementTipsScreen() {
    val scrollState = rememberScrollState()

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)

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
                        context.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "back"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Stress Management Tips",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {


            StressSection(
                title = "Get Outdoors – Use Local Nature",
                tips = listOf(
                    "Stewart Park – Great for walking, nature, and a breather from the town.",
                    "Albert Park – Ideal for a jog, walk, or even just feeding ducks by the lake.",
                    "North York Moors (nearby) – Hiking or biking in the Moors is a great escape."
                )
            )

            StressSection(
                title = "Tap Into Local Wellness Activities",
                tips = listOf(
                    "The Neptune Centre – Offers gym, swimming, and exercise classes.",
                    "Middlesbrough Sports Village – Great for physical activity to boost mental health.",
                    "Look into yoga or meditation classes at local centres or on Meetup/Facebook groups."
                )
            )

            StressSection(
                title = "Connect Socially (But Calmly)",
                tips = listOf(
                    "Grab a coffee or tea at Bedford Street Coffee, Off the Ground, or The Mockingbird Deli.",
                    "Join a local club or community group: art, football, crafting, or volunteering.",
                    "Check out Middlesbrough Community Learning Hub and Middlesbrough Environment City."
                )
            )

            StressSection(
                title = "Use Local Mental Health Support",
                tips = listOf(
                    "Tees, Esk and Wear Valleys NHS Foundation Trust offers mental health support.",
                    "Local charity MIND Teesside provides drop-ins, counselling, and support groups.",
                    "GP surgeries can refer you to IAPT services for anxiety, depression, or stress."
                )
            )

            StressSection(
                title = "Calm at Home",
                tips = listOf(
                    "Try apps like Headspace or Calm for guided meditations.",
                    "Listen to local artists or nature sounds from Saltburn waves or Flatts Lane Woodlands.",
                    "Keep a journal or gratitude list — it’s simple but powerful."
                )
            )

            StressSection(
                title = "Healthy Comfort Food",
                tips = listOf(
                    "Enjoy a parmo now and then, but try balancing with fresh produce from Middlesbrough Market.",
                    "Cook simple, healthy meals with friends or family — it can be therapeutic!"
                )
            )

            StressSection(
                title = "Final Tip: Walk It Off",
                tips = listOf(
                    "Middlesbrough town centre and suburbs are walkable.",
                    "Brisk walks with music or podcasts can help clear your head."
                )
            )
        }
    }
}

@Composable
fun StressSection(title: String, tips: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F8FF)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            tips.forEach { tip ->
                Text(
                    text = "• $tip",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
