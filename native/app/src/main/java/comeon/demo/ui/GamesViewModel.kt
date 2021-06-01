package comeon.demo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import comeon.demo.data.games.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    repository: GamesRepository
) : ViewModel() {

    private val refresh = MutableLiveData(Unit)

    val request = refresh.switchMap {
        repository.request()
    }

    fun refresh() {
        refresh.value = Unit
    }
}