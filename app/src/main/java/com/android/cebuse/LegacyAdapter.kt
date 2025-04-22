package com.android.cebuse

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class LegacyAdapter(private val context: Activity, private val legacies: List<LegacyItem>) : BaseAdapter() {
    override fun getCount(): Int = legacies.size
    override fun getItem(position: Int): Any = legacies[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_location, parent, false)

        val legacy = legacies[position]
        val nameTextView = view.findViewById<TextView>(R.id.textName)
        val descriptionTextView = view.findViewById<TextView>(R.id.textDescription)
        val imageView = view.findViewById<ImageView>(R.id.imageLocation)

        nameTextView.text = legacy.name
        descriptionTextView.text = legacy.description
        imageView.setImageResource(R.drawable.ic_bluelogo)

        return view
    }
}