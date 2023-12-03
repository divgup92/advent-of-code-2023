package day03

import Day
import Lines
import kotlin.math.max
import kotlin.math.min

class Day3 : Day() {
    private var numberList = mutableListOf<Map<Int, MutableList<Pair<Int, Int>>>>()
    private var specialCharList = mutableListOf<List<Int>>()
    private var starCharList = mutableListOf<List<Int>>()

    override fun part1(input: Lines): Any {

        var sum = 0.0
        buildLists(input)

        for ((i, numberLine) in numberList.withIndex()) {
            val lineList = specialCharList.subList(max(0, i - 1), min(specialCharList.size, i + 2))
            for (number in numberLine) {
                for (pos in number.value) {
                    if (isPartNumber(pos, lineList, input[0].length - 1)) {
                        sum += number.key
                    }
                }
            }
        }
        return sum

    }

    private fun buildLists(input: Lines) {
        for (line in input) {
            var start = -1
            var num = 0
            val numberMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
            val specialCharLine = mutableListOf<Int>()
            val starCharLine = mutableListOf<Int>()
            for (i in line.indices) {

                if (line[i].isDigit() && start >= 0) {
                    num = num * 10 + line[i].digitToInt()
                } else if (line[i].isDigit()) {
                    start = i
                    num = line[i].digitToInt()
                } else if (start >= 0) {
                    val listPos = numberMap.getOrDefault(num, mutableListOf())
                    listPos.add(Pair(start, i - 1))
                    numberMap[num] = listPos
                    start = -1
                    num = 0
                }

                if (!line[i].isDigit() && line[i] != '.') {
                    specialCharLine.add(i)
                }

                if (line[i] == '*') {
                    starCharLine.add(i)
                }
            }
            if (start > 0) {
                val listPos = numberMap.getOrDefault(num, mutableListOf())
                listPos.add(Pair(start, line.length - 1))
                numberMap[num] = listPos
            }
            numberList.add(numberMap)
            specialCharList.add(specialCharLine)
            starCharList.add(starCharLine)
        }
    }

    private fun isPartNumber(startEndNumber: Pair<Int, Int>, lines: List<List<Int>>, lastIndex: Int): Boolean {
        val start = max(0, startEndNumber.first - 1)
        val end = min(startEndNumber.second + 1, lastIndex)
        for (line in lines) {
            for (specialCharPos in line) {
                if (specialCharPos in start..end) {
                    return true
                }
            }
        }
        return false
    }

    override fun part2(input: Lines): Any {
        buildLists(input)
        var sum = 0
        for ((i, starLine) in starCharList.withIndex()) {
            val lineList = numberList.subList(max(0, i - 1), min(specialCharList.size, i + 2))
            for (star in starLine) {
                sum += getGearProduct(star, lineList, input[0].length)
            }
        }
        return sum
    }

    private fun getGearProduct(starPos: Int, lines: List<Map<Int, MutableList<Pair<Int, Int>>>>, lastIndex: Int): Int {
        val adjNumbers = mutableListOf<Int>()
        for (line in lines) {
            for (num in line) {
                for (numPos in num.value) {
                    val start = max(numPos.first - 1, 0)
                    val end = min(numPos.second + 1, lastIndex)
                    if (starPos in start..end) {
                        adjNumbers.add(num.key)
                    }
                }
            }
        }
        if (adjNumbers.size == 2) {
            return adjNumbers.reduce(Int::times)
        }
        return 0
    }
}