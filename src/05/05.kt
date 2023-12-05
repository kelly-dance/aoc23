import kotlin.math.min

fun main(){
    var parts = readInput(true).split("\n\n")
    val seeds =  Regex("""(\d+)""").findAll(parts.first()).toList().map { it.value.toLong() }
    println(seeds)
    val maps = parts.drop(1).map {
        it.lines().drop(1).map {
            it.split(' ').map { it.toLong() }
        }.map{(a,b,c) -> listOf(b,a,c)}
    }
    fun ap(map: List<List<Long>>, loc: Long): Long {
        val (t,s,l) = map.find { it[0] <= loc && it[0]+it[2] > loc } ?: listOf<Long>(0,0,0)
        return t+(loc-s)
    }
    var best = 100000000000L
    for(seed in seeds){
        var loc = seed
        for(rs in maps) loc=ap(rs, loc)
        best= min(best,loc)
    }
    println(best)
    var idk = seeds.windowed(2,2).map { (s,l) ->
        var best = 100000000000L
        for(v in s until s+l){
            var loc = v
            for(rs in maps) loc=ap(rs,loc)
            best=min(best,loc)
        }
        best
    }
    println(idk.min())
}

