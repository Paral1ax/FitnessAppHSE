package com.mir.fitnessapplication.main.ui.food.dishes.inside

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.exercises.inside.InsideExerciseData
import com.mir.fitnessapplication.main.ui.fitness.exercises.inside.InsideExerciseItemStorage
import com.mir.fitnessapplication.main.ui.food.FoodRecyclerViewAdapter
import com.mir.fitnessapplication.main.ui.food.dishes.DishRecyclerAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class InsideDishFragment: Fragment() {
    private var youTubePlayerView: YouTubePlayerView? = null
    private var ingredients: TextView? = null
    private var recept: TextView? = null
    //lateinit var dishName: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inside_dish_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showExerciseInfoAndVideo()
    }

    private fun showExerciseInfoAndVideo() {
        val category = FoodRecyclerViewAdapter.getAdapterPos()
        val pos = DishRecyclerAdapter.getAdapterPos()

        val dish: InsideDishItemStorage = InsideDishData().mutableList[category][pos]
        youTubePlayerView = view?.findViewById(R.id.dish_youtube_view)
        ingredients = view?.findViewById(R.id.ingredients)
        recept = view?.findViewById(R.id.recept)
        //dishName = view?.findViewById(R.id.inside_exercise_name)!!
        youTubePlayerView!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(dish.youtubeVideo, 0f)
                youTubePlayer.play()
            }
        })
        ingredients?.text = "${ingredients?.text.toString()} ${refactorStringForIngredients(dish.ingredients)}"
        recept?.text = "${recept?.text.toString()} ${refactorStringForRecept(dish.recept)}"
        //exerciseName.text = exercise.name

    }
    private fun refactorStringForIngredients(ingredients: String): String {
        val refactored: StringBuilder = StringBuilder()
        val strArray = ingredients.split(',')
        for(i in strArray) {
            i.removeSuffix(".")
            refactored.append("\n• $i")
        }
        return refactored.toString()
    }
    private fun refactorStringForRecept(recept: String): String {
        val refactored: StringBuilder = StringBuilder()
        var i = 0
        val strArray = recept.split('.')
        while (i < strArray.size - 1) {
            refactored.append("\n${i+1}. ${strArray[i]}")
            i++
        }
        return refactored.toString()
    }
}