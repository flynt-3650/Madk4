package com.example.madk4.list

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madk4.R
import com.example.madk4.databinding.FragmentListBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        val data = readDateTimeFromFile()

        listAdapter = ListAdapter(data)

        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun readDateTimeFromFile(): List<String> {
        val photosDir = File(requireContext()
            .getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos")
        val dateFile = File(photosDir, "data.txt")
        val data = mutableListOf<String>()

        try {
            FileInputStream(dateFile).use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    reader.forEachLine { line ->
                        data.add(line)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }
}
