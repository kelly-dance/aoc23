

fun main(){
    var inp = readInput(true).lines().map {
        val (l,r) = it.split(' ')
        Pair(l, r.longs())
    }
    var tot = 0L
    for((s, t) in inp){
        val targ = (1..5).flatMap { t.map{it.toInt()} }
        val r = (1..5).joinToString("?") { s } + "."
        val dp = HashMap<String,Long>()
        fun solve(i: Int, j: Int, p: Int): Long {
            val key = "$i,$j,$p"
            if(key in dp) return dp[key]!!
            var ans = 0L
            if(i==r.length){
                if(j==targ.size && p == 0) return 1L
                else return 0L
            }else{
                if(j==targ.size && p > 0) return 0L
                if(j < targ.size && p > targ[j]) return 0L
                if(r[i] != '.'){ // #
                    ans += solve(i+1, j, p+1)
                }
                if(r[i]!='#' && p==0){ // .
                    ans += solve(i+1, j, 0)
                }
                if(r[i]!='#' && j < targ.size && p == targ[j]){ // .
                    ans += solve(i+1, j+1, 0)
                }
            }
            dp[key]=ans
            return ans
        }
        var loc = solve(0,0,0)
        println("$targ $loc")
        tot += loc
    }
    println(tot)
}

