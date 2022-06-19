package com.example.ps_2017_174_restapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ps_2017_174_restapi.Models.Posts
import com.example.ps_2017_174_restapi.RetrofitAPI.RetrofitAPI
import com.example.ps_2017_174_restapi.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val retrofitAPI = RetrofitAPI.createObject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

         */

        //get the argument value passed by first fragment
        val argumentname= arguments?.getInt("id")

        val Myposts= argumentname?.let { retrofitAPI.getPosts(it) }

        //set data to recycler view
        if (Myposts != null) {
            Myposts.enqueue(object: Callback<Posts> {
                override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                    val data = response.body();
                    if (data != null) {
                        var past_title: String = getString(R.string.title)
                        var past_user_id: String = getString(R.string.user_id)
                        var past_body: String = getString(R.string.body)
                        var past_id: String = getString(R.string.id)
                        binding.textviewDetailsTitle.text = past_title.plus(data.title)
                        binding.textviewDetailsPostid.text = past_user_id.plus(data.userId)
                        binding.textviewDetailsBody.text = past_body.plus(data.body)
                        binding.textviewDetailsId.text = past_id.plus(data.id)
                    }

                }

                override fun onFailure(call: Call<Posts>, t: Throwable) {

                }
            })
        }

        //navigate to comments fragment and send argument value as id
        binding.buttonComment.setOnClickListener {
            if (argumentname != null) {
                it.findNavController().navigate(
                    SecondFragmentDirections.actionSecondFragmentToCommentsFragment(
                        id=argumentname
                    )
                )
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}