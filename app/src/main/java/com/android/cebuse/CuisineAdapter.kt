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

    private val PREFS_NAME = "FavoritesPrefs"
    private val KEY_CUISINE_FAVORITES = "cuisine_favorites"

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_location, parent, false)

        val cuisine = getItem(position)

        val titleText = itemView.findViewById<TextView>(R.id.textName)
        val descriptionText = itemView.findViewById<TextView>(R.id.textDescription)
        val imageView = itemView.findViewById<ImageView>(R.id.imageLocation)
        val favoriteIcon = itemView.findViewById<ImageView>(R.id.favorite_icon)

        titleText.text = cuisine?.title
        descriptionText.text = cuisine?.description
        cuisine?.imageResId?.let { imageView.setImageResource(it) }

        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val favorites = prefs.getStringSet(KEY_CUISINE_FAVORITES, mutableSetOf()) ?: mutableSetOf()

        if (favorites.contains(cuisine?.title)) {
            favoriteIcon.setImageResource(R.drawable.ic_star_filled)
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_star_outline)
        }

        favoriteIcon.setOnClickListener {
            val editor = prefs.edit()
            cuisine?.title?.let {
                if (favorites.contains(it)) {
                    favorites.remove(it)
                    favoriteIcon.setImageResource(R.drawable.ic_star_outline)
                } else {
                    favorites.add(it)
                    favoriteIcon.setImageResource(R.drawable.ic_star_filled)
                }
                editor.putStringSet(KEY_CUISINE_FAVORITES, favorites)
                editor.apply()
            }
        }

        return itemView
    }
}