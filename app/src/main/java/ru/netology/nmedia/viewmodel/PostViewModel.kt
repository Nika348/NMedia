package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.InMemoryRostRepository
import ru.netology.nmedia.repository.PostRepository

class PostViewModel : ViewModel() {
    private val repository : PostRepository = InMemoryRostRepository()
    val data = repository.get()
    fun like(){
        repository.like()
    }
}