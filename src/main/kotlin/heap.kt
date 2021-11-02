import kotlin.random.Random

class Heap {
    private val data: IntArray
    private var size: Int

    constructor(data: IntArray, size: Int) {
        this.data = data
        this.size = size
    }

    constructor(size: Int) {
        data = IntArray(size)
        this.size = 0
    }

    fun pop(): Int {
        val result = data[0]
        data[0] = data[size-1]
        data[size-1] = 0
        size--
        balanceDown(0)
        return result
    }

    fun size(): Int{
        return size
    }

    fun toArray(): IntArray {
        val array = IntArray(size)
        for (i in 0 until size) {
            array[i] = pop()
        }
        return array
    }

    private fun balanceDown(i: Int) {
        while (true) {
            val firstChild = 2 * i + 1
            val secondChild = 2 * i + 2
            if (firstChild >= size) break

            var nextChild = firstChild
            if (secondChild < size && data[secondChild] < data[firstChild]) {
                nextChild = secondChild
            }

            if (data[i] <= data[nextChild])
                break

            val t = data[nextChild]
            data[nextChild] = data[i]
            data[i] = t

            balanceDown(nextChild)
        }
    }

    fun insert(a: Int) {
        data[size++] = a
        fun balance(i: Int) {
            var j = i
            while (true) {
                val parent = (j - 1) / 2
                if (data[parent] <= data[j] || parent < 0)
                    break
                val t = data[parent]
                data[parent] = data[j]
                data[j] = t
                j = parent
            }
        }
        balance(size-1)
    }

    companion object {
        fun toHeap(array: IntArray): Heap {
            val heap = Heap(array, array.size)
            for (i in (array.size / 2) downTo 0) {
                heap.balanceDown(i)
            }
            return heap
        }
    }
}

fun main() {
    val arr = IntArray(10) { Random.nextInt(0, 100) }
    val arr1 = arr.clone()
    println(arr1.joinToString(","))

    val heap = Heap.toHeap(arr)

    println(heap.toArray().joinToString(","))

    println()

    val heap1 = Heap(arr1.size)
    for(i in 0 until arr1.size) {
        heap1.insert(arr1[i])
    }

    println(heap1.toArray().joinToString(","))
}
