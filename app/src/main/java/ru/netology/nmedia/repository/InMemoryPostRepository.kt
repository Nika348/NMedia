package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository : PostRepository {
    private var nextId: Long = 1L
    private var posts = listOf(
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "01 августа в 11:21",
            content = "Эмоциональный интеллект – как и зачем развивать EQ? Отличие EQ от IQ. Анастасия Высоцкая",
            likedByMe = false,
            likes = 845,
            share = 999,
            visability = 1333

        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 марта в 11:21",
            content = "Привет, это  Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            likes = 567,
            share = 784,
            visability = 1234
        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "01 апреля в 12:11",
            content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            likedByMe = false,
            likes = 234,
            share = 56,
            visability = 348
        ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 21:00",
            content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            likedByMe = false,
            likes = 56,
            share = 45,
            visability = 323
        ),

        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "12 июля в 18:36",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            likedByMe = false,
            likes = 45,
            share = 23,
            visability = 156
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (!it.likedByMe) it.likes + 1 else it.likes - 1
            )
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(share = it.share + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            listOf(
                post.copy(
                    id = ++nextId,
                    author = "me",
                    likedByMe = false,
                    published = "now",
                    likes = 0,
                    share = 0,
                    visability = 0
                )
            ) + posts
        } else {
            posts.map {
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        }
        data.value = posts
    }
}