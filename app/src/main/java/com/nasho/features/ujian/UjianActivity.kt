package com.nasho.features.ujian

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerRequest
import com.core.data.reqres.ujian.test.PilihanItem
import com.core.data.reqres.ujian.test.UjianResponse
import com.nasho.R
import com.nasho.data.adapter.AnswerListUjianAdapter
import com.nasho.databinding.ActivityUjianBinding
import com.nasho.features.quiz.alert.AlertDialogAnswerCorrect
import com.nasho.features.quiz.alert.AlertDialogAnswerIncorrect
import com.nasho.features.ujian.ujianGrade.UjianGrade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UjianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUjianBinding
    private val adapterListOpsi: AnswerListUjianAdapter by lazy { AnswerListUjianAdapter() }
    private var dataUjian: UjianResponse? = null
    private var listOpsi: List<PilihanItem> = listOf()
    private val viewModel: UjianViewModel by viewModels()
    private val userAcces: String = ""
    private var index = 0
    private var isItemSelected = false
    private var idPilihan: String = ""
    private var idSoal: String = ""
    private lateinit var idMengambilUjian: String
    private lateinit var idMateri: String
    private lateinit var idUjian: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUjianBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        idMengambilUjian = intent.getStringExtra("idMengambilUjian").toString()
        idMateri = intent.getStringExtra("idMateri").toString()
        idUjian = intent.getStringExtra("idUjian").toString()
        binding.root.applySystemWindowInsets()
        binding.rvListAnswersUjian.adapter = adapterListOpsi
        binding.rvListAnswersUjian.layoutManager = LinearLayoutManager(this)
        adapterListOpsi.setOnclickItem(rvClickListener)
        getUjian(idUjian)
        binding.btnCheckAnswerUjian.setOnClickListener {
            postJawaban(idMengambilUjian, UjianAnswerRequest(idPilihan, idSoal))
            adapterListOpsi.resetSelectedPosition()
            isItemSelected = false
            updateButtonState()
            Handler().postDelayed({
                if (index < dataUjian?.data?.size!! - 1){
                    index++
                    showQuiz(index)
                } else {
                    startActivity(Intent(this, UjianGrade::class.java).apply {
                        putExtra("idUjian", idMengambilUjian)
                    })
                    finish()
                }
            },2000)
        }
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getUjian(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getUjian(id).observe(this@UjianActivity){
                when(it){
                    is Result.Success -> {
                        Log.d("Get Quiz", "Response: ${it.data}")
                        binding.apply {
                            adapterListOpsi.setOnclickItem(rvClickListener)
                            binding.tvTitleSoalUjian.visibility = View.VISIBLE
                            quizShimmerUjian.visibility = View.GONE
                            quizShimmerUjian.stopShimmer()
                        }
                        dataUjian = it.data
                        showQuiz(index)
                    }
                    is Result.Error -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun getDiscussion(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getUjianDiscussion(id).observe(this@UjianActivity){
                when(it){
                    is Result.Success -> {

                    }
                    is Result.Error -> {


                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun postJawaban(id: String, ujianAccessRequest: UjianAnswerRequest){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.postUjianAnswer(id, ujianAccessRequest).observe(this@UjianActivity){
                when(it){
                    is Result.Success -> {
                        when(it.data.data.hasil){
                            true -> AlertDialogAnswerCorrect().show(supportFragmentManager, "Alert Answer true")
                            false -> AlertDialogAnswerIncorrect().show(supportFragmentManager, "Alert Answer false")
                        }
                    }
                    is Result.Error -> {
                        Log.d("Post Answer", "${it.errorMessage}")

                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun showQuiz(index: Int){
        binding.tvQuestPageUjian.text = "Soal ${index + 1}/${dataUjian?.data?.size}"
        dataUjian?.let{
            listOpsi = it.data[index].pilihan
            binding.tvSoalQuizUjian.text = it.data[index].soal
            binding.tvQuizTitlteUjian.text = it.data[index].namaUjian
            adapterListOpsi.submitList(listOpsi)
            idSoal = it.data[index].soalId
        }
    }

    private val rvClickListener: (PilihanItem) -> Unit ={ item ->
        Log.d("CLICKED", "Item Is Clicked")
        dataUjian?.data?.get(index)?.pilihan?.let { adapterListOpsi.setSelectedPosition(it.indexOf(item)) }
        isItemSelected = true
        idPilihan = item.id
        updateButtonState()
    }

    private fun updateButtonState(){
        binding.btnCheckAnswerUjian.isEnabled = isItemSelected
        if (isItemSelected) {
            binding.btnCheckAnswerUjian.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#015869"))
        } else {
            binding.btnCheckAnswerUjian.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#C4C4C4"))
        }

    }
}