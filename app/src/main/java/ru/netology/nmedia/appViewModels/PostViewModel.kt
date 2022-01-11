package ru.netology.nmedia.appViewModels

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

val empty = Post (id = 0,
    author = "",
    content = "",
    published = "",
    likesValue = 0,
    sharedValue = 0,
    likedByMe = false)

class PostViewModel:ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data: LiveData<List<Post>> = repository.get()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content:String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = edited.value?.copy(content = text)
        }
    }

    fun like(id: Long) = repository.like(id)
    fun share(id:Long) = repository.share(id)
    fun remove(id: Long) = repository.removeById(id)
}