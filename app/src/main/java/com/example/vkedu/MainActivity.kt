package com.example.vkedu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkedu.ui.theme.VkEduTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEduTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        onOpenSecondActivity = { value ->
                            val intent = Intent(this, SecondActivity::class.java).apply {
                                putExtra(SecondActivity.EXTRA_TEXT, value)
                            }
                            startActivity(intent)
                        },
                        onDialFriend = { phoneNumber ->
                            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${phoneNumber.trim()}")
                            }
                            startActivity(dialIntent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    onOpenSecondActivity: (String) -> Unit,
    onDialFriend: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text(text = stringResource(R.string.input_hint)) },
            singleLine = true
        )

        Button(onClick = { onOpenSecondActivity(inputText) }) {
            Text(text = stringResource(R.string.open_second_activity))
        }

        Button(onClick = { onDialFriend(inputText) }) {
            Text(text = stringResource(R.string.call_friend))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    VkEduTheme {
        MainScreen(
            onOpenSecondActivity = {},
            onDialFriend = {}
        )
    }
}
