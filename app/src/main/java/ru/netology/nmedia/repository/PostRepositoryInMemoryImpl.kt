package ru.netology.nmedia.repository

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostRepository
import ru.netology.nmedia.R

fun forPrintPostFields(value:Int ):String {
    var stringForPrint = ""
    when(value) {
        0 -> stringForPrint
        in 1 .. 1099 -> stringForPrint="$value"
        in 1100 .. 9999 -> stringForPrint="${"%.1f".format(value.toDouble()/1000)}K"
        in 10000 .. 99999 -> stringForPrint="${value/1000}K"
        !in 0 .. 1000000 -> stringForPrint="${"%.1f".format(value.toDouble()/1000000)}M"
    }
    return stringForPrint
}

class PostRepositoryInMemoryImpl: PostRepository {



    private var post = Post(1,
        "Нетология. Университет интернет-профессий будущего",
        "Привет,это новая Нетология!Когда-то Нетология начиналась с инетнсивов по онлайн-маркетингу." +
                "Затем появились курсы по дизайну, разраблотке,аналитике и управлению." +
                "Мы растем сами и помогаем рости студентам: от новичков до уверенных профессионалов." +
                "Но самое важное остается с нами: мы верим,которая застовляет хотеть больше,целится выше,бежать быстрее." +
                " Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru/",
        "21 Декабря в 18:45",0,0,false)

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun likeOrShare(textView: TextView, imageView: ImageView) {
        imageView.printValue(textView,imageView)
    }

    private fun ImageView.printValue(textView: TextView, imageView: ImageView) {
        var value:Int = 0
        when(imageView.id){
            R.id.like_button -> if(post.likedByMe){
                post = post.copy(likedByMe = !post.likedByMe, likesValue = post.likesValue -1)
                imageView.setImageResource(R.drawable.ic_favorite_border_24)
                value = post.likesValue
                textView.text= forPrintPostFields(value)
                data.value = post
            } else {
                post = post.copy(likedByMe = !post.likedByMe, likesValue = post.likesValue+1)
                data.value = post
                imageView.setImageResource(R.drawable.ic_favorite_24)
                value = post.likesValue
                textView.text=forPrintPostFields(value)
            }
            R.id.share_button -> {
                 post = post.copy(sharedValue = post.sharedValue + 1)
                data.value = post
                value = post.sharedValue
                textView.text = forPrintPostFields(value)}
        }
    }
}