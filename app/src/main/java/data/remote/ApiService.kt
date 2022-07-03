package data.remote

import data.model.DetectResponseItem
import data.model.Language
import data.model.Translation
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @GET("/languages")
    suspend fun getLanguages(): List<Language>

    @Multipart
    @POST("/detect")
    suspend fun detectLanguage(@Part("q") text: RequestBody): List<DetectResponseItem>

    @JvmSuppressWildcards
    @Multipart
    @POST("/translate")
    suspend fun translate(
        @PartMap() partMap: Map<String, RequestBody>
    ): Translation
}