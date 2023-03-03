package com.upax.bbvaprueba1.data.datasource.remote

import com.upax.bbvaprueba1.enums.ApiType

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Api(
    val value: ApiType
)
