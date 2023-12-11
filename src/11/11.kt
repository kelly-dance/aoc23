import kotlin.math.max
import kotlin.math.min

fun main(){

    var b1 = mutableListOf<Int>()
    var b2 = mutableListOf<Int>()
    var inp = readInput(true).lines().mapIndexed{ ind,s ->
        if('#' !in s) b1.add(ind)
        s
    }
    var rot = inp.first().indices.map { i -> inp.indices.map { j-> inp[j][i] }.joinToString("") }.mapIndexed{ ind,s ->
        if('#' !in s) b2.add(ind)
        s
    }
    b1.sort()
    b2.sort()

    var l = mutableListOf<Pii>()
    for( i in inp.indices)for(j in inp[i].indices){
        if(inp[i][j]=='#') l.add(i to j)
    }
    var t = 0L
    var t2 = 0L
    for(i in l.indices)for(j in i+1 until l.size){
        t += (l[i].first-l[j].first).abs+(l[i].second-l[j].second).abs
        t2 += (l[i].first-l[j].first).abs+(l[i].second-l[j].second).abs
        var d1 = b1.indexOfFirst { it > min(l[i].first, l[j].first) }
        var d2 = b1.indexOfFirst { it > max(l[i].first, l[j].first) }
        var d3 = b2.indexOfFirst { it > min(l[i].second, l[j].second) }
        var d4 = b2.indexOfFirst { it > max(l[i].second, l[j].second) }
        if(d1==-1) d1=b1.size
        if(d2==-1) d2=b1.size
        if(d3==-1) d3=b2.size
        if(d4==-1) d4=b2.size
        t += (d2-d1 + d4-d3);
        t2 += (d2-d1 + d4-d3) * 999999L;
    }
    println(t)
    println(t2)
}

