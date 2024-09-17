package com.nasho.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.ujian.ujianDiscussion.DataItem
import com.nasho.R
import com.nasho.databinding.PembahasanUjianViewBinding

class PembahasanUjianAdapter: RecyclerView.Adapter<PembahasanUjianAdapter.ViewHolder>() {

    private val data: MutableList<DataItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PembahasanUjianViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<DataItem>) {
        data.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: PembahasanUjianViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataItem, position: Int) {
            binding.apply {
                val context = itemView.context
                val areAnswersDifferent = item.jawabanDetails.any { it.jawabanBenar != it.jawabanUser }

                tvNomorPertanyaanUjian.text = "No.${position + 1} ${item.soal}"
                tvSoalUjianUjian.text = item.pembahasan
                tvJawabanBenarUjian.text = item.jawabanDetails.joinToString(", ") { it.jawabanBenar }
                cvJawabanUjian.setCardBackgroundColor(context.getColor(if (areAnswersDifferent) R.color.Error400 else R.color.Success400))
                cvJempolUjian.setCardBackgroundColor(context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success700))
                ivMaybeUjian.setImageResource(if (areAnswersDifferent) R.drawable.ic_salah else R.drawable.ic_benar)
                tvKesalahanUjian.text = if (areAnswersDifferent) "Belum Benar" else "Benar"
                tvPujianUjian.apply {
                    text = if (areAnswersDifferent) "Masih belum tepat nih" else "Kamu hebat!"
                    setTextColor(context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success50))
                }
                tvBPujianUjian.text = if (areAnswersDifferent) "Tetap berusaha dan tetap semangat" else "Terus pertahankan ya"
                val textColor = context.getColor(if (areAnswersDifferent) R.color.Error50 else R.color.Success50)
                tvJawabanKamuUjian.setTextColor(textColor)
                tvKesalahanUjian.setTextColor(textColor)
            }
        }
    }

}