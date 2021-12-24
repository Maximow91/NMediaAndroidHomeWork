package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.appViewModels.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.repository.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this){post ->

            with(binding){
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesValue.text = forPrintPostFields(post.likesValue)
                sharedValue.text = forPrintPostFields(post.sharedValue)

                if(post.likedByMe){
                    likeButton.setImageResource(R.drawable.ic_favorite_24)
                }
        }
            binding.likeButton.setOnClickListener {
                viewModel.likeOrShare(binding.likesValue,binding.likeButton)
            }
            binding.shareButton.setOnClickListener{
                viewModel.likeOrShare(binding.sharedValue,binding.shareButton)
            }
        }
    }
   }