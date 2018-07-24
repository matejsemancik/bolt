fun Double.round(n: Int): Double {
    return Math.round(this * 10.0 * n) / (10.0 * n)
}

fun Double.floor(n: Int): Double {
    return Math.floor(this * 10.0 * n) / (10.0 * n)
}

fun Double.ceil(n: Int): Double {
    return Math.ceil(this * 10.0 * n) / (10.0 * n)
}
