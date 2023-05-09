package com.example.fooddeliver.domain

import java.io.Serializable

class FoodDomain2(
    var title: String,
    var pic: String,
    var description: String,
    var fee: Double,
    var star: Int,
    var time: Int,
    var calories: Int
) :
    Serializable {
    var numberInCart = 0

}