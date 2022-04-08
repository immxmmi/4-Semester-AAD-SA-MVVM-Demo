package at.technikum_wien.if20b231.mvvmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.technikum_wien.if20b231.mvvmdemo.ui.theme.MVVMDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val data by remember { mutableStateOf("") }
    val loading by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState(0)

    Column {
        TopAppBar(title =  { Text("ViewModel & Compose") })
        Button(
            onClick = { },
            enabled = !loading,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Reload", fontSize = 21.sp)
        }
        Text(text = data,
            fontSize = 21.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .verticalScroll(state = scrollState, enabled = true)
        )
    }
}