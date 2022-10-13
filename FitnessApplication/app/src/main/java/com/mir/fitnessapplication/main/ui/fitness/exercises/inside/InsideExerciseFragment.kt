package com.mir.fitnessapplication.main.ui.fitness.exercises.inside

import android.os.Bundle
import android.util.Log
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
import org.w3c.dom.Text


class InsideExerciseFragment: Fragment() {

    private var youTubePlayerView: YouTubePlayerView? = null
    private var repeats: TextView? = null
    private var povtory: TextView? = null
    lateinit var exerciseName: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inside_exercise_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showExerciseInfoAndVideo()
    }

    private fun showExerciseInfoAndVideo() {
        val category = AdapterFitness.getAdapterPos()
        val pos = ExerciseRecyclerAdapter.getAdapterPos()

        val exercise: InsideExerciseItemStorage = InsideExerciseData().mutableList[category][pos]
        youTubePlayerView = view?.findViewById(R.id.youtube_player_view)
        repeats = view?.findViewById(R.id.repeats)
        povtory = view?.findViewById(R.id.podhodi)
        exerciseName = view?.findViewById(R.id.inside_exercise_name)!!
        youTubePlayerView!!.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(exercise.youtubeVideo, 0f)
                youTubePlayer.play()
            }
        })
        repeats?.text = "${repeats?.text.toString()} ${exercise.repeats}"
        povtory?.text = "${povtory?.text.toString()} ${exercise.podhodi}"
        exerciseName.text = exercise.name

    }

    override fun onDestroy() {
        youTubePlayerView?.release()
        super.onDestroy()
    }
}