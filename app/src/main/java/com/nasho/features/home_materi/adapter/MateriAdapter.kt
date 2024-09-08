package com.nasho.features.home_materi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.reqres.materi.Data
import com.core.data.reqres.materi.Materi
import com.core.data.reqres.materi.Ujian
import com.nasho.R
import com.nasho.databinding.MateriListViewBinding

class MateriAdapter : RecyclerView.Adapter<MateriAdapter.ViewHolder>() {
    private var itemListener: ((Materi) -> Unit)? = null
    private var selectedPos: Int = RecyclerView.NO_POSITION
    val data: MutableList<Materi> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MateriListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener, position == selectedPos)
    }

//    fun submitListPhase1(list: List<Materi>) {
//        val initialSize = itemCount
//        data.clear()
//        notifyItemRangeRemoved(0, initialSize)
//        list.forEach { it.phase = 1}
//        data.addAll(list)
//        notifyItemRangeInserted(0, data.size)
//    }

//    fun submitListPhase2(list: List<Materi>) {
//        val initialSize = itemCount
//        data.clear()
//        notifyItemRangeRemoved(0, initialSize)
//        list.forEach { it.phase = 2}
//        data.addAll(list)
//        notifyItemRangeInserted(0, data.size)
//    }

    fun submitList(list: List<Materi>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MateriListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item : Materi, listener: ((Materi) -> Unit)?, isSelected: Boolean ) {
            binding.root.setOnClickListener {
                listener?.invoke(item)
            }

            if (item.locked) {
                // Nonaktifkan OnClickListener jika item terkunci
                binding.root.isClickable = false
                binding.root.isEnabled = false
            } else {
                // Aktifkan OnClickListener jika item tidak terkunci
                binding.root.isClickable = true
                binding.root.isEnabled = true
                binding.root.setOnClickListener {
                    listener?.invoke(item)
                }
            }

            var sudahMengambil: Double = 0.0
            if (item.sudahMengambil) {
                sudahMengambil = 100.0
            }
            binding.ProgressBar1.setProgressPercentage(sudahMengambil)

            var quiz: Double = 0.0

            binding.textView33.text = "Materi ${item.tingkat}"
            binding.textView34.text = item.judul

            if (item.quiz != null && item.quiz.isNotEmpty()) {
                binding.tvSkor.text = item.quiz.getOrNull(itemCount)?.nilai?.toString() ?: "N/A"
                quiz = 100.0
                binding.ProgressBar2.setProgressPercentage(quiz)
            } else {
                binding.tvSkor.text = "0"
            }

            binding.ivBelajar.setImageResource(if (item.sudahMengambil) R.drawable.ic_done else R.drawable.ic_undone)

            val isQuizPassed = item.quiz?.firstOrNull()?.lulus ?: false
            binding.ivQuiz.setImageResource(if (isQuizPassed) R.drawable.ic_done else R.drawable.ic_undone)
            binding.ivLulus.setImageResource(if (isQuizPassed) R.drawable.ic_done else R.drawable.ic_undone)

            binding.ivLock.visibility = if (item.locked) View.VISIBLE else View.GONE
//            if(item.sudahMengambil){
//                binding.ivBelajar.setImageResource(R.drawable.ic_done)
//            } else {
//                binding.ivBelajar.setImageResource(R.drawable.ic_undone)
//            }
//
//            if(item.quiz[0].lulus){
//                binding.ivQuiz.setImageResource(R.drawable.ic_done)
//                binding.ivLulus.setImageResource(R.drawable.ic_done)
//            } else {
//                binding.ivQuiz.setImageResource(R.drawable.ic_done)
//                binding.ivLulus.setImageResource(R.drawable.ic_undone)
//            }
//
//            if(item.locked){
//                binding.ivLock.visibility = View.VISIBLE
//            } else {
//                binding.ivLock.visibility = View.GONE
//            }

        }
    }

    fun setOnClickItem(listener: ((Materi) -> Unit)?) {
        this.itemListener = listener
    }
}