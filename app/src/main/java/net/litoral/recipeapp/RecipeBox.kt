package net.litoral.recipeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeBox: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_box)

        val intentId = intent.getStringExtra("btnId")
        val textView = findViewById<TextView>(R.id.RecipeTitle)
        textView.text = intentId.toString()


    }
}