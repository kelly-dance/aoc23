
val dirs = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1,1 to 1,-1 to -1, 1 to -1, -1 to 1)

fun d3p1(){
    val cov = HashSet<Pair<Int,Int>>()
    val g = readInput(true).lines().map { it.toList() }
    val w = g.size
    val h = g.first().size
    var tot = 0L
    for (i in g.indices) for(j in g[i].indices){
        if((i to j) in cov) continue
        if(!g[i][j].isDigit())continue
        var good = false
        for ((dx,dy) in dirs){
            val x = i+dx
            val y = j+dy
            if(!g.indices.contains(x)) continue
            if(y !in g[x].indices) continue
            if(g[x][y]!='.' && !g[x][y].isDigit()){
                good=true
            }
        }

        var p = j
        while(p>0 && g[i][p-1].isDigit())p-=1
        var s = g[i][p].toString()
        val loc = HashSet<Pair<Int,Int>>()
        loc.add(i to p)
        while(p+1 < h && g[i][p+1].isDigit()) {
            s+=g[i][p+1]
            p+=1
            loc.add(i to p)
        }
        if(good){
            cov.addAll(loc)
            tot += s.toLong()
        }
    }

    println(tot)

}

fun d3p2(){
    val g = readInput(true).lines().map { it.toList() }
    val w = g.size
    val h = g.first().size
    var tot = 0L
    var adj = HashMap<Pii,HashSet<Pair<Pii,Int>>>()
    for (i in g.indices) for(j in g[i].indices){
        if(!g[i][j].isDigit())continue
        var nexTo = mutableListOf<Pii>()
        for ((dx,dy) in dirs){
            val x = i+dx
            val y = j+dy
            if(!g.indices.contains(x)) continue
            if(y !in g[x].indices) continue
            if(g[x][y]!='.' && !g[x][y].isDigit()){
                nexTo.add(x to y)
            }
        }

        var p = j

        while(p>0 && g[i][p-1].isDigit())p-=1
        val start = i to p
        var s = g[i][p].toString()
        while(p+1 < h && g[i][p+1].isDigit()) {
            s+=g[i][p+1]
            p+=1
        }
        for(l in nexTo){
            if(l !in adj) adj[l]=HashSet()
            adj[l]!!.add(start to s.toInt())
        }

    }
    var tott = 0L
    for((x,y) in adj.keys){
        if(g[x][y]!='*') continue
        if(adj[x to y]!!.size != 2) continue
        val (a,b) = adj[x to y]!!.toList()
        tott += (a.second*b.second)
    }
    println(tott)

}

fun main(){
    d3p1()
    d3p2()
}