package com.android.cebuse

import android.app.Activity
import android.os.Bundle
import android.widget.ListView

class CuisineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuisine)

        val cuisineListView = findViewById<ListView>(R.id.cuisineListView)

        val cuisines = listOf(
            CuisineItem("Cebu Lechon", "Crispy-skinned, juicy roasted pig, marinated with herbs and slow-roasted to perfection.", R.drawable.developer1),
            CuisineItem("Lechon Paksiw Cebu", "Leftover lechon simmered in vinegar, garlic, and spices for a tangy twist.", R.drawable.developer1 ),
            CuisineItem("Ampao", "Crispy puffed rice snack with caramelized sugar, enjoyed during festivals.", R.drawable.developer1 ),
            CuisineItem("Otap", "Flaky, sugary puff pastry perfect with coffee or tea.", R.drawable.developer1),
            CuisineItem("Danggit", "Crispy dried fish served with rice and vinegar dip, popular for breakfast.", R.drawable.developer1),
            CuisineItem("Puso", "Rice steamed in woven palm leaves, served with grilled meat or stews.", R.drawable.developer1),
            CuisineItem("Chorizo de Cebu", "Sweet and spicy grilled pork sausage, a local favorite.", R.drawable.developer1),
            CuisineItem("Bod Bod", "Sticky rice with coconut milk, wrapped in banana leaves and steamed.", R.drawable.developer1),
            CuisineItem("Bukayo", "Caramelized mature coconut treat with a chewy texture.", R.drawable.developer1),
            CuisineItem("Linubihang Kagang", "Alegria-style crab cooked in coconut milk with ginger and spices.", R.drawable.developer1)
        )

        val adapter = CuisineAdapter(this, cuisines)
        cuisineListView.adapter = adapter
    }
}