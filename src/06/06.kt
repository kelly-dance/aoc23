
fun d6p1(){
    var (t,d) = readInput(true).lines().map { Regex("""(\d+)""").findAll(it).toList().map {it.value.toLong() }}
    var w = 1
    for(i in t.indices){
        var ct = 0
        for(a in 0..t[i]){
            if(a*(t[i]-a)>d[i]) ct+=1
        }
        w*=ct
    }
    println(w)
}

fun d6p2(){
    var (t,d) = readInput(true).lines().map { Regex("""(\d+)""").findAll(it).toList().map {it.value }.joinToString("").toLong()}
    var ct = 0
    for(a in 0..t){
        if(a*(t-a)>d) ct+=1
    }
    println(ct)
}

fun main(){
    d6p1()
    d6p2()
}


