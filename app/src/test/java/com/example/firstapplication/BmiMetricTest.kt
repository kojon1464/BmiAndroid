package com.example.firstapplication

import com.example.firstapplication.bmi.BmiMetric
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class BmiMetricTest {

    @Test
    fun testCalculate() {
        val calculator = BmiMetric()
        Assert.assertEquals(22.222, calculator.calculate(150, 50), 0.001)
    }

    @Test(expected = Exception::class)
    fun testMinHeightError() {
        val calculator = BmiMetric()
        calculator.calculate(90, 50)
    }

    @Test(expected = Exception::class)
    fun testMaxHeightError() {
        val calculator = BmiMetric()
        calculator.calculate(300, 50)
    }

    @Test(expected = Exception::class)
    fun testMinWeightError() {
        val calculator = BmiMetric()
        calculator.calculate(200, 10)
    }

    @Test(expected = Exception::class)
    fun testMaxWeightError() {
        val calculator = BmiMetric()
        calculator.calculate(200, 250)
    }
}