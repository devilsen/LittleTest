package com.wuba.acm.question

/**
 * desc :
 * date : 2018/9/26
 * @author : dongSen
 * 观察下面的规律，写一个函数accum
accum(“abcd”);    // “A-Bb-Ccc-Dddd”
accum(“RqaEzty”); // “R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy”
accum(“cwAt”);    // “C-Ww-Aaa-Tttt”
 */


fun accum(text: String): StringBuilder {
    val result = StringBuilder()
    for (index in text.indices) {
        val letter = text[index]
        result.append(letter.toUpperCase())

        val last = letter.toLowerCase()
        for (x in 1..index) {
            result.append(last)
        }

        if (index != text.length - 1)
            result.append("-")
    }

    return result
}

fun main(args: Array<String>) {
    print(accum("abcd"))
}