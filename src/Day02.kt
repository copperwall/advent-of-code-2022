import kotlin.Exception

const val TIE = 3
const val LOSE = 0
const val WIN = 6

enum class Move {
    ROCK, PAPER, SCISSORS
}

enum class Outcome {
    LOSS, TIE, WIN
}

fun stringToOutcome(s: String): Outcome {
    return when (s) {
        "X" -> Outcome.LOSS
        "Y" -> Outcome.TIE
        "Z" -> Outcome.WIN
        else -> throw Exception("Unknown string $s")
    }
}


val pointsForMove = mapOf(Move.ROCK to 1, Move.PAPER to 2, Move.SCISSORS to 3)

fun strToMove(move: String): Move {
    if (move == "A" || move == "X") return Move.ROCK
    if (move == "B" || move == "Y") return Move.PAPER
    if (move == "C" || move == "Z") return Move.SCISSORS

    throw Exception("Unknown move $move")
}
fun main() {
    part2()
}

fun part2() {
    val testInput = readInput("Day02")
    var sum = 0

    for (roundStr in testInput) {
        sum += calculateRound2(roundStr)
    }

    println(sum)
}

fun part1() {
    val testInput = readInput("Day02")

    var sum = 0

    for (roundStr in testInput) {
        sum += calculateRound1(roundStr)
    }

    println(sum)
}

fun calculateRound1(round: String): Int {
    val (opponentMove, myMove) = round.split(" ").map {
        strToMove(it)
    }

    val movePoints = pointsForMove.getValue(myMove)
    val gamePoints = gameLogic(opponentMove, myMove)

    return movePoints + gamePoints
}

fun determineMyMove(opponentMove: Move, outcome: Outcome): Move {
    when (opponentMove) {
        Move.ROCK -> {
            return when (outcome) {
                Outcome.WIN -> Move.PAPER
                Outcome.LOSS -> Move.SCISSORS
                Outcome.TIE -> Move.ROCK
            }
        }
        Move.PAPER -> {

            return when (outcome) {
                Outcome.WIN -> Move.SCISSORS
                Outcome.LOSS -> Move.ROCK
                Outcome.TIE -> Move.PAPER
            }
        }
        else -> {
            return when (outcome) {
                Outcome.WIN -> Move.ROCK
                Outcome.LOSS -> Move.PAPER
                Outcome.TIE -> Move.SCISSORS
            }
        }
    }
}

fun calculateRound2(round: String): Int {
    val (opponentMoveStr, outcomeStr) = round.split(" ")

    val outcome = stringToOutcome(outcomeStr)
    val opponentMove = strToMove(opponentMoveStr)
    val myMove = determineMyMove(opponentMove, outcome)

    val movePoints = pointsForMove.getValue(myMove)
    val gamePoints = gameLogic(opponentMove, myMove)

    return movePoints + gamePoints
}

fun gameLogic(opponent: Move, me: Move): Int {
    // Tie
    if (opponent == me) {
        return TIE
    }

    return if (opponent == Move.ROCK) {
        if (me == Move.SCISSORS) LOSE else WIN
    } else if (opponent == Move.SCISSORS) {
        if (me == Move.PAPER) LOSE else WIN
    } else {
        // Opponent Paper
        if (me == Move.ROCK) LOSE else WIN
    }
}