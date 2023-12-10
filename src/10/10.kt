import java.util.Deque
import java.util.Queue

fun main(){
    // UDLR RLDU
    var start = Pair(0,0)
    var raw = readInput(true).lines().flatMap {
        listOf(it.flatMap { listOf(it,'X') }.joinToString(""), "X.".repeat(it.length))
    }
    var inp = raw.mapIndexed{ ind, line ->
        line.mapIndexed{ j, ch ->
            when(ch){
                '.' -> 0b0000
                '|' -> 0b1100
                '-' -> 0b0011
                'L' -> 0b1001
                'F' -> 0b0101
                '7' -> 0b0110
                'J' -> 0b1010
                'X' -> 0b1111
                'S' -> {
                    start = Pair(ind,j)
                    0b1111
                }
                else -> throw Exception("idk")
            }
        }
    }
    var delt = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).reversed().toList()
    var m = listOf(1,0,3,2)
    var dist = HashMap<Pii,Int>()
    dist[start]=0
    var q = ArrayDeque<Pair<Int,Int>>()
    q.add(start)
    while(q.isNotEmpty()){
        val l = q.first()
        q.removeFirst()
        for(d in 0 until 4){
            var nex = l + delt[d]
            if(nex.first < 0 || nex.first >= inp.size || nex.second <0 || nex.second >= inp.first().size) continue
            if(nex in dist) continue
            if((inp[l.first][l.second] and (1 shl d)) > 0 && (inp[nex.first][nex.second] and (1 shl m[d])) > 0){
                dist[nex] = 1 + dist[l]!!
                q.add(nex)
            }
        }

    }
    println(dist.values.max()/2)
    dist[(-1 to -1)]=-1
    val q2 = mutableListOf<Pii>(-1 to -1)
    while(q2.isNotEmpty()){
        val l = q2.last()
        q2.removeLast()
        for(dir in delt){
            val n = dir + l
            if(n.first < -1 || n.first >= inp.size+1 || n.second < -1 || n.second >= inp.first().size+1) continue
            if(n in dist) continue
            dist[n]=-1
            q2.add(n)
        }
    }
    var c = 0
    for(i in -1..inp.size) {
        for(j in -1..inp.first().size){
            if((i to j) in dist){
//                print(if(dist[i to j]!! ==-1) ' ' else '#')
            }else{
                if(i%2==0 && j%2==0) {
                    c++
//                    print('I')
                }else{
//                    print(' ')
                }
            }

        }
//        println()
    }
    println(c) // 574
}

