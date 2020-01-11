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
    private lateinit var arr1d: List<String>
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        world = World()
        arr1d = world.transform2dTo1dArray(world.result)

        gridView = findViewById(R.id.gridview)
        val adapter = ArrayAdapter<String>(this, R.layout.cells, arr1d)
        gridView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        //method to click the single item in a gridView

        gridview.setOnItemClickListener { parent, view, position, id ->
            val itemClicked = parent.getItemAtPosition(position).toString()
            val changedString: String
            val x = " "
            val y = "IIII"
            if (itemClicked.contains(" ")) {
                changedString = itemClicked.replace(x, y, false)
            } else
                changedString = itemClicked.replace(y, x, false)

            //create a temporany array and set the clicked cell with "X"

            val tempArr = world.result
            tempArr[position / 20][(position % 20)] = changedString

            //transfrom the array 2d in array 1d and show him with the adapter

            val tempArr1d = world.transform2dTo1dArray(tempArr)
            val tempAdapter = ArrayAdapter<String>(this, R.layout.cells, tempArr1d)
            gridView.adapter = tempAdapter

            start.setOnClickListener {
                val finalMatrix = world.nextGeneration(tempArr)
                val tempnewArr1d = world.transform2dTo1dArray(finalMatrix)
                val tempnewAdapter = ArrayAdapter<String>(this, R.layout.cells, tempnewArr1d)
                gridView.adapter = tempnewAdapter
            }


        }
    }
}



