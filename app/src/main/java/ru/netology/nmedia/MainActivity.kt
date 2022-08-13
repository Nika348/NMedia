package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        binding.group.visibility = View.GONE
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                binding.group.visibility = View.VISIBLE
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
        })

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            binding.content.setText(post.content)
            binding.content.requestFocus()
        }

        binding.list.adapter = adapter

        binding.save.setOnClickListener {
            if (binding.content.text.isNullOrBlank()) {
                Toast.makeText(it.context, getString(R.string.empty_post_error), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val text = binding.content.text.toString()

            viewModel.editContent(text)
            viewModel.save()

            binding.content.clearFocus()
            AndroidUtils.hidekeyboard(binding.content)
            binding.content.setText("")
            binding.group.visibility = View.GONE
        }

        binding.cancel.setOnClickListener {
            with(binding.content) {
                setText("")
                clearFocus()
                AndroidUtils.hidekeyboard(this)
                binding.group.visibility = View.GONE
            }
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}