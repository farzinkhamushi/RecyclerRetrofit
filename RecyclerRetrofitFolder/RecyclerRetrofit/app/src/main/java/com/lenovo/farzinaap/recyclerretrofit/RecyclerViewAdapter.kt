package com.lenovo.farzinaap.recyclerretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lenovo.farzinaap.recyclerretrofit.databinding.RecyclerviewRowBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items =  ArrayList<RecyclerData>()

    fun setDataList(data:ArrayList<RecyclerData>){
        this.items = data
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
    class MyViewHolder(val binding: RecyclerviewRowBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecyclerData){
            binding.recyclerData = data
            binding.executePendingBindings()
        }
    }

    companion object{
        @BindingAdapter("loadImage")
        fun loadImage(thumbImage: ImageView, url:String){
            Glide.with(thumbImage)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbImage)
        }
    }
}