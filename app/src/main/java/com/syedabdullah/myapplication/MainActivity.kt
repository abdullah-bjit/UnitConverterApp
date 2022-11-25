package com.syedabdullah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.syedabdullah.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var positionConvertFrom = 0
    private var positionConvertTo = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var convertFrom: String
    private lateinit var convertTo: String
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
                positionConvertFrom = p2
                convertFrom = p0?.getItemAtPosition(p2).toString()
                Log.d("convert", convertFrom)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.spConvertTo.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                positionConvertTo = p2
                convertTo = p0?.getItemAtPosition(p2).toString()
                Log.d("convert", convertTo)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.ivConvertArrow.setOnClickListener(View.OnClickListener {
            binding.spConvertFrom.setSelection(positionConvertTo)
            binding.spConvertTo.setSelection(positionConverFrom)
            calculate()
        })

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
