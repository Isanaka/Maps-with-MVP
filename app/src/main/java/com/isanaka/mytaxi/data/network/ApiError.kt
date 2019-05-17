package com.isanaka.mytaxi.data.network

data class ApiError(
    val status: ApiStatus,
    val code: Int = -1,
    var message: String = ""
) {
    fun getErrorMessage(): String {
        if (message.isEmpty()) {
            message = when (status) {
                ApiStatus.EMPTY_RESPONSE -> "No data available in repository"
                ApiStatus.NO_CONNECTION -> "Error in connecting to repository"
                ApiStatus.BAD_RESPONSE -> "Error in getting response "
                ApiStatus.TIMEOUT -> " Time out  error"
                ApiStatus.NOT_DEFINED -> "An unexpected error"
            }
        }
        return message
    }

    enum class ApiStatus {
        NO_CONNECTION,
        BAD_RESPONSE,
        TIMEOUT,
        EMPTY_RESPONSE,
        NOT_DEFINED;
    }
}