package stresslevelapp.tesside.belidesravan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import stresslevelapp.tesside.belidesravan.ui.theme.StressLevelTesterTheme

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.profileScreen).setContent {
            StressLevelTesterTheme {
                UserProfile()
            }
        }
    }

}

@Composable
fun UserProfile() {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                text = "Profile",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
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
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))


            Image(
                painter = painterResource(id = R.drawable.iv_profile),
                contentDescription = "Profile",
                modifier = Modifier.size(96.dp)

            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "User Name")

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = StressLevelTesterData.getUserName(context),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "User Email")

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = StressLevelTesterData.getUserEmail(context),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            StressLevelTesterData.saveLoginStatus(context, false)

                            context.startActivity(Intent(context, LoginActivity::class.java))
                            (context as Activity).finish()
                        }
                        .padding(horizontal = 12.dp)
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.black),
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.white),
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

            }

            DetailOfAppOwner()


        }
    }
}


@Composable
fun DetailOfAppOwner() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Contact Us", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Name: Belide Sravan")
                    Text("Email: belidesravan009@gmail.com")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("About Us", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Welcome to the Stress Level Tester App – your personal tool for understanding and managing stress.\n" +
                                "Created by Belide Sravan, this app is designed to help you identify and measure your stress levels in real time. Whether you’re feeling overwhelmed, anxious, or simply want to track your mental well-being, we’re here to help you take control of your stress before it takes control of you.\n" +
                                "Thank you for choosing us to be a part of your journey to peace and well-being.\n"
                    )
                }
            }
        }
    }
}
