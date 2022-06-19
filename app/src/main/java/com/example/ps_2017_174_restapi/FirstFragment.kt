package com.example.ps_2017_174_restapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ps_2017_174_restapi.Adapter.PostListAdapter
import com.example.ps_2017_174_restapi.Models.Posts
import com.example.ps_2017_174_restapi.RetrofitAPI.RetrofitAPI
import com.example.ps_2017_174_restapi.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    private val retrofitAPI = RetrofitAPI.createObject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        */

        binding.recyclerviewPosts.layoutManager=LinearLayoutManager(view.context)

        val Myposts=retrofitAPI.getPosts();

        Myposts.enqueue(object: Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                val data_set=response.body();
                //Log.i("*****************",data_set.toString())

                binding.recyclerviewPosts.adapter= data_set?.let { PostListAdapter(it) }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}