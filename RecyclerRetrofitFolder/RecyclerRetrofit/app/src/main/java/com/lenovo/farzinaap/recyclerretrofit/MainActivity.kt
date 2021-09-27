package com.lenovo.farzinaap.recyclerretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.lenovo.farzinaap.recyclerretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var recyclerview:RecyclerView
    lateinit var progressbar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.recyclerview)
        progressbar = findViewById(R.id.progressbar)
        val viewmodel = makeApiCall()

        setupBinding(viewmodel)

    }

    fun setupBinding(viewModel : MainActivityViewModel){
        val activityMainBinding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewModel,viewModel)
        activityMainBinding.executePendingBindings()
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    fun makeApiCall():MainActivityViewModel{
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this,Observer<RecyclerList>{
            progressbar.visibility = GONE
            if (it != null){
                //update the adapter
                viewModel.setAdapterData(it.items)
            }else{
                Toast.makeText(this@MainActivity,"Error in fetching data",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall("newyork")
        return viewModel
    }
}

