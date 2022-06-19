package com.example.ps_2017_174_restapi.RetrofitAPI

import com.example.ps_2017_174_restapi.Models.Comments
import com.example.ps_2017_174_restapi.Models.Posts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {


    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("posts/{id}")
    fun getPosts(@Path("id")id :Int):Call<Posts>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id")id :Int): Call<List<Comments>>
    //https://jsonplaceholder.typicode.com/comments?postId=7
    //https://jsonplaceholder.typicode.com/posts/7/comments

    companion object{
        var BASE_URL="https://jsonplaceholder.typicode.com/"

        fun createObject():RetrofitAPI{
            var retrofitAPI= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofitAPI.create(RetrofitAPI::class.java)

        }


    }
}