package data.model

import com.google.gson.annotations.SerializedName


data class Language(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
) {
    override fun toString(): String {
        return name
    }
}


data class DetectResponseItem(
    @SerializedName("confidence")
    val confidence: Double,
    @SerializedName("language")
    val language: String
)

data class Translation(
    @SerializedName("translatedText")
    val translatedText: String
)
