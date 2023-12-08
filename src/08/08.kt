

fun LCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

/*
*  I didn't save my part 1 code :/
* */

fun main(){
    var (a,b) = readInput(true).split("\n\n")
    println(a)
    var m = b.lines().map{
        var (l,r) = it.split(" = ")
        Pair(l, Pair(r.substring(1,4), r.substring(6,9)))
    }.toMap()
    var tot = 1L
    for(k in m.keys){
        if(k[2]!='A') continue
        var loc = k
        var s = 0L
        var times = mutableListOf<Pair<Long,String>>()
        for(c in a.toList().cycle()){
            s+=1
            if(c=='L') loc=m[loc]!!.first
            if(c=='R') loc=m[loc]!!.second
            if(loc[2]=='Z') {
                times.add(Pair(s,loc))
            }
            if(times.size > 3) break
        }
        println(times[1].first-times[0].first)
        tot = LCM(tot, times[1].first-times[0].first)
    }
    println(tot)
}

