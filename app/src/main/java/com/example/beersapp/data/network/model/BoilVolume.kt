package com.example.beersapp.data.network.model

import com.google.gson.annotations.SerializedName


data class BoilVolume(

    @SerializedName("value") var value: Float? = null,
    @SerializedName("unit") var unit: String? = null

)