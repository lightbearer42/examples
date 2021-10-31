import kotlin.random.Random

fun main() {
    val a = IntArray(15) { Random.nextInt(0, 100) }.toTypedArray()
    println(a.joinToString(","))
    insertSort(a)
    println(a.joinToString(","))
}

fun insertSort(array: Array<Int>) : Array<Int> {
    for (count in 1 until array.size) {
        val item = array[count]
        var i = count
        while (i>0 && item < array[i-1]){
            array[i] = array[i-1]
            i -= 1
        }
        array[i] = item
    }
    return array
}