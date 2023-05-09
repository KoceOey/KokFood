package com.example.fooddeliver.domain

import java.io.Serializable

class FoodDomain (
    var title: String,
    var pic: String,
    var description: String,
    var fee: Double,
    var star: Int,
    var time: Int,
    var calories: Int,
    var numberInCart: Int = 0

)
