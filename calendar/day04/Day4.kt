package day04

import Day
import Lines
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

class Day4 : Day() {
    override fun part1(input: Lines): Any {
        var sum = 0
        for(line in input) {
            val lineSplit = line.split(":", "|")
            val winningNums = lineSplit[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val myNums = lineSplit[2].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            var matching = 0
            for(winningNum in winningNums) {
                if(myNums.contains(winningNum)) {
                    matching++
                }
            }
            val two = 2.0
            sum += two.pow(matching-1).toInt()
            println("$matching $sum")
        }
        return sum
    }

    override fun part2(input: Lines): Any {
        var sum = 0
        val numCardMap = mutableMapOf<Int, Int>()
        for(i in 1..input.size) {
            numCardMap[i] = 1
        }
        for(line in input) {
            val lineSplit = line.split(":", "|")
            val cardNum = lineSplit[0].filter { it.isDigit() }.toInt()
            if(cardNum == input.size) {
                break
            }
            val winningNums = lineSplit[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val myNums = lineSplit[2].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            var matching = 0

            for(winningNum in winningNums) {
                if(myNums.contains(winningNum)) {
                    matching++
                }
            }
            val base = numCardMap[cardNum]
            for(i in min(cardNum+1, input.size)..min(input.size, cardNum+matching)) {
                print("$i,$cardNum ")
                numCardMap[i] = numCardMap[i]!! + base!!
            }
            println()
        }
        println(numCardMap)
        return numCardMap.values.sum()
    }
}