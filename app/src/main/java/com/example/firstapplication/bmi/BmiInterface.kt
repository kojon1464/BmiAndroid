package com.example.firstapplication.bmi

interface BmiInterface {

    fun calculate(height: Int, weight: Int): Double

    fun getMaxHeight(): Int
    fun getMinHeight(): Int

    fun getMaxWeight(): Int
    fun getMinWeight(): Int
}