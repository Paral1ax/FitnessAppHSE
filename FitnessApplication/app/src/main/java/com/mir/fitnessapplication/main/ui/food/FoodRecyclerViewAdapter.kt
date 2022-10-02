package com.mir.fitnessapplication.main.ui.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.FitnessItemStorage

class FoodRecyclerViewAdapter: RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder>() {

    var listFood = mutableListOf<FoodItemStorage>()

    fun setData(listFitness: List<FoodItemStorage>) {
        this.listFood.clear()
        this.listFood.addAll(listFitness)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_food_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView?.setImageResource(listFood[position].imageIdDrawable)
        holder.mainText?.text = listFood[position].mainText
        holder.supportText?.text = listFood[position].supportText
    }

    class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var imageView: ImageView? = null
        var mainText: TextView? = null
        var supportText: TextView? = null

        init {
            imageView = itemView?.findViewById(R.id.eatingImageView)
            mainText = itemView?.findViewById(R.id.eatingTextItem)
            supportText = itemView?.findViewById(R.id.eatingSupportTextItem)
        }
    }
}
