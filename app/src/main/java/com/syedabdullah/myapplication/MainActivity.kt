package com.syedabdullah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syedabdullah.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //dropdown work
        val convertFrom= binding.spConvertFrom
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
