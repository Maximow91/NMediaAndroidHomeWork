package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.forPrintPostFields

typealias CallBack = (Post) -> Unit

class PostsAdapter (private val likeCallback: CallBack,
                    private val shareCallback: CallBack): ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PostViewHolder(binding, likeCallback, shareCallback)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(private val binding: CardPostBinding,
                     private val likeCallback: CallBack,
                     private val shareCallback: CallBack) :RecyclerView.ViewHolder(binding.root){

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesValue.text = forPrintPostFields(post.likesValue)
            sharedValue.text = forPrintPostFields(post.sharedValue)

            if (post.likedByMe) likeButton.setImageResource(R.drawable.ic_favorite_24)
            else likeButton.setImageResource(R.drawable.ic_favorite_border_24)
            likeButton.setOnClickListener {
                likeCallback(post)
            }
            shareButton.setOnClickListener {
                shareCallback(post)
            }
        }
    }
}

class PostDiffCallback: DiffUtil.ItemCallback<Post>(){

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}