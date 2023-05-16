package com.example.beersapplication.data.network.model

import com.example.beersapp.data.network.model.Temp
import com.google.gson.annotations.SerializedName


data class Fermentation (

  @SerializedName("temp" ) var temp : Temp? = Temp()

)