package com.example.task8

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val number_list : MutableList<String> = ArrayList()

    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

    var page = 1
    var isLoading = false
    val limit = 10

    lateinit var adapter: numberAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        getPage()
    }

    fun getPage() {

        val start = (page - 1) * limit
        val end = page * limit

        for (i in start..end) {
            number_list.add("Item " + i.toString())
        }
        Handler().postDelayed({
                              if (::adapter.isInitialized) {
                                  adapter.notifyDataSetChanged()
                              } else {
                                  adapter = numberAdapter((this))
                                  recyclerView.adapter = adapter
                              }
        }, 5000)
    }

    class numberAdapter(val activity: MainActivity): RecyclerView.Adapter<numberAdapter.numberViewHolder>() {

        @SuppressLint("ResourceType")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): numberViewHolder {

            return numberViewHolder(LayoutInflater.from(activity).inflate(R.id.tv_number,parent,false))
        }

        override fun onBindViewHolder(holder: numberViewHolder, position: Int) {
            holder.tvnumber.text = activity.number_list[position]
        }

        override fun getItemCount(): Int {
            return 0
        }

        class numberViewHolder(v: View): RecyclerView.ViewHolder(v) {
            val tvnumber = v.findViewById<TextView>(R.id.tv_number)
        }
    }
}