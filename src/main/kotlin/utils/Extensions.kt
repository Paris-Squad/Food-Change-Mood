package org.example.utils

fun String.lineHasOddQuoteCount(): Boolean {
    return this.count { it == '"' } % 2 != 0
}

fun String.lineHasEvenOrZeroQuoteCount(): Boolean {
    return this.count { it == '"' } % 2 == 0
}

fun Float?.inRange(range: ClosedFloatingPointRange<Float>) : Boolean{
    return  this?.let {
        this in range
    } ?: false
}