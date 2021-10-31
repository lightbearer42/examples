import kotlin.random.Random

fun main() {
    val a = IntArray(100) { Random.nextInt() }.toTypedArray()
    a.sort()
    val b = Random.nextInt(0, 100)
    binarySearch(a , 0..99, a[b])
    println(a.joinToString(","))
}

fun binarySearch(array: Array<Int>, a: IntRange, b: Int) {
    if (a.last - a.first < 2)
        return
    val center = (a.first + a.last) / 2
    if (b < array[center]){
        binarySearch(array, a.first..center, b)
    } else if (b > array[center]) {
        binarySearch(array, center..a.last, b)
    } else
        println("Found, b=$b, center=$center, ${array[center]}")
}