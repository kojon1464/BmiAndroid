package com.example.firstapplication.bmi

import kotlin.math.pow

class BmiMetric: AbstractBmiCalculator() {
    override fun calculate(height: Int, weight: Int): Double {
        validate(height, weight)
        return weight / ((height.toDouble() / 100).pow(2))
    }

    override fun getMaxHeight(): Int {
        return 250
    }

    override fun getMinHeight(): Int {
        return 100
    }

    override fun getMaxWeight(): Int {
        return 200
    }

    override fun getMinWeight(): Int {
        return 30
    }
}