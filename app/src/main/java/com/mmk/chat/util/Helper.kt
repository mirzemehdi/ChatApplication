package com.mmk.chat.util

import java.sql.Time
import kotlin.random.Random

/**
 * Created by mirzemehdi on 1/28/21
 */
class Helper {

    companion object {

        private const val lorem =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet dui nunc. Donec placerat ac felis id condimentum. In a molestie lectus. Etiam non sollicitudin leo. Duis euismod eget massa vitae faucibus. Suspendisse iaculis, urna a dignissim suscipit, orci justo scelerisque purus, et fringilla justo velit non orci."

        // Returns some random portion of lorem text
        fun getRandomText() = lorem.substring(0, Random.nextInt(lorem.length))

        // Function returns random time string ex: 10:29 AM
        fun getRandomTimeString(): String {
            val builder = StringBuilder("")
            val firstDigit= Random.nextInt(2)
            builder.append(firstDigit)
            val secondDigit=if (firstDigit==0) Random.nextInt(10) else Random.nextInt(2)
            builder.append(secondDigit)
            builder.append(":")
            val thirdDigit= Random.nextInt(6)
            builder.append(thirdDigit)
            val fourthDigit= Random.nextInt(10)
            builder.append(fourthDigit)
            val dayTimeOption = if (Random.nextInt(2) == 0) " AM" else " PM"
            builder.append(dayTimeOption)
            return builder.toString()
        }

    }
}