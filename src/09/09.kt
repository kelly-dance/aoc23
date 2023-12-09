

fun main(){
    var p1 = readInput(true).lines().map {
        it.longs()
    }.sumOf {
        var l = mutableListOf(it.toMutableList())
        while(l.last().distinct().size != 1){
            l.add(l.last().windowed(2).map{ (a,b) -> b-a }.toMutableList())
        }
        l.last().add(l.last().last())
        for(i in l.indices.reversed().drop(1)){
            l[i].add(l[i].last() + l[i+1].last())
        }
        l[0].last()
    }
    var p2 = readInput(true).lines().map {
        it.longs().reversed().toList()
    }.sumOf {
        var l = mutableListOf(it.toMutableList())
        while(l.last().distinct().size != 1){
            l.add(l.last().windowed(2).map{ (a,b) -> b-a }.toMutableList())
        }
        l.last().add(l.last().last())
        for(i in l.indices.reversed().drop(1)){
            l[i].add(l[i].last() + l[i+1].last())
        }
        l[0].last()
    }
    println("$p1 $p2")
}


