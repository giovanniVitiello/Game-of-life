package com.example.gameoflife

import org.junit.Test

import org.junit.Assert.*

class WorldTest {

    @Test
    fun transform2dTo1dArray() {
        val cell = Cell(TypeCell.ALIVE)
        val input = Array(5) { Array(5) { cell } }
        val output: Array<Cell>
        val expected = arrayOf(cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,
            cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,cell,cell)

        val world = World(5,5)
        output = world.transform2dTo1dArray(input).toTypedArray()
        assertArrayEquals(output,expected)
    }

    @Test
    fun numNeighboursOf() {
        val output : Int
        val expected = 0
        val world=World(5,5)

        output = world.numNeighboursOf(3,3)
        assertEquals(output,expected)
    }
}