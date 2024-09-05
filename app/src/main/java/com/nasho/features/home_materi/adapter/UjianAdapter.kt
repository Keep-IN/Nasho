package com.nasho.features.home_materi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.materi.Materi
import com.core.data.reqres.materi.Ujian
import com.nasho.R
import com.nasho.databinding.UjianListViewBinding

class UjianAdapter : RecyclerView.Adapter<UjianAdapter.ViewHolder>(){
    private var itemListener: ((Ujian) -> Unit)? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION
    val data: MutableList<Ujian> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UjianListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener, position == selectedPos)
    }

//    fun submitListPhase1(list: List<Ujian>) {
//        val initialSize = itemCount
//        data.clear()
//        notifyItemRangeRemoved(0, initialSize)
//        list.forEach { it.phase_ujian = 1}
//        data.addAll(list)
//        notifyItemRangeInserted(0, data.size)
//    }
//
//    fun submitListPhase2(list: List<Ujian>) {
//        val initialSize = itemCount
//        data.clear()
//        notifyItemRangeRemoved(0, initialSize)
//        list.forEach { it.phase_ujian = 2}
//        data.addAll(list)
//        notifyItemRangeInserted(0, data.size)
//    }
    fun submitList(list: List<Ujian>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: UjianListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item : Ujian, listener: ((Ujian) -> Unit)?, isSelected: Boolean ) {
            binding.root.setOnClickListener {
                listener?.invoke(item)
            }

            binding.apply {
                if (item.locked) {
                    // Nonaktifkan OnClickListener jika item terkunci
                    root.isClickable = false
                    root.isEnabled = false
                } else {
                    // Aktifkan OnClickListener jika item tidak terkunci
                    root.isClickable = true
                    root.isEnabled = true
                    root.setOnClickListener {
                        listener?.invoke(item)
                    }
                }

                var progress: Double = 0.0

                if (item.phase_ujian == 1){
                    textView36.text = "Ujian Tengah"
                } else {
                    textView36.text = "Ujian Akhir"
                }
                textView38.text = item.nama
                val nilai = item.riwayat?.firstOrNull()?.nilai?.toString() ?: "N/A"
                textView44.text = nilai

                if (item.riwayat != null && item.riwayat.isNotEmpty()) {
                    progress = 100.0
                    if (item.riwayat[0].lulus) {
                        binding.ivUjian.setImageResource(R.drawable.ic_done)
                        binding.ivSelesai.setImageResource(R.drawable.ic_done)
                    } else {
                        binding.ivUjian.setImageResource(R.drawable.ic_undone)
                        binding.ivSelesai.setImageResource(R.drawable.ic_done)
                    }
                } else {
                    // Handle the case where riwayat is null or empty
                    binding.ivUjian.setImageResource(R.drawable.ic_undone)
                    binding.ivSelesai.setImageResource(R.drawable.ic_undone)
                }

                ProgressBar.setProgressPercentage(progress)

                ivLock.visibility = if (item.locked) View.VISIBLE else View.GONE
            }
        }
    }

    fun setOnClickItem(listener: ((Ujian) -> Unit)?) {
        this.itemListener = listener
    }
}