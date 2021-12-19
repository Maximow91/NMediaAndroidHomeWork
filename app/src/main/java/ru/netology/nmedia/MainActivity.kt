package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val post = Post(1,
        "Нетология. Университет интернет-профессий будущего",
        "Привет,это новая Нетология!Когда-то Нетология начиналась с инетнсивов по онлайн-маркетингу." +
                "Затем появились курсы по дизайну, разраблотке,аналитике и управлению." +
                "Мы растем сами и помогаем рости студентам: от новичков до уверенных профессионалов." +
                "Но самое важное остается с нами: мы верим,которая застовляет хотеть больше,целится выше,бежать быстрее." +
                " Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru/",
        "21 Декабря в 18:45")

    private fun ImageView.printValue(textView: TextView, imageView: ImageView) {
        var value:Int = 0
        when(imageView.id){
            R.id.like_button -> if(post.likedByMe){
                post.likedByMe = !post.likedByMe
                imageView.setImageResource(R.drawable.ic_favorite_border_24)
                value = --post.likesValue
                textView.text= forPrintPostFields(value)
            } else {
                post.likedByMe = !post.likedByMe
                imageView.setImageResource(R.drawable.ic_favorite_24)
                value = ++post.likesValue
                textView.text=forPrintPostFields(value)
            }
            R.id.share_button -> {value = ++post.sharedValue
                textView.text = forPrintPostFields(value)}
        }
    }

    private fun forPrintPostFields(value:Int ):String {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesValue.text = forPrintPostFields(post.likesValue)
            sharedValue.text = forPrintPostFields(post.sharedValue)

            if(post.likedByMe){
                likeButton.setImageResource(R.drawable.ic_favorite_24)
            }

            likeButton.setOnClickListener {
                likeButton.printValue(likesValue,likeButton)
            }

            shareButton.setOnClickListener{
                shareButton.printValue(sharedValue,shareButton)
            }

            binding.root.setOnClickListener {
                sharedValue.text = "100"
            }

            avatar.setOnClickListener{
                sharedValue.text = "100"
            }
        }
    }
   }