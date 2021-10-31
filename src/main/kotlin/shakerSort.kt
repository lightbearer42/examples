import kotlin.random.Random

fun main() {
    val a = IntArray(15) { Random.nextInt(0, 100) }.toTypedArray()
    println(a.joinToString(","))
    shakerSort(a)
    println(a.joinToString(","))
}

private fun swap(array: Array<Int>, k: Int, i: Int) {
    val a = array[k]
    array[k] = array[i]
    array[i] = a
}

fun shakerSort(array: Array<Int>) : Array<Int> {
    var z = 0
    var left = 0
    var right = array.size - 1
    while (left <= right) {
        for (i in left until right){
            if (array[i] > array[i+1]){
                swap(array, i, i+1)
                z++
            }
        }
        right--
        for (i in right downTo  left+1){
            if (array[i] < array[i-1]){
                swap(array, i, i-1)
                z++
            }

        }
        left++
    }
    return array
}