package com.example.retrofitprac

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var arrayList: ArrayList<CustomModelElement>
    //lateinit var arrayList2: ArrayList<Map<String,Any>>
    lateinit var customAdapter: CustomAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var listener: clickListener
    lateinit var progressBar: ProgressBar
    lateinit var tvError: TextView
    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickLambda: (Int) -> Unit={
            Toast.makeText(applicationContext, "clicked item index is $it", Toast.LENGTH_LONG).show()
        }

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progBar)
        tvError = findViewById(R.id.tvError)
        refreshLayout = findViewById(R.id.refreshLayout)

        progressBar.visibility=View.VISIBLE
        refreshLayout.setOnRefreshListener{
            Toast.makeText(applicationContext, "Refreshing...", Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing=false
        }


        listener= object : clickListener() {
            override fun click(index: Int) {
                    Toast.makeText(applicationContext, "clicked item index is $index", Toast.LENGTH_LONG).show()
            }
        }

        //val img: ImageView = findViewById(R.id.img)
        arrayList=ArrayList()
        //val call: Call<Movies> = RetroInstance.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0")


            //val call: Call<CustomModelElement> = RetroInstance.api.getData()
            val call: Call<List<CustomModelElement>> = RetroInstance.instance?.getMyApi()!!.getData()
            println("check here ${call.request()}");
            println("check here ${call}");
            //Log.d("jjj","line 45 i = " + i)
            //call.enqueue(object : Callback<Movies> {
            call.enqueue(object : Callback<List<CustomModelElement>> {
                //override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                override fun onResponse(
                    call: Call<List<CustomModelElement>>,
                    response: Response<List<CustomModelElement>>
                ) {
                    println("response: ${response.body()}")

                      //Log.d("jjj","line 50 i = " + i)
                      println("check here")
                      println("response: ${response.body()}")
                      if (response.body() != null) {
                          Log.d("#####", "check here")
                          Log.d("#####", "response: ${response.body()}")

                          //tv.text = response.body()!!.results[5].title
  //                    tv.text = "id = " + response.body()!!.id
  //                    tv2.text = "title = " + response.body()!!.title
  //                    tv3.text = "userId = " + response.body()!!.userId

  //                    tv4.text = "Complete Status = " + response.body()!!.completed

                          arrayList = response.body() as ArrayList<CustomModelElement>
                          Log.d("#####", "line 66")
  //                    Glide.with(this@MainActivity)
  //                         .load("https://image.tmdb.org/t/p/w500" + response.body()!!.results[5].poster_path)
  //                         .into(img)


                          //if (i == 10) {
                           //   Log.d("#####", "line 87 arrayList size = ${arrayList.size} & i = $i")
                              customAdapter = CustomAdapter(applicationContext, arrayList, clickLambda)
                              recyclerView.adapter = customAdapter
                              recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                     //     }
                     // } else {
                          Log.d("#####", "line 72")
                          progressBar.visibility=View.INVISIBLE
                          return
                      }

                }

                //override fun onFailure(call: Call<Movies>, t: Throwable) {
                override fun onFailure(call: Call<List<CustomModelElement>>, t: Throwable) {

                    //Log.d("jjj", t.message.toString())
                    progressBar.visibility=View.GONE
                    tvError.visibility=View.VISIBLE

                    Log.d("error response", t.message.toString())
                }
            })
    }
}