package com.example.gameoflife

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.annotation.LayoutRes

class CellAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val allCell: List<Cell>):
    ArrayAdapter<Cell>(context, layoutResource, allCell)
     {
    private var mCell: List<Cell> = allCell

    override fun getCount(): Int {
        return mCell.size
    }

    override fun getItem(p0: Int): Cell? {
        return mCell.get(p0)
    }

         override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
             val view: ImageView =
                 convertView as ImageView? ?: LayoutInflater.from(context).inflate(
                     layoutResource,
                     parent,
                     false
                 ) as ImageView
             val cell = mCell[position]
             //view.setBackgroundColor( if (cell.alive) Color.parseColor("#000000") else Color.parseColor("#FFFFFF"))
             view.setImageResource(
                 if (cell.alive.equals(TypeCell.ALIVE)) R.drawable.square_cell
                 else if (cell.alive.equals(TypeCell.NEVERBORN)) R.drawable.square_cell_white
                 else R.drawable.square_cell_green
             )
             return view
         }

}