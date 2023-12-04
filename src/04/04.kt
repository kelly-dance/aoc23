import kotlin.math.min

fun main(){
    var inp = readInput(true).lines().map{
        it.split(':')[1].split('|').map{
            Regex("""(\d+)""").findAll(it).toList().map { it.value.toLong() }
        }
    }.map { (a,b) ->
        val cts = DefaultMap<Long,Long>{0}
        for(v in a) cts.applyOn(v){it+1}
        var c=0
        for(v in b) if(cts[v]>0){
            c+=1
            cts.applyOn(v){it-1}
        }
        c
    }
    println(inp.sumOf { (1 shl it)/2 })
    var cts = mutableListOf<Long>()
    for(x in inp.indices) cts.add(1)
    for(i in inp.indices) for(j in i+1 until min(i+1+inp[i], inp.size)){
        cts[j] += cts[i]
    }
    println(cts.sum())
}