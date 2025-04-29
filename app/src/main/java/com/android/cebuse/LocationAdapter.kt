package com.android.cebuse

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class LocationAdapter(private val context: Activity, private val locations: List<Location>) : BaseAdapter() {

    private val PREFS_NAME = "FavoritesPrefs"
    private val KEY_LOCATION_FAVORITES = "location_favorites"

    override fun getCount(): Int = locations.size

    override fun getItem(position: Int): Any = locations[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = convertView ?: inflater.inflate(R.layout.list_item_location, null)

        val imageView = rowView.findViewById<ImageView>(R.id.imageLocation)
        val nameView = rowView.findViewById<TextView>(R.id.textName)
        val descView = rowView.findViewById<TextView>(R.id.textDescription)
        val favoriteIcon = rowView.findViewById<ImageView>(R.id.favorite_icon)

        val location = locations[position]
        imageView.setImageResource(location.imageResId)
        nameView.text = location.name
        descView.text = location.description

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favorites = prefs.getStringSet(KEY_LOCATION_FAVORITES, mutableSetOf()) ?: mutableSetOf()

        if (favorites.contains(location.name)) {
            favoriteIcon.setImageResource(R.drawable.ic_star_filled)
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_star_outline)
        }

        favoriteIcon.setOnClickListener {
            val editor = prefs.edit()
            if (favorites.contains(location.name)) {
                favorites.remove(location.name)
                favoriteIcon.setImageResource(R.drawable.ic_star_outline)
            } else {
                favorites.add(location.name)
                favoriteIcon.setImageResource(R.drawable.ic_star_filled)
            }
            editor.putStringSet(KEY_LOCATION_FAVORITES, favorites)
            editor.apply()
        }

        return rowView
    }
}