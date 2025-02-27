package hr.ferit.stefanbelic.mushroom.presentation.OtrovneScreen

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
class OtrovneViewModel @Inject constructor(private val mushroomRepository: MushroomRepository) :
    ViewModel() {

    private val _mushroomList = mutableStateOf(OtrovneScreenState())
    val mushroomList: State<OtrovneScreenState> = _mushroomList

    fun getOtrovneGljiveMushroomList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mushroomRepository.getOtrovneGljiveMushroomList().collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let {
                                _mushroomList.value =
                                    OtrovneScreenState(data = it, isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _mushroomList.value = OtrovneScreenState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _mushroomList.value =
                                OtrovneScreenState(isLoading = false, error = it.error)
                        }
                    }
                }

            } catch (e: Exception) {
                _mushroomList.value =
                    OtrovneScreenState(isLoading = false, error = "Failed to fetch data")
            }
        }
    }

    fun removeFromLMushroomList(mushroomInfo: MashroomInfo) =
        viewModelScope.launch(Dispatchers.IO) {
            mushroomRepository.removeOtrovneMushroom(mushroomInfo.uid).collect {

            }
        }

}

data class OtrovneScreenState(
    val isLoading: Boolean = true,
    val data: List<MashroomInfo> = emptyList(),
    val error: String? = null
)