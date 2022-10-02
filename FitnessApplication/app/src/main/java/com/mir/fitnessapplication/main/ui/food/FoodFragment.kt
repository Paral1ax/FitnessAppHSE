package com.mir.fitnessapplication.main.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R

class FoodFragment : Fragment() {
    private lateinit var foodViewModel: FoodViewModel

    var listFoodItems: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodViewModel =
            ViewModelProvider(this).get(FoodViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_food, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        var listItem = mutableListOf<FoodItemStorage>(
            FoodItemStorage(R.drawable.zavtrak, "Утро", "Начни свой день с правильного завтрака!"),
            FoodItemStorage(R.drawable.obed, "День", "Полезный полуденный прием пищи"),
            FoodItemStorage(R.drawable.uzin, "Вечер", "Заверши свой день на высоте!"))

        listFoodItems = view?.findViewById(R.id.eatingRecyclerview)
        listFoodItems?.layoutManager = LinearLayoutManager(context)
        val adapter = FoodRecyclerViewAdapter()
        listFoodItems?.adapter = adapter
        adapter.setData(listItem)
    }
}