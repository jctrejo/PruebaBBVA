package com.upax.bbvaprueba1.manager

import com.upax.bbvaprueba1.domain.EnvironmentModel
import com.upax.bbvaprueba1.enums.ApiType
import com.upax.bbvaprueba1.enums.DeploymentType

object EnvironmentManager {
    private val environments = mutableListOf(
        EnvironmentModel(
            apiType = ApiType.URL_SECUNDARY,
            deploymentType = DeploymentType.HTTP_WEB
        ),
        EnvironmentModel(
            apiType = ApiType.MOVILE,
            deploymentType = DeploymentType.HTTP_MOBILE
        )
    )

    fun getBaseUrl(apiType: ApiType): String {
        return environments.find { it.apiType == apiType }!!.baseUrl
    }
}
