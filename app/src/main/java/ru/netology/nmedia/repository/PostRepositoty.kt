package ru.netology.nmedia

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData

interface PostRepository {
    fun get():LiveData<Post>
    fun like()
    fun share()
}