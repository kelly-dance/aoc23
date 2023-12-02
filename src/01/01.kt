fun p1(){
    println(readInput(true).lines().map {
        it.filter { it.isDigit() }.map { it.digitToInt() }
    }.sumOf { it.first()*10 + it.last() })
}

fun p2(){
    val digits = listOf("","one","two","three","four","five","six","seven","eight","nine")
    val out = readInput(true).lines().map { s->
        s.indices.flatMap { ind ->
            val res = Regex("""^(\d|one|two|three|four|five|six|seven|eight|nine)""").find(s.substring(ind))
            if(res != null) listOf(res.value) else listOf()
        }
    }.map {
        it.map {
            when(it){
                "0","1","2","3","4","5","6","7","8","9" -> it.toInt()
                in digits -> digits.indexOf(it)
                else -> -1000000000
            }
        }
    }.sumOf { it.first()*10 + it.last()}
    println(out)
}

fun main() {
    p1()
    p2()
}

