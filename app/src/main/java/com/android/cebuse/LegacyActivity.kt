package com.android.cebuse

import android.app.Activity
import android.os.Bundle
import android.widget.ListView

class LegacyActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legacy)

        val listView = findViewById<ListView>(R.id.legacyListView)

        val legacies = listOf(
            LegacyItem("Simala Shrine (Monastery of the Holy Eucharist) – Sibonga", "Castle-like Marian pilgrimage site. Famous for miracles and spiritual devotion.", R.drawable.developer1),
            LegacyItem("Carcar City Heritage Area – Carcar", "Home to ancestral houses, St. Catherine of Alexandria Church, and Balay na Tisa. Preserves Spanish and American-period architecture.", R.drawable.developer1),
            LegacyItem("Boljoon Church (Patrocinio de Maria) – Boljoon", "Declared as a National Cultural Treasure. Oldest remaining original church complex in Cebu.", R.drawable.developer1),
            LegacyItem("Argao Church (San Miguel Arcangel Church) – Argao", "Built in 1734 with baroque features and thick coral stone walls. Adjacent to an old convent and Spanish-era plaza.", R.drawable.developer1),
            LegacyItem("Dalaguete Church (San Guillermo de Aquitania) – Dalaguete", "Fortress-style church with watchtowers. A major religious and historical landmark.", R.drawable.developer1),
            LegacyItem("Oslob Cuartel & Immaculate Conception Church – Oslob", "Cuartel: Unfinished Spanish barracks made of coral stones. Church: Built in 1830s, restored after fire.", R.drawable.developer1),
            LegacyItem("Baluarte (Watchtower) – Oslob", "Coastal watchtower used against Moro pirate attacks. One of several old baluartes in the area.", R.drawable.developer1),
            LegacyItem("Carcar Rotunda & Old Train Station – Carcar", "Rotunda surrounded by heritage buildings. The old Carcar train station marks the Cebu railway system from early 1900s.", R.drawable.developer1),
            LegacyItem("St. Francis of Assisi Church – Naga", "Historical church known for its simplicity and age. Serves as a spiritual landmark in Southern Cebu.", R.drawable.developer1),
            LegacyItem("Valladolid Church (San Guillermo de Aquitania Parish) – Carcar", "Distinct from Dalaguete’s church but similarly historic. Built during Spanish period, with stunning interior paintings.", R.drawable.developer1)
        )
        val adapter = LegacyAdapter(this, legacies)
        listView.adapter = adapter
    }
}