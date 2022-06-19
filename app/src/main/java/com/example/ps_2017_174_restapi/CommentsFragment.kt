package com.example.ps_2017_174_restapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ps_2017_174_restapi.Adapter.CommentListAdapter
import com.example.ps_2017_174_restapi.Adapter.PostListAdapter
import com.example.ps_2017_174_restapi.Models.Comments
import com.example.ps_2017_174_restapi.RetrofitAPI.RetrofitAPI
import com.example.ps_2017_174_restapi.databinding.FragmentCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null

    private val binding get() = _binding!!

    private val retrofitAPI = RetrofitAPI.createObject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewComments.layoutManager= LinearLayoutManager(view.context)

        val argumentname= arguments?.getInt("id")

        val MyComments= argumentname?.let { retrofitAPI.getComments(it) }

        if (MyComments != null) {
            MyComments.enqueue(object: Callback<List<Comments>> {
                override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                    val data=response.body()
                    binding.recyclerviewComments.adapter= data?.let { CommentListAdapter(it) }
                }

                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {

                }
            })
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}