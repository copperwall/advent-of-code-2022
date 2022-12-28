val priorities = buildPriorities()

fun main() {
    two()
}

// Each line is a knapsack
// Each knapsack is a list of characters,
// split evenly across two compartments
// Find the only thing shared between the two compartments
fun one() {
    val knapsacks = readInput("Day03")
    val priorities = knapsacks.map { getPriorityFromKnapsack(it) }
    val sum = priorities.sum()

    println(sum)
}

fun two() {
    val knapsacks = readInput("Day03")
    // Chunk into threes

    val groups = knapsacks.chunked(3)

    var sum = 0
    for (group in groups) {
        val (one, two, three) = group.map { it.toSet() }

        val intersection = one.intersect(two).intersect(three)
        val intersectingStr = intersection.toList()[0]
        sum += priorities.getValue(intersectingStr)
    }

    println(sum)
}

// Map<char, int>
fun buildPriorities(): Map<Char, Int> {
    val lowercaseRange = 'a'..'z'
    val uppercaseRange = 'A'..'Z'

    var i = 1
    val priorities = mutableMapOf<Char, Int>()

    for (c in lowercaseRange + uppercaseRange) {
        priorities[c] = i
        i += 1
    }

    return priorities
}

fun getPriorityFromKnapsack(knapsack: String): Int {
    // split into two compartments
    val mid = knapsack.length / 2
    val first = knapsack.substring(0 until mid)
    val second = knapsack.substring(mid)

    val intersection = first.split("").intersect(second.split(""))
    val intersectingStr = intersection.toList()[1]
    println(intersectingStr)
    println(priorities.getValue(intersectingStr[0]))

    return priorities.getValue(intersectingStr[0])
}