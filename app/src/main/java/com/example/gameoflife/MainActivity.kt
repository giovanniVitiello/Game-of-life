package com.example.gameoflife

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.cells.*
import kotlinx.android.synthetic.main.cells.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var world: World
    private lateinit var arr1d: List<Cell>
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        world = World(20, 20)
        arr1d = world.transform2dTo1dArray(world.result)

        gridView = findViewById(R.id.gridview)
        val adapter = CellAdapter(this, R.layout.cells, arr1d)
        gridView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        //method to click the single item in a gridView

        gridview.setOnItemClickListener { parent, view, position, id ->
            val itemClicked = parent.getItemAtPosition(position) as Cell

            if (!itemClicked.alive) {
                itemClicked.alive = true
            } else
               itemClicked.alive = false

            //create a temporany array and set the clicked cell with "X"

            val tempArr = world.result
            tempArr[position / world.rows][(position % world.columns)] = itemClicked

            //transfrom the array 2d in array 1d and show him with the adapter

            val tempArr1d = world.transform2dTo1dArray(tempArr)
            val tempAdapter = CellAdapter(this, R.layout.cells, tempArr1d)
            gridView.adapter = tempAdapter

            start.setOnClickListener {
                val finalMatrix = world.nextGeneration(tempArr)
                val tempnewArr1d = world.transform2dTo1dArray(finalMatrix)
                val tempnewAdapter = CellAdapter(this, R.layout.cells, tempnewArr1d)
                gridView.adapter = tempnewAdapter
            }


        }
    }
}



