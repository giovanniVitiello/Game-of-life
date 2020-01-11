package com.example.gameoflife

class World {

    var column : Int
    var row : Int
    var result : Array<Array<String>>
    var cell: String

    init {
        column = 20
        row = 20
        cell = " "
        result = Array(row) { Array(column) { cell } }
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

    fun numNeighboursOf(i: Int, j : Int): Int{
        var nb = 0

        for (k in i - 1..i + 1) {
            for (l in j - 1..j + 1) {
                if ((k != i || l != j) && k >= 0 && k < row && l >= 0 && l < column
                ) {
                    val cell: String = result.get(k).get(l)
                    if (cell.equals("IIII")) {
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

    fun nextGeneration() {
        val cellLive = ArrayList<String>()
        val cellDead = ArrayList<String>()

        for (i in 1 until row-1){
            for (j in 1 until column-1){
                val nbNeighbours = numNeighboursOf(i, j)

                // rule 1 & rule 3
                if (cell.equals("IIII") &&
                    (nbNeighbours < 2 || nbNeighbours > 3)) {
                    cellDead.add(cell);
                }

                // rule 2 & rule 4
                if ((cell.equals("IIII") && (nbNeighbours == 3 || nbNeighbours == 2))
                    ||
                    (!cell.equals("IIII") && nbNeighbours == 3)) {
                    cellLive.add(cell);
                }
            }
        }

    }


}