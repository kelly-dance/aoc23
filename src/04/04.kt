import kotlin.math.min

fun main(){
    val inp = readInput(true).lines().map{
        it.substring(it.indexOf(':')).split('|').map{
            Regex("""(\d+)""").findAll(it).toList().map { it.value.toLong() }
        }
    }.map { (a,b) -> a.intersect(b).size }
    println(inp.sumOf { (1 shl it)/2 })
    val cts = inp.map { 1 }.toMutableList()
    for(i in inp.indices) for(j in i+1 until min(i+1+inp[i], inp.size)) cts[j] += cts[i]
    println(cts.sum())
}