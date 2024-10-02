package co.init.core.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PageResponse<T : Serializable>(
    @SerializedName("page") val page: Int,
    @SerializedName("result") val result: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
) : Serializable