package com.syedabdullah.myapplication.model

class Kilometer: Units() {
    override fun calculate(value: Double,unit: String):Double {
        val result=when(unit){
            "Meter"->value*1000
            "Feet"->value*3280.84
            "Inch"->value*39370.08
            else->value
        }
        return result
    }
}