package com.example.firstapplication.bmi

import androidx.room.TypeConverter
import java.io.Serializable

enum class Unit(val mass_unit: String, val height_unit: String): Serializable {
    METRIC("kg", "cm"),
    IMPERIAL("lb", "in")
}

class UnitConverter {

    @TypeConverter
    fun toUnit(value: String) = enumValueOf<Unit>(value)

    @TypeConverter
    fun fromUnit(value: Unit) = value.name
}