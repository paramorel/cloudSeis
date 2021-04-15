package com.example.cloudseis.data.responses

data class AllNetworksResponse(
    val id: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val completed: String,
    val locationDescription: String,
    val colorMain: String,
    val colorAdditional: String,
    val ownerId: String
);