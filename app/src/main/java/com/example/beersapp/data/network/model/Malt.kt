package com.example.beersapp.data.network.model

import com.example.beersapp.data.network.model.Amount
import com.google.gson.annotations.SerializedName


data class Malt(

    @SerializedName("name") var name: String? = null,
    @SerializedName("amount") var amount: Amount? = Amount()

)