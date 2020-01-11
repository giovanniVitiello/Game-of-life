package com.example.gameoflife

class World constructor(val rows: Int, val columns: Int){

    var result : Array<Array<Cell>>

    init {

        result = Array(rows) { Array(columns) { Cell(0,0,false) } }
        for (row in 0 until rows ){
            for(column in 0 until columns){
                val cell = Cell(row, column, false)
                result[row][column] = cell
            }
        }

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
                    val cell: Cell = result.get(k).get(l)
                    if (cell.alive) {
                        nb++
                    }
                }
            }
        }
        return nb
    }

//    fun bornCell(cell : String) : String{
//           if (cell.equals(" ")){
//               cell.replace(" ", "IIII")
//           }
//        return cell
//    }
//
//    fun deadCell(cell : String) : String{
//        if (cell.equals("IIII")){
//            cell.replace(" ", " ")
//        }
//        return cell
//    }

    fun nextGeneration(matrix : Array<Array<Cell>>) : Array<Array<Cell>>{

        for (i in 0 until rows){
            for (j in 0 until columns){
                val nbNeighbours = numNeighboursOf(i, j)

                // rule 1 & rule 3
                if (matrix[i][j].alive &&
                    (nbNeighbours < 2 || nbNeighbours > 3)) {
                    matrix[i][j].alive = false
                }

                // rule 2 & rule 4
                if ((matrix[i][j].alive && (nbNeighbours == 3 || nbNeighbours == 2))
                    ||
                    (!matrix[i][j].alive && nbNeighbours == 3)) {
                    matrix[i][j].alive = true
                }
            }
        }
        return matrix
    }


}