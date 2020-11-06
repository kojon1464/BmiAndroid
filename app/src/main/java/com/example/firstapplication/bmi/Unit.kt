package com.example.firstapplication.bmi

import java.io.Serializable

enum class Unit(val mass_unit: String, val height_unit: String): Serializable {
    METRIC("kg", "cm"),
    IMPERIAL("lb", "in")
}