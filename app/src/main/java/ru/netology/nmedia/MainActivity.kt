package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.AdapterCallback
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.appViewModels.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.repository.*
import ru.netology.nmedia.util.AndroidUtils

fun forPrintPostFields(value:Int ):String {
    var stringForPrint = ""
    when(value) {
        0 -> stringForPrint
        in  1.. 1099 -> stringForPrint="$value"
        in 1100 .. 9999 -> stringForPrint="${"%.1f".format(value.toDouble()/1000)}K"
        in 10000 .. 99999 -> stringForPrint="${value/1000}K"
        !in 0 .. 1000000 -> stringForPrint="${"%.1f".format(value.toDouble()/1000000)}M"
    }
    return stringForPrint
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : AdapterCallback {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.remove(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                    binding.content.requestFocus()
                }
            }

            viewModel.edited.observe(this) {
                if (it.id != 0L) {
                    binding.content.setText(it.content)
                    binding.group.visibility = View.VISIBLE
                }
                binding.close.setOnClickListener {
                    binding.group.visibility = View.GONE
                    binding.content.setText("")
                    AndroidUtils.hideKeyboard(it)
                }
            }

            binding.save.setOnClickListener {
                with(binding.content) {
                    var content = text.toString()
                    if (content.isBlank()) {
                        Toast.makeText(it.context, R.string.error_empty_content, Toast.LENGTH_LONG)
                            .show()
                        return@setOnClickListener
                    }

                    viewModel.changeContent(content)
                    viewModel.save()
                    binding.group.visibility = View.GONE

                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(it)
                }
            }
        }
    }
}





