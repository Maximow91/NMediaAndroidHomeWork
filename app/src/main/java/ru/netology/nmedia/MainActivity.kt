package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.appViewModels.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.repository.*

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

        val adapter = PostsAdapter( {viewModel.like(it.id)}, {viewModel.share(it.id)} )
        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}






