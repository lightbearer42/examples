import kotlin.random.Random

fun main() {
    val a = IntArray(15) { Random.nextInt(0, 100) }.toTypedArray()
    println(a.joinToString(","))
    quickSort(a, a.indices)
    println(a.joinToString(","))
}

fun quickSort(array: Array<Int>, range: IntRange) {
    if (range.last <= range.first)
        return

    var center = (range.last + range.first) / 2
    val centerVal = array[center]

    var j = range.last
    var i = range.first
    while (i <= j) {
        while (array[i] < centerVal)
            i++
        while (array[j] > centerVal)
            j--
        if (i > j)
            break
        val tmp = array[i]
        array[i] = array[j]
        array[j] = tmp
        i++
        j--
    }

    quickSort(array, range.first..j)
    quickSort(array, i..range.last)
}
