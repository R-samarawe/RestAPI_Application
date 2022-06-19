package com.example.ps_2017_174_restapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ps_2017_174_restapi.Models.Comments
import com.example.ps_2017_174_restapi.R

class CommentListAdapter (private val dataSet: List<Comments>) :
    RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id_text: TextView = view.findViewById(R.id.textview_id)
        val name_text: TextView = view.findViewById(R.id.textview_name)
        val email_text: TextView = view.findViewById(R.id.textview_email)
        val body_text: TextView = view.findViewById(R.id.textview_body)


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_comment_element, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.id_text.text = dataSet[position].id.toString()
        viewHolder.name_text.text = dataSet[position].name
        viewHolder.email_text.text = dataSet[position].email
        viewHolder.body_text.text = dataSet[position].body

    }


    override fun getItemCount() = dataSet.size

}