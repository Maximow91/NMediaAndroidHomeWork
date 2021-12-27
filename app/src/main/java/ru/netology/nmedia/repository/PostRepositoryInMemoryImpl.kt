package ru.netology.nmedia.repository

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepository
import ru.netology.nmedia.R

class PostRepositoryInMemoryImpl: PostRepository {



    private var post =
        Post(2,
            "Нетология. Университет интернет-профессий будущего",
            "Привет,это новая Нетология!Когда-то Нетология начиналась с инетнсивов по онлайн-маркетингу." +
                    "Затем появились курсы по дизайну, разраблотке,аналитике и управлению." +
                    "Мы растем сами и помогаем рости студентам: от новичков до уверенных профессионалов." +
                    "Но самое важное остается с нами: мы верим,которая застовляет хотеть больше,целится выше,бежать быстрее." +
                    " Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru/",
            "21 Декабря в 18:45",1,0,false)



    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = if (post.likedByMe)  post.copy(likedByMe = !post.likedByMe, likesValue = post.likesValue - 1)
        else
            post.copy(likedByMe = !post.likedByMe, likesValue = post.likesValue + 1)
        data.value = post
    }

    override fun share() {
        post = post.copy(sharedValue = post.sharedValue + 1)
        data.value = post
    }
}