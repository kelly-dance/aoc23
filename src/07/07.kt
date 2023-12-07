

fun d7p1(){
    fun rank(hand: String): Int {
        val h = hand.toList()
        val grpd = h.groupBy { it }.toList().map{it.second.size}.sortedBy { -it }.toMutableList()
        if(grpd.isEmpty()) return 7
        if(grpd[0] == 5) return 7
        if(grpd[0] == 4) return 6
        if(grpd[0] == 3 && grpd[1] == 2) return 5
        if(grpd[0] == 3) return 4
        if(grpd[0] == 2 && grpd[1] == 2) return 3
        if(grpd[0] == 2) return 2
        return 1
    }

    var inp = readInput(true).lines().map {
        var (a,b)= it.split(' ')
        a=a.replace('A','E').replace('T','A').replace('J','B').replace('Q','C').replace('K','D')
        Pair(Pair(rank(a),a),b.toLong())
    }
    inp = inp.sortedBy { it.first.second }.sortedBy { it.first.first }
    var s = 0L
    for(i in inp.indices){
        s += (i+1) * inp[i].second
    }
    println(s)
}

fun d7p2(){
    fun rank(hand: String): Int {
        val h = hand.toList()
        var ct = h.filter { it=='1' }.size
        val grpd = h.filter { it!='1' }.groupBy { it }.toList().map{it.second.size}.sortedBy { -it }.toMutableList()
        if(grpd.isEmpty()) return 7
        if(grpd[0]+ct == 5) return 7
        if(grpd[0]+ct == 4) return 6
        if(grpd[0]+ct == 3 && grpd[1] == 2) return 5
        if(grpd[0]+ct == 3) return 4
        if(grpd[0]+ct == 2 && grpd[1] == 2) return 3
        if(grpd[0]+ct == 2) return 2
        return 1
    }

    var inp = readInput(true).lines().map {
        var (a,b)= it.split(' ')
        a=a.replace('A','E').replace('T','A').replace('J','1').replace('Q','C').replace('K','D')
        Pair(Pair(rank(a),a),b.toLong())
    }
    inp = inp.sortedBy { it.first.second }.sortedBy { it.first.first }
    var s = 0L
    for(i in inp.indices){
        s += (i+1) * inp[i].second
    }
    println(s)
}

fun main(){
    d7p1()
    d7p2()
}

