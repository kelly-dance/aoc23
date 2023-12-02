import kotlin.math.max

fun main(){
    val shared = readInput(true).lines().map { Regex("""(\d+) (red|green|blue)""").findAll(it)
        .map { Pair(it.groupValues[1].toInt(), it.groupValues[2]) }.toList() }.mapIndexed{ ind, l ->
        val cts = DefaultMap<String,Int> {0}
        for ((ct,clr) in l) cts.applyOn(clr) { max(it, ct) }
        Pair(ind,cts)
    }
    val p1 = shared.filter { (ind,it) -> it["red"] <= 12 && it["green"] <= 13 && it["blue"] <= 14 }.sumOf { (ind,_) -> ind+1 }
    println(p1)
    val p2 = shared.map { (ind,cts) -> cts.values.fold(1) { l,r -> l*r } }.sum()
    println(p2)
}
