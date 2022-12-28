fun main() {
   //main1();
   main2()
}
fun main1() {
   val inputLines = readInput("Day04-basic")

   val pairs = inputLines.map { line ->
      line.split(",").map { it.split("-").map { str -> str.toInt() } }
   }

   // Find

   var duplicates = 0

   for (pair in pairs) {
      val (firstElf, secondElf) = pair

      val firstRange = IntRange(firstElf[0], firstElf[1])
      val secondRange = IntRange(secondElf[0], secondElf[1])

      val smallerRange = if (firstRange.count() < secondRange.count()) {
         firstRange
      } else {
         secondRange
      }

      val firstIntersection = firstRange.intersect(secondRange)
      // If the intersection is the size of the smaller range, it exists totally within the larger one
      if (firstIntersection.size == smallerRange.count()) {
         duplicates += 1
      }
   }

   println(duplicates)
}

fun main2() {
   val inputLines = readInput("Day04")

   val pairs = inputLines.map { line ->
      line.split(",").map { it.split("-").map { str -> str.toInt() } }
   }

   // Find

   var duplicates = 0

   for (pair in pairs) {
      val (firstElf, secondElf) = pair

      val firstRange = IntRange(firstElf[0], firstElf[1])
      val secondRange = IntRange(secondElf[0], secondElf[1])

      val firstIntersection = firstRange.intersect(secondRange)
      // If the intersection is the size of the smaller range, it exists totally within the larger one
      if (!firstIntersection.isEmpty()) {
         duplicates += 1
      }
   }

   println(duplicates)
}
