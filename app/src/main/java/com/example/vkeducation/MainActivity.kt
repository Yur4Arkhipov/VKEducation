package com.example.vkeducation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducation.ui.theme.VKEducationTheme
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VKEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    First(
                        onSendTextBtnClick = { text ->
                            val intent = Intent(this, SecondActivity::class.java).apply {
                                putExtra("KEY", text)
                            }
                            this.startActivity(intent)
                        },
                        onCallBtnClick = { text ->
                            if (text.isNotEmpty()) {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = "tel:$text".toUri()
                                }
                                if (intent.resolveActivity(packageManager) != null) {
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, "Нет приложения для звонков", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.getStringExtra("KEY") ?: "ошибка"

        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Second(
                        text = text,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun First(
    onSendTextBtnClick: (String) -> Unit,
    onCallBtnClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text("First Activity")
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = { onSendTextBtnClick(text) }
            ) {
                Text(text = "Открыть вторую Activity")
            }
            Button(
                onClick = { onCallBtnClick(text) }
            ) {
                Text(text = "Позвонить другу")
            }
        }
    }
}

@Composable
fun Second(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Second Activity")
        Text(text)
    }
}
