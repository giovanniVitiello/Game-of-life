package com.example.gameoflife2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var world: World
    private lateinit var worldRandom: Array<Array<Cell>>
    private lateinit var world1dRandom: List<Cell>
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        world = World(20, 20)
        worldRandom = world.addAliveCellRandom()
        world1dRandom = world.transform2dTo1dArray(worldRandom)


        gridView = findViewById(R.id.gridview)
        val adapter = CellAdapter(this, R.layout.cells, world1dRandom)
        gridView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        //method to click the single item in a gridView

        gridview.setOnItemClickListener { parent, view, position, id ->
            val itemClicked = parent.getItemAtPosition(position) as Cell

            if (itemClicked.alive.equals(TypeCell.NEVERBORN)) {
                itemClicked.alive = TypeCell.ALIVE
            } else
                itemClicked.alive = TypeCell.NEVERBORN

            //create a temporany array and set the clicked cell with black

            worldRandom[position / 20][(position % 20)] = itemClicked

//        transfrom the array 2d in array 1d and show him with the adapter

            val tempArr1d = world.transform2dTo1dArray(worldRandom)
            val tempAdapter = CellAdapter(this, R.layout.cells, tempArr1d)
            gridView.adapter = tempAdapter

            start.setOnClickListener {
                val finalMatrix = world.nextGeneration(worldRandom)
                val tempnewArr1d = world.transform2dTo1dArray(finalMatrix)
                val tempnewAdapter = CellAdapter(this, R.layout.cells, tempnewArr1d)
                gridView.adapter = tempnewAdapter
            }


        }
    }
}



