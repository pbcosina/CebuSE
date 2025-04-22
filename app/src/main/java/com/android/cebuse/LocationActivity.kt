package com.android.cebuse

import android.app.Activity
import android.os.Bundle
import android.widget.ListView

class LocationActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val listView: ListView = findViewById(R.id.listView)

        val locations = listOf(
            Location("Kawasan Falls", "Located in Badian, Kawasan Falls is renowned for its striking turquoise waters and lush surroundings.", R.drawable.developer1),
            Location("Oslob Whale Shark Watching", "Swim alongside gentle whale sharks in their natural habitat.", R.drawable.developer1),
            Location("Sumilon Island", "Pristine white-sand beaches and crystal-clear waters perfect for snorkeling.", R.drawable.developer1),
            Location("Osmeña Peak", "The highest point in Cebu with panoramic views of the island.", R.drawable.developer1),
            Location("Moalboal Sardine Run and Turtle Point", "Witness a mesmerizing sardine run and swim with sea turtles.", R.drawable.developer1),
            Location("Pescador Island", "A diver's paradise with vibrant coral reefs.", R.drawable.developer1),
            Location("Mantayupan Falls", "Two-tiered waterfall nestled in lush greenery, great for swimming.", R.drawable.developer1),
            Location("Cambais Falls", "Hidden gem in Alegria with multiple cascades and turquoise pools.", R.drawable.developer1),
            Location("Inambakan Falls", "Five-level waterfall surrounded by jungle in Ginatilan.", R.drawable.developer1),
            Location("Lambug Beach", "Peaceful white-sand beach in Badian.", R.drawable.developer1)
        )

        val adapter = LocationAdapter(this, locations)
        listView.adapter = adapter
    }
}