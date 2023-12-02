fun main(){
    val s = readInput(true).lines().map { Regex("""(\d+) (red|green|blue)""").findAll(it)
        .groupBy { it.groupValues[2] }.map { (k,v) -> k to v.maxOf { it.groupValues[1].toLong() } }.toMap() }
    println(s.indices.sumOf { if(s[it]["red"]!! <= 12 && s[it]["green"]!! <= 13 && s[it]["blue"]!! <= 14) it+1 else 0 })
    println(s.sumOf { it.values.prod() })
}