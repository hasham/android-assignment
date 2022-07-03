package ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import data.model.DetectResponseItem
import data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {


    fun getLanguages() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getLanguages()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTranslation(text: String, target: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = repository.detectLanguage(text)

            if (data.isNotEmpty()) {

                val detectedLanguage = (data[0] as DetectResponseItem).language
                val translationResponse = repository.translate(text, detectedLanguage, target)

                emit(Resource.success(data = translationResponse))
            }


        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}