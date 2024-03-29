package com.mir.fitnessapplication.main.ui.fitness.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.exercises.inside.InsideExerciseFragment
import de.hdodenhof.circleimageview.CircleImageView

class ExerciseRecyclerAdapter: RecyclerView.Adapter<ExerciseRecyclerAdapter.ViewHolder>() {

    var exercises = mutableListOf<ExerciseItemStorage>()

    fun setData(exercisesList: List<ExerciseItemStorage>) {
        this.exercises.clear()
        this.exercises.addAll(exercisesList)
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var image: CircleImageView? = null
        var category: TextView? = null
        var text: TextView? = null

        init {
            image = itemView?.findViewById(R.id.exercise_pic)
            category = itemView?.findViewById(R.id.exercise_category)
            text = itemView?.findViewById(R.id.exercise_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_exercise_recycler_layout, parent, false)
        return ExerciseRecyclerAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image?.setImageResource(exercises[position].imageIdDrawable)
        holder.category?.text = exercises[position].category
        holder.text?.text = exercises[position].text

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val fragment = InsideExerciseFragment()
            itemPos = holder.absoluteAdapterPosition
            activity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return exercises.count()
    }

    companion object {
        var itemPos: Int = 0

        fun getAdapterPos(): Int {
            return itemPos
        }
    }
}