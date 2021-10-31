import kotlin.random.Random

fun main() {
    val a = IntArray(15) { Random.nextInt(0, 100) }.toTypedArray()
    println(a.joinToString(","))
    bubbleSort(a)
    println(a.joinToString(","))
}

private fun swap(array: Array<Int>, k: Int, i: Int) {
    val a = array[k]
    array[k] = array[i]
    array[i] = a
}

fun bubbleSort(array: Array<Int>) : Array<Int> {
    for (i in 0..array.size-2){
        var sorted = true
        for (k in 0..array.size-2) {
            if (array[k] > array[k+1]){
                sorted = false
                swap(array, k, k+1)
            }
        }
        if (sorted)
            break
    }
    return array
}