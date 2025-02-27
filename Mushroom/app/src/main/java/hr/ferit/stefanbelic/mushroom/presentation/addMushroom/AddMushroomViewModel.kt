package hr.ferit.stefanbelic.mushroom.presentation.addMushroom

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.stefanbelic.mushroom.common.Resource
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo
import hr.ferit.stefanbelic.mushroom.repositories.MushroomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMushroomViewModel @Inject constructor(private val mushroomRepository: MushroomRepository) :
    ViewModel() {

    private val _addMushroomState = mutableStateOf(AddMushroomScreenState())
    val addMushroomState: State<AddMushroomScreenState> = _addMushroomState

    fun addJestiveMushroom(mushroomInfo: MashroomInfo) = viewModelScope.launch(Dispatchers.IO) {
        _addMushroomState.value =
            AddMushroomScreenState(isLoading = true)

        mushroomRepository.addJestiveMushroom(mushroomInfo).collect {
            when (it) {
                is Resource.Error -> {
                    _addMushroomState.value =
                        AddMushroomScreenState(isLoading = false, error = it.error)
                }

                is Resource.Success -> {

                    delay(2000)
                    it.data?.let {
                        _addMushroomState.value =
                            AddMushroomScreenState(isLoading = false, data = true)
                    }

                }

                is Resource.Loading -> {
                    _addMushroomState.value =
                        AddMushroomScreenState(isLoading = true)
                }
            }
        }
    }

    fun addOtrovneMushroom(mushroomInfo: MashroomInfo) = viewModelScope.launch(Dispatchers.IO) {
        _addMushroomState.value =
            AddMushroomScreenState(isLoading = true)

        mushroomRepository.addOtrovneMushroom(mushroomInfo).collect {
            when (it) {
                is Resource.Error -> {
                    _addMushroomState.value =
                        AddMushroomScreenState(isLoading = false, error = it.error)
                }

                is Resource.Success -> {

                    delay(2000)
                    it.data?.let {
                        _addMushroomState.value =
                            AddMushroomScreenState(isLoading = false, data = true)
                    }

                }

                is Resource.Loading -> {
                    _addMushroomState.value =
                        AddMushroomScreenState(isLoading = true)
                }
            }
        }
    }

    fun addMoreData() {
        _addMushroomState.value = AddMushroomScreenState(isLoading = false, data = false)
    }

}

data class AddMushroomScreenState(
    val isLoading: Boolean = false,
    val data: Boolean = false,
    val error: String? = null
)