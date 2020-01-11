package com.example.gameoflife

class Cell {
    var alive : Boolean = true
    var x : Int = 0
    var y : Int = 0

    fun liveCell () : Boolean = alive

    fun deadCell () : Boolean = !alive
}