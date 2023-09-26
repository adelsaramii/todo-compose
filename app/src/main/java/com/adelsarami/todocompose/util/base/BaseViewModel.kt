package com.adelsarami.todocompose.util.base

import androidx.lifecycle.ViewModel
import com.example.smartdam.util.base.resource.ErrorModel
import com.example.smartdam.util.base.resource.Resource
import com.pilot.supplier.util.base.resource.ResourceErrorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {

    suspend fun <T> get(
        flow: MutableStateFlow<Resource<T>>,
        result: suspend () -> Triple<Boolean, T?, String?>
    ) {
        flow.emit(Resource.Loading())

        val triple = result()
        if (triple.first) {
            flow.emit(Resource.Success(triple.second))
        }
        else {
            flow.emit(
                Resource.Error(
                    ErrorModel(
                        resourceErrorType = ResourceErrorType.SERVER,
                        code = triple.third
                    )
                )
            )
        }
    }

    suspend fun <T> get(
        flow: MutableSharedFlow<Resource<T>>,
        result: suspend () -> Triple<Boolean, T?, String?>
    ) {
        flow.emit(Resource.Loading())

        val triple = result()
        if (triple.first) {
            flow.emit(Resource.Success(triple.second))
        }
        else {
            flow.emit(
                Resource.Error(
                    ErrorModel(
                        resourceErrorType = ResourceErrorType.SERVER,
                        code = triple.third
                    )
                )
            )
        }
    }

    suspend fun <T> getFlow(
        observer: MutableStateFlow<T?>,
        observable: suspend () -> Flow<T?>
    ) {
        observable().collect {
            observer.emit(it)
        }
    }

}