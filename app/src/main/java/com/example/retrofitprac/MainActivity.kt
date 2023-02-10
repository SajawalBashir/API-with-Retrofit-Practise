package com.example.retrofitprac

import android.content.Intent
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
import com.example.retrofitprac.dataClass.CustomModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //lateinit var arrayList: ArrayList<CustomModel>
    //lateinit var arrayList2: ArrayList<Map<String,Any>>
    lateinit var obj: CustomModel
    lateinit var customAdapter: CustomAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var innerRecyclerView: RecyclerView
    lateinit var listener: clickListener
    lateinit var progressBar: ProgressBar
    lateinit var tvError: TextView
    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        innerRecyclerView = findViewById(R.id.innerRecyclerView)
        progressBar = findViewById(R.id.progBar)
        tvError = findViewById(R.id.tvError)
        refreshLayout = findViewById(R.id.refreshLayout)

        val clickLambda: (Int,CustomModel) -> Unit = { it: Int, obj: CustomModel ->
            Toast.makeText(applicationContext, "MainActivity.kt clicked item index is $it and ${obj.carts.size}", Toast.LENGTH_LONG)
                .show()

//            recyclerView.visibility = View.INVISIBLE
//            innerRecyclerView.visibility = View.VISIBLE
//            val innerCustomAdapter = innerCustomAdapter(
//                applicationContext,
//                obj,
//                it
//            )
//            innerRecyclerView.adapter = innerCustomAdapter
//            innerRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
//            val bundle = Bundle()
//            bundle.putSerializable("obj",obj)
            Log.d("#####", "MainActivity.kt Line 57")
            val intent = Intent(this,MainAcitivity2::class.java)
            Log.d("#####", "MainActivity.kt Line 59")
            intent.putExtra("index",it)
            Log.d("#####", "MainActivity.kt Line 61")
            intent.putExtra("obj",obj as java.io.Serializable)
            Log.d("#####", "MainActivity.kt Line 63")
            startActivity(intent)
            Log.d("#####", "MainActivity.kt Line 65")
            Log.d("#####", "MainActivity.kt Line 67")
        }

        progressBar.visibility = View.VISIBLE
        refreshLayout.setOnRefreshListener {
            Toast.makeText(applicationContext, "Refreshing...", Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing = false
        }


//        listener = object : clickListener() {
//            override fun click(index: Int, obj: CustomModel) {
//                Toast.makeText(
//                    applicationContext,
//                    "clicked item index is $index",
//                    Toast.LENGTH_LONG
//                ).show()
//
////                recyclerView.visibility = View.INVISIBLE
////                innerRecyclerView.visibility = View.VISIBLE
////                val innerCustomAdapter = innerCustomAdapter(
////                    applicationContext,
////                    obj,
////                    index
////                )
////                innerRecyclerView.adapter = innerCustomAdapter
////                innerRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
//
//
//                Log.d("#####", "MainActivity.kt Line 87")
//            }
//        }

        //val img: ImageView = findViewById(R.id.img)
        //arrayList = ArrayList()
        //val call: Call<Movies> = RetroInstance.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0")


        //val call: Call<CustomModelElement> = RetroInstance.api.getData()
        //val call: Call<List<CustomModel>> = RetroInstance.instance?.getMyApi()!!.getData()
        val call: Call<CustomModel> = RetroInstance.instance?.getMyApi()!!.getData()
        println("MainActivity.kt check here ${call.request()}");
        println("MainActivity.kt check here ${call}");
        //Log.d("jjj","line 45 i = " + i)
        //call.enqueue(object : Callback<Movies> {
        try {
            call.enqueue(object : Callback<CustomModel> {
                //override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                override fun onResponse(call: Call<CustomModel>, response: Response<CustomModel>) {
                    println("response: ${response}")

                    //Log.d("jjj","line 50 i = " + i)
//                      println("check here")
//                      println("response: ${response.body()}")
                    if (response.body() != null) {
                        Log.d("#####", "MainActivity.kt check here")
                        Log.d("#####", "MainActivity.kt response: ${response.body()}")

                        //tv.text = response.body()!!.results[5].title
                        //                    tv.text = "id = " + response.body()!!.id
                        //                    tv2.text = "title = " + response.body()!!.title
                        //                    tv3.text = "userId = " + response.body()!!.userId

                        //                    tv4.text = "Complete Status = " + response.body()!!.completed

                        //arrayList = response.body() as ArrayList<CustomModel>
                        obj = response.body()!!
                        Log.d("#####", "MainActivity.kt line 104")
                        //                    Glide.with(this@MainActivity)
                        //                         .load("https://image.tmdb.org/t/p/w500" + response.body()!!.results[5].poster_path)
                        //                         .into(img)


                        //if (i == 10) {
                        //   Log.d("#####", "line 87 arrayList size = ${arrayList.size} & i = $i")
                        customAdapter = CustomAdapter(applicationContext, obj, clickLambda)
                        recyclerView.adapter = customAdapter
                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        //     }
                        // } else {
                        Log.d("#####", "MainActivity.kt line 117")
                        progressBar.visibility = View.INVISIBLE
                        return
                    }

                }

                //override fun onFailure(call: Call<Movies>, t: Throwable) {
                override fun onFailure(call: Call<CustomModel>, t: Throwable) {

                    //Log.d("jjj", t.message.toString())
                    progressBar.visibility = View.GONE
                    tvError.visibility = View.VISIBLE

                    Log.d("error response", t.message.toString())
                }
            })
        } catch (e: java.lang.Exception) {
            println("catch exception here $e")
        }
    }

//    fun runInnerRecycler(
//        obj: CustomModel,
//        clickLambda: (Int) -> Unit,
//        totalInCart: String,
//        discountedTotal: String,
//        userId: String,
//        totalProducts: String,
//        totalQuantity: String,
//        size: Int,
//        position: Int
//    ) {
//        val customAdapter = innerCustomAdapter(
//            applicationContext,
//            obj,
//            clickLambda,
//            totalInCart,
//            discountedTotal,
//            userId,
//            totalProducts,
//            totalQuantity,
//            size,
//            position
//        )
//        innerRecyclerView.adapter = customAdapter
//        innerRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
//    }
}