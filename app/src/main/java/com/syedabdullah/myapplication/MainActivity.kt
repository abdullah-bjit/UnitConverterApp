package com.syedabdullah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.syedabdullah.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //dropdown work
        val calculate=binding.calculateButton
        calculate.setOnClickListener{

        }

        binding.spConvertFrom.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }

    //calculate funtion
    private fun calculate(convertFrom:String, convertTo:String, value:Double ):Double{
        var result=0.0
        when(convertFrom){
            "Kilo-Meter"->{
                val km=Kilometer()
                result=km.calculate(value,convertTo)
            }
            "Meter"->{
                val m=Meter()
                result=m.calculate(value,convertTo)
            }
            "Feet"->{
                val f=Feet()
                result=f.calculate(value,convertTo)
            }
            else->{
                val i=Inch()
                result=i.calculate(value,convertTo)
            }
        }
        return result
    }
}

abstract class Units{
    abstract fun calculate(value:Double,unit:String):Double
}

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

class Inch:Units(){
    override fun calculate(value: Double, unit: String): Double {
        val result=when(unit){
            "Kilo-Meter"->value.div(39370.1)
            "Meter"->value*0.0254
            "Feet"->value*0.0833333
            else->value
        }
        return result
    }
}
