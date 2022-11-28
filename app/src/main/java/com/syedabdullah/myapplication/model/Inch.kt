package com.syedabdullah.myapplication.model

class Inch:Units() {
    override fun calculate(value: Double, unit: String): Double {
        val result = when (unit) {
            "Kilo-Meter" -> value.div(39370.1)
            "Meter" -> value * 0.0254
            "Feet" -> value * 0.0833333
            else -> value
        }
        return result
    }
}