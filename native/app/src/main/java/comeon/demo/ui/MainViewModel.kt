package comeon.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var titleData: MutableLiveData<String> = MutableLiveData()

    val title: LiveData<String> get() = titleData

    fun setTitle(value: String) {
        titleData.value = value
    }
}