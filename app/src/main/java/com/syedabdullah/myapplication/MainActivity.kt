package com.syedabdullah.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
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
    private val key_value = "key_value"
    private val key_result = "key_result"
    private val SPEECH_REQUEST_CODE = 0

    // Create an intent that can start the Speech Recognizer activity
    @Suppress("DEPRECATION")
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }
        // This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0) ?: return
                }
            // Do something with spokenText.
            Log.d("voice", spokenText)
            binding.inputTextET?.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("ClickableViewAccessibility")

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

        binding.ivConvertArrow.setOnClickListener(View.OnClickListener {
            swapUnits()
        })



        //dropdown work
        binding.calculateButton.setOnClickListener {
            calculate()
        }

        //voice input
        binding.voiceInputFAB?.setOnClickListener {
            displaySpeechRecognizer()
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
        val result: Double
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

        val history = History()
        addHistory(value, result, convertFrom, convertTo)
        showHistory()
        Log.d("History", history.getHistory().toString())
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

    //function that add data to list of History
    private fun addHistory(value: Double, result: Double, convertFrom: String, convertTo: String) {
        val history = History()
        history.addToHistory(value, result, convertFrom, convertTo)
    }

    //print history
    private fun showHistory(){
        val history = History()
        val lists=history.getHistory()
        var ans=""
        for(i in lists){
            ans+=i+'\n'
        }
        binding.tvHistory?.text=ans
    }
}
