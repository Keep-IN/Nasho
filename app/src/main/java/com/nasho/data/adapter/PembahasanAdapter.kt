package com.nasho.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.core.data.reqres.quiz.quizDiscussion.DataJawaban
import com.nasho.R
import com.nasho.databinding.PembahasanViewBinding
import com.google.android.material.imageview.ShapeableImageView

class PembahasanAdapter: RecyclerView.Adapter<PembahasanAdapter.ViewHolder>() {
    private var itemListener: ((DataJawaban) -> Unit)? = null
    private val data: MutableList<DataJawaban> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PembahasanViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = data.size

    fun submitList(list: List<DataJawaban>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: PembahasanViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: DataJawaban, listener: ((DataJawaban) -> Unit)?) {
            binding.apply {
                tvSoalUjian.text = item.soal
                tvJawabanBenar.text = item.jawabanDetails.joinToString(", ") { it.jawabanBenar }

                val areAnswersDifferent = item.jawabanDetails.any { it.jawabanBenar != it.jawabanUser }

                cvJawaban.setCardBackgroundColor(
                    if (areAnswersDifferent)
                        itemView.context.getColor(R.color.Error400)
                    else
                        itemView.context.getColor(R.color.Success400)
                )
                ivMaybe.setImageResource(
                    if (areAnswersDifferent)
                        R.drawable.ic_benar
                    else
                        R.drawable.ic_salah
                )
            }
        }
    }
}
