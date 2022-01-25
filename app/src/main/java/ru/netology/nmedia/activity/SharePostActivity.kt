package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding
import ru.netology.nmedia.databinding.ActivitySharePostBinding

class SharePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySharePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener {
            val text = binding.content
        }

    }
}