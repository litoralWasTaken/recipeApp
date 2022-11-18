package net.litoral.recipeapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class RecipeBox: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_box)

        val intentId = intent.getStringExtra("btnId")
        val mealTitle = intent.getStringExtra("mealTitle")
        val mealInstructions = intent.getStringExtra("mealInstructions")
        val mealStrThumb = intent.getStringExtra("mealStrThumb")

        val titleView = findViewById<TextView>(R.id.RecipeTitle)
        val instructionView = findViewById<TextView>(R.id.RecipeBody)
        val recipeImgView = findViewById<ImageView>(R.id.RecipePreview)

        titleView.text = mealTitle
        instructionView.text = mealInstructions

        Picasso.get().load(mealStrThumb).into(recipeImgView)
    }
}