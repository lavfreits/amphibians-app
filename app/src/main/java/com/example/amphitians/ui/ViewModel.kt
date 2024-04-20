package com.example.amphitians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphitians.AmphibiansApplication
import com.example.amphitians.data.Repository
import com.example.amphitians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface UiState {

    data class Success( val amphibians: List<Amphibian>) : UiState
    data object Error : UiState
    data object Loading : UiState
}
class AmphibiansViewModel(private val networkRepository: Repository) : ViewModel() {
    lateinit var amphibians: List<Amphibian>


    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians(){
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                amphibians = networkRepository.getAmphibians()
                UiState.Success(amphibians)
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val networkRepository = application.container.repository
                AmphibiansViewModel( networkRepository = networkRepository)
            }
        }
    }
}