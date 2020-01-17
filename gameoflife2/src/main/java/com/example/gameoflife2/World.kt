package com.example.gameoflife2

class World constructor(val rows: Int, val columns: Int){

    var world = arrayOf<Array<Cell>>()
    var newWorldRandom = arrayOf<Array<Cell>>()


    fun initialWorld() : Array<Array<Cell>>{
        world = Array(rows) { Array(columns) { Cell( TypeCell.NEVERBORN) } }
        return world
    }
    fun addAliveCellRandom(): Array<Array<Cell>> {
        val cellNeverBorn = Cell(TypeCell.NEVERBORN)
        val cellAlive = Cell(TypeCell.ALIVE)
        newWorldRandom  = Array(rows) { Array(columns) {  cellNeverBorn } }
        repeat(150){
            val location = Location(0,0)
            location.x = (0..19).random()
            location.y = (0..19).random()
            newWorldRandom[location.x][location.y] = cellAlive
        }
        return newWorldRandom
    }

    fun transform2dTo1dArray(arr2d : Array<Array<Cell>>): List<Cell>{
        val arr1d = ArrayList<Cell>()
        for (i in 0 until arr2d.size) {
            for (s in 0 until arr2d[i].size) {
                arr1d.add(arr2d[i][s])
            }
        }
        return arr1d
    }

    fun numNeighboursOf(i: Int, j : Int): Int{
        var nb = 0

        for (k in i - 1..i + 1) {
            for (l in j - 1..j + 1) {
                if ((k != i || l != j) && k >= 0 && k < rows && l >= 0 && l < columns
                ) {
                    val cell = newWorldRandom[k][l]
                    if (cell.alive == TypeCell.ALIVE) {
                        nb++
                    }
                }
            }
        }
        return nb
    }

    fun nextGeneration(matrix : Array<Array<Cell>>) : Array<Array<Cell>>{

        for (i in 0 until rows){
            for (j in 0 until columns){
                val nbNeighbours = numNeighboursOf(i, j)

                // rule 1 & rule 3
                if (matrix[i][j].alive == TypeCell.ALIVE &&
                    (nbNeighbours < 2 || nbNeighbours > 3)) {
                    matrix[i][j].alive = TypeCell.DEAD
                }

                // rule 2 & rule 4
                if ((matrix[i][j].alive == TypeCell.ALIVE && (nbNeighbours == 3 || nbNeighbours == 2))
                    ||
                    (matrix[i][j].alive == TypeCell.NEVERBORN && nbNeighbours == 3)
                    ||
                    (matrix[i][j].alive == TypeCell.DEAD && nbNeighbours == 3)){

                    matrix[i][j].alive = TypeCell.ALIVE
                }
            }
        }
        return matrix
    }



}