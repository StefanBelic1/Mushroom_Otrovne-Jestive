package hr.ferit.stefanbelic.mushroom.presentation.JestiveScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ferit.stefanbelic.mushroom.common.Resource
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo
import hr.ferit.stefanbelic.mushroom.repositories.MushroomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JestiveViewModel @Inject constructor(private val mushroomRepository: MushroomRepository) :
    ViewModel() {

    private val _jestiveMushroomList = mutableStateOf(JestiveScreenState())
    val jestiveMushroomList: State<JestiveScreenState> = _jestiveMushroomList

    fun getJestiveGljiveMushroomList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mushroomRepository.getJestiveGljiveMushroomList().collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let {
                                _jestiveMushroomList.value =
                                    JestiveScreenState(data = it, isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _jestiveMushroomList.value = JestiveScreenState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _jestiveMushroomList.value =
                                JestiveScreenState(isLoading = false, error = it.error)
                        }
                    }
                }

            } catch (e: Exception) {
                _jestiveMushroomList.value =
                    JestiveScreenState(isLoading = false, error = "Failed to fetch data")
            }
        }
    }

    fun removeJestiveMushroom(mushroomInfo: MashroomInfo) = viewModelScope.launch(Dispatchers.IO) {
        mushroomRepository.removeJestiveMushroom(mushroomInfo.uid).collect {

        }
    }

}


data class JestiveScreenState(
    val isLoading: Boolean = true,
    val data: List<MashroomInfo> = emptyList(),
    val error: String? = null
)