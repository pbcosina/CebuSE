package com.android.cebuse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CuisineAdapter(context: Context, cuisines: List<CuisineItem>) :
    ArrayAdapter<CuisineItem>(context, R.layout.list_item_location, cuisines) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_location, parent, false)

        val cuisine = getItem(position)

        val titleText = itemView.findViewById<TextView>(R.id.textName)
        val descriptionText = itemView.findViewById<TextView>(R.id.textDescription)
        val imageView = itemView.findViewById<ImageView>(R.id.imageLocation)

        titleText.text = cuisine?.title
        descriptionText.text = cuisine?.description
        cuisine?.imageResId?.let { imageView.setImageResource(it) }

        return itemView
    }
}