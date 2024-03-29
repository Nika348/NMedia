package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.InMemoryPostRepository
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySharedPrefsImpl

val empty = Post(
    0,
    "",
    "",
    "",
    null,
    0,
    false,
    0,
    0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : PostRepository = PostRepositorySharedPrefsImpl(application)
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun edit(post: Post) {
        edited.value = post
    }
    fun likeById(id: Long){
        repository.likeById(id)
    }
    fun share(id: Long){
        repository.share(id)
    }
    fun removeById(id: Long){
        repository.removeById(id)
    }

    fun save(){
        edited.value?.let{
            repository.save(it)
        }
        edited.value = empty
    }
    fun editContent(content: String){
        edited.value?.let {
            val trimmed = content.trim()
            if (trimmed == it.content){
                return
            }
            edited.value = it.copy(content = trimmed)
        }
    }
}