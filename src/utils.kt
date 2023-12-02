import java.io.File
import kotlin.math.absoluteValue

typealias Pii = Pair<Int, Int>

fun readInput(real: Boolean): String {
    val file = File(if(real) "./real.txt" else "./test.txt")
    return file.readText().replace("\r\n", "\n").trim()
}

fun MatchResult.ints(): List<Int> {
    return this.groupValues.drop(1).map(String::toInt)
}

fun<K, V> MutableMap<K, V>.applyOn(key: K, fn: (value: V) -> V) = apply {
    this[key] = fn(this.getValue(key))
}

class DefaultMap<K, V>(val deriveDefault: (key: K) -> V) : HashMap<K, V>() {
    override operator fun get(key: K): V {
        if(!super.containsKey(key)) super.put(key, deriveDefault(key))
        return super.get(key)!!
    }
}

fun<T> List<T>.splitWhen(includeBoundary: Boolean = false, predicate: (T) -> Boolean): List<List<T>> {
    return flatMapIndexed { index: Int, value: T ->
        when {
            index == 0 || index == this.lastIndex -> listOf(index)
            predicate(value) -> listOf(index - 1, index + if (includeBoundary) 0 else 1)
            else -> emptyList()
        }
    }.windowed(size = 2, step = 2) { (from, to) -> slice(from..to) }
}


fun sign(x: Int): Int {
    return when {
        x > 0 -> 1
        x < 0 -> -1
        else -> 0
    }
}

operator fun Pii.plus(otr: Pii): Pii {
    return Pair(this.first + otr.first, this.second + otr.second)
}

operator fun Pii.minus(otr: Pii): Pii {
    return Pair(this.first - otr.first, this.second - otr.second)
}

operator fun Pii.times(otr: Int): Pii {
    return this.first*otr to this.second*otr
}

fun<T> List<T>.split(on: T) = splitWhen { it == on }

fun<T> Iterable<T>.cycle() = generateSequence { this }.flatten()

fun Iterable<Int>.prod() = this.fold(1) { l, r -> l * r }
fun Iterable<Long>.prod() = this.fold(1L) { l, r -> l * r }

fun<T> Collection<T>.eachCount() = this.groupingBy { it }.eachCount()

fun<T, R> Iterable<T>.cartesianProduct(other: Iterable<R>): MutableList<Pair<T, R>> {
    return this.flatMap { first -> other.map { Pair(first, it) } }.toMutableList()
}

val Char.Companion.box: Char
    get() = '█'

val Int.abs: Int
    get() = this.absoluteValue

infix fun Int.mod(otr: Int): Int {
    return (this % otr  + otr) % otr
}

infix fun Long.mod(otr: Long): Long {
    return (this % otr  + otr) % otr
}

