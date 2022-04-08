package at.technikum_wien.if20b231.mvvmdemo

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class DemoViewModel : ViewModel() {
    // Auswahlmöglichkeiten
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    // ??
    private val loadingInt = MutableLiveData(true)

    // ??
    val loading : LiveData<Boolean>
        get() = loadingInt

    // Random Generator
    private fun randomString(length : Int) : String{
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map (charPool::get)
            .joinToString("")
    }

    // Lädt die Daten aus dem Generator
    private fun loadEntries(liveData : MutableLiveData<List<String>>) {
        loadingInt.value = true

        viewModelScope.launch {
            liveData.value = listOf()
            delay(2000)
            liveData.value = (0..99).map { randomString(10) }
            loadingInt.value = false
        }

    }

    private val entriesInt : MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            loadEntries(it)
        }
    }

    // Convertiert die Liste to String
    val entries : LiveData<String>
        get() = Transformations.map(entriesInt) { it.joinToString("\n") }



    fun reload() {
        loadEntries(entriesInt)
    }
}