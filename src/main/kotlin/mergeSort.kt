import kotlin.random.Random

fun main() {
    val a = IntArray(15) { Random.nextInt(0, 100) }.toTypedArray()
    println(a.joinToString(","))
    val buffer = IntArray(a.size).toTypedArray()
    mergeSort(a, buffer, 0, a.size - 1)
    println(a.joinToString(","))
}

fun mergeSort(array: Array<Int>, buffer: Array<Int>, first: Int, last: Int){
    if (last - first < 1)
        return
    val center = (last + first) / 2
    mergeSort(array, buffer, first, center)
    mergeSort(array, buffer, center + 1 , last)
    merge(array, buffer, first, last)
}

fun merge(array: Array<Int>, buffer: Array<Int>, first: Int, last: Int) {
    val center = (first + last) / 2
    var cur1 = first
    var cur2 = center + 1
    for (i in first..last) {
        if(cur1 <= center && cur2 <= last){
            if (array[cur1] > array[cur2]){
                buffer[i] = array[cur2]
                cur2++
            } else {
                buffer[i] = array[cur1]
                cur1++
            }
        } else if (cur1 <= center) {
            buffer[i] = array[cur1]
            cur1++
        } else {
            buffer[i] = array[cur2]
            cur2++
        }
    }
    System.arraycopy(buffer, first, array, first, last - first + 1)
}