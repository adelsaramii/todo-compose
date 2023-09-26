package com.example.smartdam.util.base.resource

import com.pilot.supplier.util.base.resource.ResourceErrorType

data class ErrorModel(
    val message: String? = null,
    val resourceErrorType: ResourceErrorType? = null,
    val code: String? = null
)