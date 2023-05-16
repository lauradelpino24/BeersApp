package com.example.beersapp.data.network.model

import com.google.gson.annotations.SerializedName


data class PunkResponse(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("first_brewed") var firstBrewed: String,
    @SerializedName("description") var description: String,
    @SerializedName("image_url") var imageUrl: String,
    @SerializedName("abv") var abv: Float,
    @SerializedName("ibu") var ibu: Float,
    @SerializedName("target_fg") var targetFg: Float,
    @SerializedName("target_og") var targetOg: Float,
    @SerializedName("ebc") var ebc: Float,
    @SerializedName("srm") var srm: Float,
    @SerializedName("ph") var ph: Float,
    @SerializedName("attenuation_level") var attenuationLevel: Float,
    @SerializedName("volume") var volume: Volume? = Volume(),
    @SerializedName("boil_volume") var boilVolume: BoilVolume? = BoilVolume(),
    @SerializedName("method") var method: Method? = Method(),
    @SerializedName("ingredients") var ingredients: Ingredients? = Ingredients(),
    @SerializedName("food_pairing") var foodPairing: ArrayList<String> = arrayListOf(),
    @SerializedName("brewers_tips") var brewersTips: String,
    @SerializedName("contributed_by") var contributedBy: String

)