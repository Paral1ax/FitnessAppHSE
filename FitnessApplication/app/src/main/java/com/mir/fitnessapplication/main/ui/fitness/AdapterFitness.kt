package com.mir.fitnessapplication.main.ui.fitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.exercises.ExerciseFragment

class AdapterFitness() : RecyclerView.Adapter<AdapterFitness.ViewHolder>(
) {

    var listFitness = mutableListOf<FitnessItemStorage>()

    fun setData(listFitness: List<FitnessItemStorage>) {
        this.listFitness.clear()
        this.listFitness.addAll(listFitness)
    }

    override fun getItemCount(): Int {
        return listFitness.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_fitness_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView?.setImageResource(listFitness[position].imageIdDrawable)
        holder.mainText?.text = listFitness[position].mainText
        holder.supportText?.text = listFitness[position].supportText

        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val fragment: ExerciseFragment = ExerciseFragment()
            itemPos = holder.absoluteAdapterPosition
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit()
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var imageView: ImageView? = null
        var mainText: TextView? = null
        var supportText: TextView? = null

        init {
            imageView = itemView?.findViewById(R.id.mainImageItem)
            mainText = itemView?.findViewById(R.id.mainTextItem)
            supportText = itemView?.findViewById(R.id.supportTextItem)
        }
    }

    companion object {
        public var itemPos: Int = 0

        public fun getAdapterPos(): Int {
            return itemPos
        }
    }
}

