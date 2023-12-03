package day01

import Day
import Lines
import java.util.*

class Day1 : Day() {
    override fun part1(input: Lines): Any {
        var sum = 0;
        for (line in input) {
            var number = line.filter { it.isDigit() }
            sum += (number.first().toString() + number.last()).toInt()
        }
        return sum
    }

    override fun part2(input: Lines): Any {
        val digitMap = mapOf(
            "one" to "1", "two" to "2", "three" to "3",
            "four" to "4", "five" to "5", "six" to "6", "seven" to "7",
            "eight" to "8", "nine" to "9"
        )
        var sum = 0

        for (line in input) {
            var twoDigit = ""
            for (i in line.indices) {
                val digit = digitMap.keys.firstOrNull { it in line.substring(0, i) }
                if (digitMap[digit] != null) {
                    twoDigit += digitMap[digit]
                    break;
                } else if (line[i].isDigit()) {
                    twoDigit += line[i]
                    break;
                }
            }

            for (i in (line.length - 1) downTo 0) {
                val digit = digitMap.keys.firstOrNull { it in line.substring(i, line.length) }
                if (digitMap[digit] != null) {
                    twoDigit += digitMap[digit]
                    break;
                } else if (line[i].isDigit()) {
                    twoDigit += line[i]
                    break;
                }
            }

            sum += twoDigit.toInt()
        }
        return sum
    }
}