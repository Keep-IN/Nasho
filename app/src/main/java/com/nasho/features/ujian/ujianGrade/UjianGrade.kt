package com.nasho.features.ujian.ujianGrade

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.reqres.materi.Materi
import com.nasho.R
import com.nasho.databinding.ActivityUjianGradeBinding
import com.nasho.features.home_materi.MateriContainer
import com.nasho.features.ujian.UjianViewModel
import com.nasho.features.ujian.ujianDiscussion.PembahasanUjian
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UjianGrade : AppCompatActivity() {

    private lateinit var binding: ActivityUjianGradeBinding
    private val viewModel: UjianViewModel by viewModels()
    private lateinit var idUjian: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUjianGradeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        idUjian = intent.getStringExtra("idUjian").toString()
        binding.root.applySystemWindowInsets()
        getUjianGrade(idUjian)

        binding.btnPembahasanUjian.setOnClickListener {
            startActivity(Intent(this, PembahasanUjian::class.java).apply {
                putExtra("idMengambilUjian", idUjian)
            })
        }
        binding.btnkmUjian.setOnClickListener {
            val intent = Intent(this@UjianGrade, MateriContainer::class.java)
            startActivity(intent)
        }
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getUjianGrade(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getUjianGrade(id).observe(this@UjianGrade){
                when(it){
                    is Result.Success -> {
                        binding.apply {
                            tvJudulQuizUjian.text = it.data.data.ujian[0].namaUjian
                            tvQuizGradeUjian.text = it.data.data.ujian[0].nilai.toString()
                            tvJumlahBenarUjian.text = "${it.data.data.ujian[0].jumlahBenar.toString()} Soal"
                            tvJumlahSalahUjian.text = "${it.data.data.ujian[0].jumlahSalah.toString()} Soal"
                            if (it.data.data.ujian[0].lulus){
                                binding.apply {
                                    cvQuizGradeUjian.setCardBackgroundColor(Color.parseColor("#90BEC7"))
                                    tvKalimatPenyemangatUjian.text = "Kamu Hebat!!!"
                                    tvKalimatPenyemangat2Ujian.text = "Tetap Pertahankan\nSemangatmu Ya"
                                    tvQuizGradeUjian.setTextColor(Color.parseColor("#015869"))
                                    ivQuizSuccessUjian.setImageResource(R.drawable.ic_quiz_success)
                                }
                            } else {
                                binding.apply {
                                    cvQuizGradeUjian.setCardBackgroundColor(Color.parseColor("#CE9589"))
                                    tvKalimatPenyemangatUjian.text = "Tetap Semangat!!!"
                                    tvKalimatPenyemangat2Ujian.text = "Terus Semangat\nKamu Pasti Bisa"
                                    tvQuizGradeUjian.setTextColor(Color.parseColor("#B40101"))
                                    ivQuizSuccessUjian.setImageResource(R.drawable.ic_quiz_fail)
                                }
                            }
                        }
                    }
                    is Result.Error -> {


                    }
                    else -> {

                    }
                }
            }
        }
    }
}