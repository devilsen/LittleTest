package com.test.devilsen.test

import org.junit.Test
import kotlin.math.ceil

/**
 * desc :
 * date : 2020/9/24 3:15 PM
 * @author : dongSen
 */

class AlignSizeTest {

    @Test
    fun testAlignSize() {
        newTemplateBuilder(720, 1280)
    }

    private fun newTemplateBuilder(width: Int, height: Int) {
        if (width <= 0 || height <= 0) return

        val vShorter = width.coerceAtMost(height)
        val vLonger = width.coerceAtLeast(height)
        val tShorter = 720.coerceAtMost(vShorter)

        val number = tShorter * (vLonger / vShorter.toFloat()).toDouble()
        println("number: $number")
        val tLonger = ceil(number).toInt()

        val tWidth: Int
        val tHeight: Int
        if (width >= height) {
            tWidth = tLonger
            tHeight = tShorter
        } else {
            tWidth = tShorter
            tHeight = tLonger
        }
        println("width: $tWidth height: $tHeight")

        align(tWidth, 4)
        align(tHeight, 2)
    }

    private fun align(size: Int, align: Int): Int {
        val numb = size / align.toFloat()
        println("number1 : $numb")
        val result = (ceil(numb.toDouble()) * align).toInt()
        println("result : $result")
        println()
        return result
    }

}