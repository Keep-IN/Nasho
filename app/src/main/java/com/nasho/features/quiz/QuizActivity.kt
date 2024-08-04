package com.nasho.features.quiz

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.reqres.quiz.Pilihan
import com.core.data.reqres.quiz.QuizResponse
import com.core.data.reqres.quiz.answerValidationReqRes.QuizAnswerRequest
import com.nasho.data.adapter.AnswerListAdapter
import com.nasho.databinding.ActivityQuizBinding
import com.nasho.features.quiz.alert.AlertDialogAnswerCorrect
import com.nasho.features.quiz.alert.AlertDialogAnswerIncorrect
import com.nasho.features.quiz.quizGrade.QuizGrade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private val adapterListOpsi: AnswerListAdapter by lazy {AnswerListAdapter()}
    private var dataQuiz: QuizResponse? = null
    private var listOpsi: List<Pilihan> = listOf()
    private val viewModel: QuizViewModel by viewModels()
    private val userAcces: String = ""
    private var index = 0
    private var isItemSelected = false
    private var idPilihan: String = ""
    private var idSoal: String = ""
    private var idQuiz: String = "ba6309d9-aa93-49ca-909b-d30fc5d2a06f"
    private var idMateri: String = "d2bf909e-9f05-45bf-a55b-f2b8b6d47e3f"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()
        binding.rvListAnswers.adapter = adapterListOpsi
        binding.rvListAnswers.layoutManager = LinearLayoutManager(this)
        adapterListOpsi.setOnclickItem(rvClickListener)
        getQuiz(idMateri)
        binding.btnCheckAnswer.setOnClickListener {
            postJawaban(idQuiz, QuizAnswerRequest(idPilihan, idSoal))
            adapterListOpsi.resetSelectedPosition()
            isItemSelected = false
            updateButtonState()
            Handler().postDelayed({
                if (index < dataQuiz?.data?.size!! - 1){
                    index++
                    showQuiz(index)
                } else {
                    startActivity(Intent(this, QuizGrade::class.java))
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
    private fun userAccessQuiz(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.postAccessQuiz(id).observe(this@QuizActivity){
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

    private fun getQuiz(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getQuiz(id).observe(this@QuizActivity){
                when(it){
                    is Result.Success -> {
                        Log.d("Get Quiz", "Response: ${it.data}")
                        binding.apply {
                            adapterListOpsi.setOnclickItem(rvClickListener)
                            binding.tvTitleSoal.visibility = View.VISIBLE
                            quizShimmer.visibility = View.GONE
                            quizShimmer.stopShimmer()
                        }
                        dataQuiz = it.data
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
            viewModel.getQuizDiscussion(id).observe(this@QuizActivity){
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

    private fun postJawaban(id: String, quizAccessRequest: QuizAnswerRequest){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.postQuizAnswer(id, quizAccessRequest).observe(this@QuizActivity){
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
        binding.tvQuestPage.text = "Soal ${index + 1}/${dataQuiz?.data?.size}"
        dataQuiz?.let{
            listOpsi = it.data[index].pilihan
            binding.tvSoalQuiz.text = it.data[index].soal
            binding.tvQuizTitlte.text = it.data[index].nama
            adapterListOpsi.submitList(listOpsi)
            idSoal = it.data[index].soalId
        }
    }

    private val rvClickListener: (Pilihan) -> Unit ={item ->
        Log.d("CLICKED", "Item Is Clicked")
        dataQuiz?.data?.get(index)?.pilihan?.let { adapterListOpsi.setSelectedPosition(it.indexOf(item)) }
        isItemSelected = true
        idPilihan = item.id
        updateButtonState()
    }

    private fun updateButtonState(){
        binding.btnCheckAnswer.isEnabled = isItemSelected
        if (isItemSelected) {
            binding.btnCheckAnswer.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#015869"))
        } else {
            binding.btnCheckAnswer.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#C4C4C4"))
        }

    }
}