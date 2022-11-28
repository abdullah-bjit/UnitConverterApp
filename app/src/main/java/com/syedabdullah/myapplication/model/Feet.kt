package com.syedabdullah.myapplication.model

class Feet: Units() {
    override fun calculate(value: Double, unit: String): Double {
        val result=when(unit){
            "Kilo-Meter"->value*0.0003048
            "Meter"->value*0.3048
            "Inch"->value*12
            else->value
        }
        return result
    }
}
