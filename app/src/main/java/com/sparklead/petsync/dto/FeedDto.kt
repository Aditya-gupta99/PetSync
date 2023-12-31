package com.sparklead.petsync.dto

import kotlinx.serialization.Serializable

@Serializable
data class FeedDto(

    val id: String,

    val pet: String,

    val timestamp: String
)
