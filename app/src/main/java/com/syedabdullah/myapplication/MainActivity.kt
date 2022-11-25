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
    private fun calculate(){

    }
}

abstract class Units{
    abstract fun calculate(unit:String):Double
}

class Kilometer: Units() {
    override fun calculate(unit: String):Double {
        return 0.0
    }
}
class Meter: Units() {
    override fun calculate(unit: String): Double {
        return 0.0
    }
}
class Feet: Units() {
    override fun calculate(unit: String): Double {
        return 0.0
    }
}

class Inche:Units(){
    override fun calculate(unit: String): Double {
        return 0.0
    }
}
