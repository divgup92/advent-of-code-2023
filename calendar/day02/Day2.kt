package day02

import Day
import Lines
import kotlin.math.max

class Day2 : Day() {
    override fun part1(input: Lines): Any {
        val colors = mapOf("red" to 12, "green" to 13, "blue" to 14)
        var sum = 0
        for (line in input) {
            val splitLine = line.split(",", ";", ":")
            var gameNum = splitLine[0].substring(splitLine[0].indexOf("Game") + 5).toInt()
            var possible = true
            for (value in splitLine) {
                if (!possible) {
                    break
                }

                for (color in colors.keys) {
                    if (value.contains(color)) {
                        val number = value.substring(0, value.indexOf(color)).trim().toInt()
                        if (number > colors[color]!!) {
                            possible = false
                            break
                        }
                    }
                }
            }
            if (possible) {
                sum += gameNum
            }
        }
        return sum
    }

    override fun part2(input: Lines): Any {
        var sum = 0
        for (line in input) {
            val splitLine = line.split(",", ";", ":")
            var colors = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
            for (value in splitLine) {
                for (color in colors.keys) {
                    if (value.contains(color)) {
                        val number = value.substring(0, value.indexOf(color)).trim().toInt()
                        colors.put(color, max(colors.get(color)!!, number))
                        break
                    }
                }
            }
            sum += colors.values.reduce(Int::times)
        }
        return sum
    }
}