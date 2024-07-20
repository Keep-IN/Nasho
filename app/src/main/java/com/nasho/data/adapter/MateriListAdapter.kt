package com.nasho.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.materi.Materi
import com.nasho.databinding.MateriListViewBinding

class MateriListAdapter: RecyclerView.Adapter<MateriListAdapter.ViewHolder>() {
    private var itemListener: ((Materi) -> Unit)? = null
    private val data: MutableList<Materi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriListAdapter.ViewHolder {
        return ViewHolder(
            MateriListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MateriListAdapter.ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    inner class ViewHolder(private val binding: MateriListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Materi, listener: ((Materi) -> Unit)?){
        }
    }
}