package com.example.ps_2017_174_restapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ps_2017_174_restapi.FirstFragmentDirections
import com.example.ps_2017_174_restapi.Models.Comments
import com.example.ps_2017_174_restapi.Models.Posts
import com.example.ps_2017_174_restapi.R

class PostListAdapter(private val dataSet: List<Posts>) :
        RecyclerView.Adapter<PostListAdapter.ViewHolder>() {


        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title_text: TextView = view.findViewById(R.id.textview_title)
            val userid_text: TextView = view.findViewById(R.id.textview_user_id)


        }


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_list_element, viewGroup, false)

            return ViewHolder(view)
        }


        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            viewHolder.title_text.text = dataSet[position].title
            viewHolder.userid_text.text = dataSet[position].id.toString()




            viewHolder.itemView.setOnClickListener{
                it.findNavController().navigate(
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                        id=viewHolder.userid_text.text.toString().toInt()

                    ))
            }


        }


        override fun getItemCount() = dataSet.size

    }
