package stresslevelapp.tesside.belidesravan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserLoginActivityScreen()
        }
    }
}

@Composable
fun UserLoginActivityScreen() {
    var testerMail by remember { mutableStateOf("") }
    var testerPassword by remember { mutableStateOf("") }

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
                value = testerMail,
                onValueChange = { testerMail = it },
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

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = testerPassword,
                onValueChange = { testerPassword = it },
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
                onClick = {
                    when {
                        testerMail.isEmpty() -> {
                            Toast.makeText(context, "Please Enter Mail", Toast.LENGTH_SHORT).show()
                        }

                        testerPassword.isEmpty() -> {
                            Toast.makeText(context, " Please Enter Password", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
                            val testerData = TesterData(
                                "",
                                testerMail,
                                "",
                                testerPassword
                            )

                            loginTester(testerData,context)
                        }

                    }
                },
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
                    text = "Login",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "I'm new to this app !", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Join Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Blue text color for "Sign Up"
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                    context.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}

fun loginTester(testerData: TesterData, context: Context) {

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("TesterData").child(testerData.emailid.replace(".", ","))

    databaseReference.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val dbData = task.result?.getValue(TesterData::class.java)
            if (dbData != null) {
                if (dbData.password == testerData.password) {

                    StressLevelTesterData.saveLoginStatus(context, true)
                    StressLevelTesterData.saveUserName(context, dbData.emailid)
                    StressLevelTesterData.saveUserEmail(context, dbData.name)


                    context.startActivity(Intent(context, stresslevelapp.tesside.belidesravan.BaseActivity::class.java))
                    (context as Activity).finish()
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Seems Incorrect Credentials", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Your account not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                context,
                "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}