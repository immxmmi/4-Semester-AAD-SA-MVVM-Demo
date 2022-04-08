package at.technikum_wien.if20b231.mvvmdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.technikum_wien.if20b231.mvvmdemo.ui.theme.MVVMDemoTheme

class MainActivity : ComponentActivity() {

    // ViewModel
    val viewModel by viewModels<DemoViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel : DemoViewModel) {
    // Data aus der ViewModel Klasse holen
    //val data by remember { mutableStateOf("") }
    val data by viewModel.entries.observeAsState("")
    // Läd oder nicht
    //val loading by remember { mutableStateOf(false) }
    val loading by viewModel.loading.observeAsState(initial = true);
    val scrollState = rememberScrollState(0)

    Column {
        TopAppBar(title =  { Text("ViewModel & Compose - T1") })
        Button(
            // Wenn gedrückt wird wird die Funktion in der Klasse ausgeführt
            onClick = { viewModel.reload() },
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