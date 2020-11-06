package com.example.firstapplication.bmi

import java.lang.Exception

abstract class AbstractBmiCalculator : BmiInterface {

    protected fun validate(height: Int, weight: Int) {
        if (height < getMinHeight() || height > getMaxHeight()) {
            throw Exception("Height out of range ${getMinHeight()}..${getMaxHeight()}. Given: ${height})")
        }

        if (weight < getMinWeight() || weight > getMaxWeight()) {
            throw Exception("Height out of range ${getMinWeight()}..${getMaxWeight()}. Given: ${weight})")
        }
    }
}