package ru.netology.nmedia

fun amountSh(shareAmount: Int): String {
    val share = shareAmount.toDouble()
    if (share > 999 && share < 9999) {
        return String.format("%.1f", share / 1000) + "K"
    } else if (share > 9999 && share < 999999)
        return "${(share / 1000).toInt()}K"
    else if (share > 999999) {
        return String.format("%.1f", share / 1000000) + "M"
    }
    return "$shareAmount"
}

fun amountLi(likeAmount: Int): String {
    val like = likeAmount.toDouble()
    if (like > 999 && like < 999999) {
        return String.format("%.1f", like / 1000) + "K"
    } else if (like > 9999 && like < 999999)
        return "${(like / 1000).toInt()}K"
    else if (like > 999999) {
        return String.format("%.1f", like / 1000000) + "M"
    }
    return "$likeAmount"
}

fun amountVi(visabilityAmount: Int): String {
    val visability = visabilityAmount.toDouble()
    if (visability > 999 && visability < 999999) {
        return String.format("%.1f", visability / 1000) + "K"
    } else if (visability > 9999 && visability < 999999)
        return "${(visability / 1000).toInt()}K"
    else if (visability > 999999) {
        return String.format("%.1f", visability / 1000000) + "M"
    }
    return "$visabilityAmount"
}