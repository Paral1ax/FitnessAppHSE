package com.mir.fitnessapplication.main.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R


class FitnessFragment : Fragment() {

  private lateinit var fitnessViewModel: FitnessViewModel

  var listFitnessItem: RecyclerView? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    fitnessViewModel =
            ViewModelProvider(this).get(FitnessViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_fitness, container, false)

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    loadRecyclerView()
  }

  private fun loadRecyclerView() {
    var listItem = mutableListOf<FitnessItemStorage>(FitnessItemStorage(R.drawable.ruka, "Накачать базуки", "give fisting my ass"),
      FitnessItemStorage(R.drawable.spins, "Накачать спину", "Хочешь чтобы про тебя говорили что за рама два Вандама?"),
      FitnessItemStorage(R.drawable.pres, "Накачать пресс", "Fucking slaves"),
      FitnessItemStorage(R.drawable.nogii, "Накачать ноги", "Торпеды как у Роналдо ждут тебя"),      )

    listFitnessItem = view?.findViewById(R.id.ViewFitnessItems)
    listFitnessItem?.layoutManager = LinearLayoutManager(context)
    val adapter = AdapterFitness()
    listFitnessItem?.adapter = adapter
    adapter?.setData(listItem)
  }
}