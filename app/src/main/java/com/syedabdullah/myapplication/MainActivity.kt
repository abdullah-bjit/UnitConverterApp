package com.syedabdullah.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.syedabdullah.myapplication.databinding.ActivityMainBinding
import com.syedabdullah.myapplication.model.*

class MainActivity : AppCompatActivity() {
    private var positionConvertFrom = 0
    private var positionConvertTo = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var convertFrom: String
    private lateinit var convertTo: String

    //    private var input: String = null.toString()
    private val key_value = "key_value"
    private val key_result = "key_result"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spConvertFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                positionConvertFrom = p2
                convertFrom = p0?.getItemAtPosition(p2).toString()
                Log.d("convert", convertFrom)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        binding.spConvertTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                positionConvertTo = p2
                convertTo = p0?.getItemAtPosition(p2).toString()
                Log.d("convert", convertTo)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //swaping unit
        binding.ivConvertArrow.setOnClickListener(View.OnClickListener {
            swapUnits()
        })

        //dropdown work
        binding.calculateButton.setOnClickListener {
            calculate()
        }

    }

    //saveInstanceState functionality
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(key_value, binding.teConvertTo.editText?.text.toString())
        outState.putString(key_result, binding.tvResult.text.toString())
    }

    // restore saveInstanceState
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.teConvertTo.editText?.setText(
            savedInstanceState.getString(key_value)
        )
        binding.tvResult.text = savedInstanceState.getString(key_result)
    }

    //calculate function
    private fun calculate() {
        val value = (binding.teConvertTo.editText?.text.toString()).toDoubleOrNull() ?: return
        var result: Double
        when (convertFrom) {
            "Kilo-Meter" -> {
                val km = Kilometer()
                result = km.calculate(value, convertTo)
            }
            "Meter" -> {
                val m = Meter()
                result = m.calculate(value, convertTo)
            }
            "Feet" -> {
                val f = Feet()
                result = f.calculate(value, convertTo)
            }
            else -> {
                val i = Inch()
                result = i.calculate(value, convertTo)
            }
        }
        binding.tvResult.text = result.toString()
        Log.d("result", result.toString())
    }


    //swap units
    private fun swapUnits() {
        binding.spConvertFrom.setSelection(positionConvertTo)
        binding.spConvertTo.setSelection(positionConvertFrom)
        var temp = convertFrom
        convertFrom = convertTo
        convertTo = temp
        calculate()
    }
}
