package com.syedabdullah.myapplication.model

private val listOfHistory= mutableListOf<String>()
class History {
    fun addToHistory(data:Double,result:Double, convertFrom:String, convertTo:String)
    {
        listOfHistory.add("$data Convert from $convertFrom To $convertTo result: $result")
    }
    fun getHistory():MutableList<String>{
        return listOfHistory
    }
}