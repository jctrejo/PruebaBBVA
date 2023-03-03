package com.upax.bbvaprueba1.enums

import com.upax.bbvaprueba1.common.Constants.BASE_URL
import com.upax.bbvaprueba1.common.Constants.BASE_URL_SECUNDARY

enum class ApiType(
    val url: String
) {
    MOVILE(BASE_URL),
    URL_SECUNDARY(BASE_URL_SECUNDARY)
}