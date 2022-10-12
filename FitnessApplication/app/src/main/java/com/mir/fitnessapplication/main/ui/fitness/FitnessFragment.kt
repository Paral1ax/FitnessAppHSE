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
    var listItem =
      mutableListOf<FitnessItemStorage>(
        FitnessItemStorage(R.drawable.ruka, "Накачать руки", "Лучшие упражнения на руки от экспертов"),
      FitnessItemStorage(R.drawable.spins, "Накачать спину", "Более 10 упражнений для прокачки спины в зале и дома"),
      FitnessItemStorage(R.drawable.pres, "Накачать пресс", "Топовые упражнения на пресс со своим весом"),
      FitnessItemStorage(R.drawable.nogii, "Накачать ноги", "Всевозможные упражнения на ноги для прокачки мышц, выносливости и растяжки"),      )

    listFitnessItem = view?.findViewById(R.id.ViewFitnessItems)
    listFitnessItem?.layoutManager = LinearLayoutManager(context)
    val adapter = AdapterFitness()
    listFitnessItem?.adapter = adapter
    adapter?.setData(listItem)
  }
}