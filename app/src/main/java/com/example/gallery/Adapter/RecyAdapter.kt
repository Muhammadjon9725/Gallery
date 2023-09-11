package com.example.gallery.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.Mytype
import com.example.gallery.databinding.MyViewPagerItemBinding

class RecyAdapter(var list:ArrayList<Mytype> = ArrayList()):
    RecyclerView.Adapter<RecyAdapter.VH>() {
    inner class VH(val myViewPagerItemBinding: MyViewPagerItemBinding):RecyclerView.ViewHolder(myViewPagerItemBinding.root){
        fun onBind(mytype: Mytype){
            myViewPagerItemBinding.itemName.text = mytype.name
            myViewPagerItemBinding.itemImage.setImageURI(Uri.parse(mytype.imageUri))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MyViewPagerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }
}