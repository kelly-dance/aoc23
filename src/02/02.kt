import kotlin.math.max

fun main(){
    val s = readInput(true).lines().map { Regex("""(\d+) (red|green|blue)""").findAll(it)
        .map { Pair(it.groupValues[1].toInt(), it.groupValues[2]) } }.map{
        val cts = DefaultMap<String,Int> {0}
        for ((ct,clr) in it) cts.applyOn(clr) { max(it, ct) }
        cts
    }
    println(s.indices.sumOf { if(s[it]["red"] <= 12 && s[it]["green"] <= 13 && s[it]["blue"] <= 14) it+1 else 0 })
    println(s.sumOf { it.values.fold(1L) { l, r -> l * r } })
}
