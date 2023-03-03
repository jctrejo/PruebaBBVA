package com.upax.bbvaprueba1.domain

import com.upax.bbvaprueba1.enums.ApiType
import com.upax.bbvaprueba1.enums.DeploymentType

data class EnvironmentModel(
    val apiType: ApiType,
    val deploymentType: DeploymentType
) {

    var baseUrl = ""

    init {
        setBaseUrl(apiType, deploymentType)
    }

    private fun setBaseUrl(
        apiType: ApiType,
        deploymentType: DeploymentType
    ) {
        val prefix = when (deploymentType) {
            DeploymentType.HTTP_WEB -> ""
            DeploymentType.HTTP_MOBILE -> ""
        }

        baseUrl = "https://${prefix}${apiType.url}"
    }

    override fun toString(): String {
        return baseUrl
    }
}