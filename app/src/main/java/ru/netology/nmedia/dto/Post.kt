package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val video: String?,
    val likes: Int,
    val likedByMe: Boolean = false,
    val share: Int,
    val visability: Int
)