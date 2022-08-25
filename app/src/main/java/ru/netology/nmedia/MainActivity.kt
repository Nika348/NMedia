package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val newPostLauncher = registerForActivityResult(NewPostActivityContract()){ text ->
            text ?: return@registerForActivityResult
            viewModel.editContent(text)
            viewModel.save()
        }

        val newEditPostLauncher = registerForActivityResult(NewEditPostActivityContract()){ text ->
            text ?: return@registerForActivityResult
            viewModel.editContent(text)
            viewModel.save()
        }

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                Intent().apply {
                    action = Intent.ACTION_EDIT
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                viewModel.edit(post)
                newEditPostLauncher.launch(post.content)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onVideo(post: Post) {
                val appIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("vnd.youtube:" + post.video)
                )

                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + post.video)
                )
                val videoIntent =
                    Intent.createChooser(appIntent, getString(R.string.chooser_video_post))
                startActivity(videoIntent)
                }

        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.create.setOnClickListener {
            newPostLauncher.launch()
        }
    }
}