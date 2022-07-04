package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            share = 11000
        )

        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if(post.likedByMe){
                likes?.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            likes?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likesAmount?.text = amountLi(post.likes)
            }
            share?.setOnClickListener {
                post.share++
                shareAmount?.text = amountSh(post.share)
            }
        }
    }
}

fun amountSh(shareAmount: Int): String{
    var share = shareAmount.toDouble()
    if (share > 999 && share < 9999) {
        return String.format("%.1f", share / 1000)+ "K"
    } else if(share > 9999 && share < 999999)
        return "${(share / 1000).toInt()}K"
    else if(share >999999){
        return String.format("%.1f", share / 1000000)+ "M"
    }
    return "$shareAmount"
}

fun amountLi(likeAmount: Int): String{
    var like = likeAmount.toDouble()
    if (like > 999 && like < 999999) {
        return String.format("%.1f", like / 1000)+ "K"
    } else if(like > 9999 && like < 999999)
        return "${(like / 1000).toInt()}K"
    else if(like >999999){
        return String.format("%.1f", like / 1000000)+ "M"
    }
    return "$likeAmount"
}