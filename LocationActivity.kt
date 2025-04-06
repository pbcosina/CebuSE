package com.android.cebuse

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LocationActivity : Activity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocationAdapter
    private val locationList = mutableListOf<Location>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        locationList.add(Location("Cebu City", "Capital of Cebu Province"))
        locationList.add(Location("Lapu-Lapu City", "Famous for Mactan Shrine"))
        locationList.add(Location("Oslob", "Home of whale sharks"))

        adapter = LocationAdapter(locationList)
        recyclerView.adapter = adapter
    }

    data class Location(
        val name: String,
        val details: String
    )

    class LocationAdapter(private val locationList: List<Location>) :
        RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

        inner class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameText: TextView = view.findViewById(R.id.locationName)
            val detailsText: TextView = view.findViewById(R.id.locationDetails)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_location, parent, false)
            return LocationViewHolder(view)
        }

        override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
            val location = locationList[position]
            holder.nameText.text = location.name
            holder.detailsText.text = location.details
        }

        override fun getItemCount(): Int = locationList.size
    }
}

