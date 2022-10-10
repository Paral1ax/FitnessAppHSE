package com.mir.fitnessapplication.main.ui.fitness.exercises.inside

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.AdapterFitness
import com.mir.fitnessapplication.main.ui.fitness.exercises.ExerciseRecyclerAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class InsideExerciseFragment: Fragment() {

    var youTubePlayerView: YouTubePlayerView? = null
    var repeats: TextView? = null
    var povtory: TextView? = null

    init {
        youTubePlayerView = view?.findViewById(R.id.youtube_player_view)
        repeats = view?.findViewById(R.id.repeats)
        povtory = view?.findViewById(R.id.podhodi)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inside_exercise_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showExerciseInfoAndVideo() {
        val category = AdapterFitness.getAdapterPos()
        val pos = ExerciseRecyclerAdapter.getAdapterPos()

        val exercise: InsideExerciseItemStorage = InsideExerciseData().mutableList[category][pos]
        youTubePlayerView!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(exercise.youtubeVideo, 0f)
            }
        })

    }
}