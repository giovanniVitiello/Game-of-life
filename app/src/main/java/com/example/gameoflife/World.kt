package com.example.gameoflife

import android.widget.ArrayAdapter
import android.widget.GridView

class World {

    var column : Int
    var row : Int
    var result : Array<Array<String>>
    var cell: Cell

    init {
        column = 20
        row = 20
        result = Array(row) { Array(column) { " " } }
        cell = Cell()
    }


    fun transform2dTo1dArray(arr2d : Array<Array<String>>): List<String>{
        val arr1d = ArrayList<String>()
        for (i in 0 until arr2d.size) {
            for (s in 0 until arr2d[i].size) {
                arr1d.add(arr2d[i][s])
            }
        }
        return arr1d
    }

//    fun nextGeneration() : List<String>{
//        if ()
//
//        return emptyList()
//    }


}