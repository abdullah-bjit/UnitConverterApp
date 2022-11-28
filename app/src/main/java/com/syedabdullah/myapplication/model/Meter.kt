package com.syedabdullah.myapplication.model

class Meter: Units() {
    override fun calculate(value: Double,unit: String): Double {
        val result=when(unit){
            "Kilo-Meter"->value*0.001
            "Feet"->value*3.28084
            "Inch"->value*39.3701
            else->value
        }
        return result
    }
}