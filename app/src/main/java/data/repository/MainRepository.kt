package data.repository

import data.model.Translation
import data.remote.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getLanguages() = apiService.getLanguages()
    suspend fun detectLanguage(text: String): List<Any> {

        val body: RequestBody = text.toRequestBody("text/plain".toMediaTypeOrNull())
        return apiService.detectLanguage(body)
    }


    suspend fun translate(text: String, source: String, target: String): Translation {
        val textBody: RequestBody = text.toRequestBody("text/plain".toMediaTypeOrNull())
        val sourceBody: RequestBody = source.toRequestBody("text/plain".toMediaTypeOrNull())
        val targetBody: RequestBody = target.toRequestBody("text/plain".toMediaTypeOrNull())

        val map: HashMap<String, RequestBody> = HashMap()
        map["q"] = textBody
        map["source"] = sourceBody
        map["target"] = targetBody

        return apiService.translate(map)
    }

}