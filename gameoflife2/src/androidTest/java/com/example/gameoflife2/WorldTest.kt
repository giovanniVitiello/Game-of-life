package com.example.gameoflife2

import com.example.gameoflife2.TypeCell.ALIVE
import org.junit.Test

import org.junit.Assert.*

class WorldTest {

    @Test
    fun getWorldIsEmpty() {
        val world = World(20,20)
        val expected = 0
        val output = world.world

        assertEquals(output.size,expected)
    }


    @Test
    fun getInitialWorldIsNotEmpty() {
        val world = World(20,20)
        val expected = 400
        val output = world.initialWorld()

        assertEquals(output.size.times(output[0].size),expected)
    }


    @Test
    fun world_is_not_empty_after_adding_a_cell(){
        val world = World(20,20)
        val emptyWorld = world.transform2dTo1dArray(world.initialWorld()).toMutableList()
        val location = Location(0,0)
        location.x = (0..399).random()
        val cell = Cell(ALIVE)
        emptyWorld[location.x] = cell

        assertTrue(emptyWorld.contains(cell))

    }


    @Test
    fun transform2dTo1dArray() {
        val cell = Cell(ALIVE)
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