package net.litoral.recipeapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val TAG = "recipeApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this

        val linearLayout = findViewById<LinearLayout>(R.id.linearScrollLayout)
        val scrollView = findViewById<ScrollView>(R.id.recipeScrollView)

        val baseUrl = "https://www.themealdb.com/"
//        var strMeal ="{\"meals\":[{\"idMeal\":\"52806\",\"strMeal\":\"Tandoori chicken\",\"strDrinkAlternate\":null,\"strCategory\":\"Chicken\",\"strArea\":\"Indian\",\"strInstructions\":\"Mix the lemon juice with the paprika and red onions in a large shallow dish. Slash each chicken thigh three times, then turn them in the juice and set aside for 10 mins.\\r\\nMix all of the marinade ingredients together and pour over the chicken. Give everything a good mix, then cover and chill for at least 1 hr. This can be done up to a day in advance.\\r\\nHeat the grill. Lift the chicken pieces onto a rack over a baking tray. Brush over a little oil and grill for 8 mins on each side or until lightly charred and completely cooked through.\",\"strMealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/qptpvt1487339892.jpg\",\"strTags\":\"Spicy,Meat\",\"strYoutube\":\"https:\\/\\/www.youtube.com\\/watch?v=-CKvt1KNU74\",\"strIngredient1\":\"lemons\",\"strIngredient2\":\"paprika\",\"strIngredient3\":\"red onions\",\"strIngredient4\":\"chicken thighs\",\"strIngredient5\":\"vegetable oil\",\"strIngredient6\":\"Greek yogurt\",\"strIngredient7\":\"ginger\",\"strIngredient8\":\"garlic clove\",\"strIngredient9\":\"garam masala\",\"strIngredient10\":\"ground cumin\",\"strIngredient11\":\"chilli powder\",\"strIngredient12\":\"turmeric\",\"strIngredient13\":\"\",\"strIngredient14\":\"\",\"strIngredient15\":\"\",\"strIngredient16\":\"\",\"strIngredient17\":\"\",\"strIngredient18\":\"\",\"strIngredient19\":\"\",\"strIngredient20\":\"\",\"strMeasure1\":\"2 Juice\",\"strMeasure2\":\"4 tsp\",\"strMeasure3\":\"2 finely chopped\",\"strMeasure4\":\"16 skinnless\",\"strMeasure5\":\"For brushing\",\"strMeasure6\":\"300ml \",\"strMeasure7\":\"large piece\",\"strMeasure8\":\"4\",\"strMeasure9\":\"\\u00be tsp\",\"strMeasure10\":\"\\u00be tsp\",\"strMeasure11\":\"\\u00bd tsp\",\"strMeasure12\":\"\\u00bc tsp\",\"strMeasure13\":\"\",\"strMeasure14\":\"\",\"strMeasure15\":\"\",\"strMeasure16\":\"\",\"strMeasure17\":\"\",\"strMeasure18\":\"\",\"strMeasure19\":\"\",\"strMeasure20\":\"\",\"strSource\":\"http:\\/\\/www.bbcgoodfood.com\\/recipes\\/1660651\\/tandoori-chicken\",\"strImageSource\":null,\"strCreativeCommonsConfirmed\":null,\"dateModified\":null}]}"

        val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        for (i in 0..100) {
            val meal = api.create(MealInterface::class.java).getRandomMeal()
            var result: MealList? = null
            var resultList: List<MealList>? = null

            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    result = meal.execute().body()

                }

                withContext(Dispatchers.Main) {
                    // generar botones
                    result!!.meals.forEachIndexed { i, meal ->
                        var imgBtn = ImageButton(context)

                        if (meal != null) {
                            Picasso.get().load(meal.strMealThumb).into(imgBtn)
                        }

                        imgBtn.setOnClickListener {
                            val intent = Intent(context, RecipeBox::class.java).apply {
                                putExtra("btnId", i.toString())
                                putExtra("mealTitle", meal.strMeal)
                                putExtra("mealInstructions", meal.strInstructions)
                                putExtra("mealStrThumb", meal.strMealThumb)
                            }

                            startActivity(intent)
                        }

                        linearLayout.addView(imgBtn)

                    }
                }
            }
        }






//        val recipeView = layoutInflater.inflate(R.layout.recipe_box, null)
//        val recipeView2 = layoutInflater.inflate(R.layout.recipe_box, null)
//        linearLayout.addView(recipeView)
//        linearLayout.addView(recipeView2)

//        for (i in 1..100) {
//           val btn = Button(this)
//            btn.text = i.toString();
//
//            btn.setOnClickListener {
//                val intent = Intent(this, RecipeBox::class.java).apply {
//                    putExtra("btnId", i.toString())
//                }
//
//                startActivity(intent)
//            }
//
//            linearLayout.addView(btn)
//
//        }
//        val view = layoutInflater.inflate(R.layout.recipe_box, null)
//        scrollView.addView(view)

    }
}


