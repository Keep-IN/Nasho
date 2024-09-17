package com.nasho.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.ujian.test.PilihanItem
import com.nasho.data.adapter.AnswerListAdapter.ViewHolder
import com.nasho.databinding.AnswerListUjianViewBinding

class AnswerListUjianAdapter: RecyclerView.Adapter<AnswerListUjianAdapter.ViewHolder>() {
    private var itemListener: ((PilihanItem) -> Unit)? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION
    private val data: MutableList<PilihanItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnswerListUjianViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener, position == selectedPos)
    }
    override fun getItemCount(): Int = data.size


    fun submitList(list: List<PilihanItem>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }
    inner class ViewHolder(private val binding: AnswerListUjianViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: PilihanItem, listener: ((PilihanItem) -> Unit)?, isSelected: Boolean){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            binding.tvOpsiItemUjian.text = item.jawaban
            if(isSelected){
                binding.answerContainerUjian.apply {
                    setCardBackgroundColor(Color.parseColor("#E8F4F3"))
                    strokeWidth = 5
                    strokeColor = Color.parseColor("#55C2BE")
                }
            } else {
                binding.answerContainerUjian.apply {
                    setCardBackgroundColor(Color.parseColor("#F1F5F9"))
                    strokeWidth = 0
                }
            }
        }
    }
    fun setOnclickItem(listener: ((PilihanItem) -> Unit)?){
        this.itemListener = listener
    }

    fun setSelectedPosition(position: Int) {
        val previousSelected = selectedPos
        selectedPos = position
        notifyItemChanged(previousSelected)
        notifyItemChanged(selectedPos)
    }

    fun resetSelectedPosition() {
        val previousPosition = selectedPos
        selectedPos = RecyclerView.NO_POSITION
        notifyItemChanged(previousPosition)
    }

}