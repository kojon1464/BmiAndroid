package com.example.firstapplication

import com.example.firstapplication.bmi.BmiImperial
import org.junit.Assert
import org.junit.Test

class BmiImperialTest {

    @Test
    fun testCalculate() {
        val calculator = BmiImperial()
        Assert.assertEquals(20.341, calculator.calculate(72, 150), 0.001)
    }

    @Test(expected = Exception::class)
    fun testMinHeightError() {
        val calculator = BmiImperial()
        calculator.calculate(30, 100)
    }

    @Test(expected = Exception::class)
    fun testMaxHeightError() {
        val calculator = BmiImperial()
        calculator.calculate(130, 50)
    }

    @Test(expected = Exception::class)
    fun testMinWeightError() {
        val calculator = BmiImperial()
        calculator.calculate(50, 49)
    }

    @Test(expected = Exception::class)
    fun testMaxWeightError() {
        val calculator = BmiImperial()
        calculator.calculate(50, 420)
    }
}