package ru.netology.nmedia

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData

interface PostRepository {
    fun get():LiveData<List<Post>>
    fun like(id:Long)
    fun share(id:Long)
    fun removeById(id: Long)
    fun save(post: Post)
}