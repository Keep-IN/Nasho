package com.nasho.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.quiz.quizDiscussion.DataJawaban
import com.nasho.R
import com.nasho.databinding.PembahasanViewBinding

class PembahasanAdapter: RecyclerView.Adapter<PembahasanAdapter.ViewHolder>() {

    private val data: MutableList<DataJawaban> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PembahasanViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<DataJawaban>) {
        data.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PembahasanViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataJawaban, position: Int) {
            binding.apply {
                val context = itemView.context
                val areAnswersDifferent = item.jawabanDetails.any { it.jawabanBenar != it.jawabanUser }

                tvNomorPertanyaan.text = "No.${position + 1} ${item.soal}"
                tvSoalUjian.text = item.pembahasan
                tvJawabanBenar.text = item.jawabanDetails.joinToString(", ") { it.jawabanBenar }
                cvJawaban.setCardBackgroundColor(context.getColor(if (areAnswersDifferent) R.color.Error400 else R.color.Success400))
                cvJempol.setCardBackgroundColor(context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success700))
                ivMaybe.setImageResource(if (areAnswersDifferent) R.drawable.ic_salah else R.drawable.ic_benar)
                tvKesalahan.text = if (areAnswersDifferent) "Belum Benar" else "Benar"
                tvPujian.apply {
                    text = if (areAnswersDifferent) "Masih belum tepat nih" else "Kamu hebat!"
                    setTextColor(context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success50))
                }
                tvBPujian.text = if (areAnswersDifferent) "Tetap berusaha dan tetap semangat" else "Terus pertahankan ya"
                val textColor = context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success50)
                tvJawabanKamu.setTextColor(textColor)
                tvKesalahan.setTextColor(textColor)
            }
        }
    }
}
