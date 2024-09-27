package com.nasho.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.quiz.Pilihan
import com.nasho.databinding.AnswerListViewBinding

class AnswerListAdapter: RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {
    private var itemListener: ((Pilihan) -> Unit)? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION
    private val data: MutableList<Pilihan> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnswerListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener, position == selectedPos)
    }
    override fun getItemCount(): Int = data.size


    fun submitList(list: List<Pilihan>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }
    inner class ViewHolder(private val binding: AnswerListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Pilihan, listener: ((Pilihan) -> Unit)?, isSelected: Boolean){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            binding.tvOpsiItem.text = item.jawaban
            if(isSelected){
                binding.answerContainer.apply {
                    setCardBackgroundColor(Color.parseColor("#E8F4F3"))
                    strokeWidth = 5
                    strokeColor = Color.parseColor("#55C2BE")
                }
            } else {
                binding.answerContainer.apply {
                    setCardBackgroundColor(Color.parseColor("#F1F5F9"))
                    strokeWidth = 0
                }
            }
        }
    }
    fun setOnclickItem(listener: ((Pilihan) -> Unit)?){
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