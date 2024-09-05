package com.nasho.features.settings

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.R
import com.nasho.databinding.ActivityTentangKamiBinding

class TentangKami : AppCompatActivity() {
    private lateinit var binding: ActivityTentangKamiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTentangKamiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text1 = "Nasho Learn adalah sebuah aplikasi yang dapat membantu pengguna untuk memahami secara lebih mendalam mengenai tata bahasa dalam bahasa Arab."
        val text2 = "Di Nasho Learn, kami percaya bahwa memahami dasar-dasar tata bahasa Arab adalah hal yang penting bagi siapa saja yang ingin benar-benar menguasai bahasa ini. Visi kami adalah menyediakan platform yang komprehensif, interaktif, dan ramah pengguna di mana para pengguna dapat mendalami studi nahwu (sintaksis) dan sharaf (morfologi)."

        binding.textView32.text = applyColorSpans(text1, "Nasho" to "#015869", "Learn" to "#E57D1A")
        binding.textView34.text = applyColorSpans(text2, "Nasho" to "#015869", "Learn" to "#E57D1A")
    }
    private fun applyColorSpans(text: String, vararg colorPairs: Pair<String, String>): SpannableString {
        val spannableString = SpannableString(text)
        colorPairs.forEach { (word, color) ->
            val start = text.indexOf(word)
            val end = start + word.length
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor(color)), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }
}