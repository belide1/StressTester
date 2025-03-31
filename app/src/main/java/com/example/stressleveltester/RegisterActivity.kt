package com.example.stressleveltester

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateAccountActivityScreen()
        }
    }
}

@Composable
fun CreateAccountActivityScreen() {

    var jsfullname by remember { mutableStateOf("") }
    var jsgraduation by remember { mutableStateOf("") }
    var jsemail by remember { mutableStateOf("") }
    var jspassword by remember { mutableStateOf("") }
    var jsconfirmpassword by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.SoftBlue)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = "Stress Level Tester",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )



        Spacer(modifier = Modifier.height(32.dp))


        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(26.dp)),

            ) {

            Spacer(modifier = Modifier.height(32.dp))


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsfullname,
                onValueChange = { jsfullname = it },
                label = { Text("Enter FullName") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.AccountCircle, // Replace with desired icon
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

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsgraduation,
                onValueChange = { jsgraduation = it },
                label = { Text("Enter Profession") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Image(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                            painter = painterResource(id = R.drawable.profession),
                            contentDescription = "Profession",
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

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsemail,
                onValueChange = { jsemail = it },
                label = { Text("Enter Email") },
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

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jspassword,
                onValueChange = { jspassword = it },
                label = { Text("Enter Password") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.Lock, // Replace with desired icon
                            contentDescription = "Email Password"
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

            Spacer(modifier = Modifier.height(46.dp))

            Button(
                onClick = { /* Handle login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.CoralRose),
                    contentColor = colorResource(
                        id = R.color.white
                    )
                ),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "I'm an old user !", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Blue text color for "Sign Up"
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                    context.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}