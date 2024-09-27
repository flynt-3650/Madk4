package com.example.madk4.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madk4.R
import com.example.madk4.databinding.FragmentListBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ListAdapter
    private val dataList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        readDataFromFile()

        listAdapter = ListAdapter(dataList)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    private fun readDataFromFile() {
        try {
            val inputStream = requireContext().assets.open("photos/data.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                line?.let { dataList.add(it) }
            }
            bufferedReader.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
