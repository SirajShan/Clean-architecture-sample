package space.stanton.technicaltest.model

enum class DataState { LOADING, SUCCESS, ERROR }

data class Data<out T> constructor(val dataState: DataState, val data: T? = null, val message: String? = null)
