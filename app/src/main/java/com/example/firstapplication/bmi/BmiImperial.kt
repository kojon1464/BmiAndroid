package com.example.firstapplication.bmi

import kotlin.math.pow

class BmiImperial: AbstractBmiCalculator() {
    override fun calculate(height: Int, weight: Int): Double {
        validate(height, weight)
        return (weight / (height.toDouble().pow(2))) * 703
    }

    override fun getMaxHeight(): Int {
        return 100
    }

    override fun getMinHeight(): Int {
        return 36
    }

    override fun getMaxWeight(): Int {
        return 400
    }

    override fun getMinWeight(): Int {
        return 50
    }
}