package com.android.cebuse

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class LocationAdapter(private val context: Activity, private val locations: List<Location>) : BaseAdapter() {

    override fun getCount(): Int = locations.size

    override fun getItem(position: Int): Any = locations[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = convertView ?: inflater.inflate(R.layout.list_item_location, null)

        val imageView = rowView.findViewById<ImageView>(R.id.imageLocation)
        val nameView = rowView.findViewById<TextView>(R.id.textName)
        val descView = rowView.findViewById<TextView>(R.id.textDescription)

        val location = locations[position]
        imageView.setImageResource(location.imageResId)
        nameView.text = location.name
        descView.text = location.description

        return rowView
    }
}