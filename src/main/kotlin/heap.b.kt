import java.util.*
import kotlin.random.Random

fun main() {
    val q = PriorityQueue<Int>()
    q.addAll(arrayOf(1, 6, 4, 8, 7, 10, 9))

    val heap = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    var size = 0;

    fun popMin() : Int {
        val result = heap[0]
        heap[0] = heap[size-1]
        heap[size-1] = 0
        size--
        fun balance(i: Int) {
            val firstChild = 2 * i + 1
            val secondChild = 2 * i + 2
            if (firstChild >= size) return

            var nextChild = firstChild
            if (secondChild < size && heap[secondChild] < heap[firstChild]) {
                nextChild = secondChild
            }

            if (heap[i] <= heap[nextChild])
                return

            val t = heap[nextChild]
            heap[nextChild] = heap[i]
            heap[i] = t

            balance(nextChild)
        }
        balance(0)
        return result
    }

    fun insert(a: Int) {
        heap[size++] = a
        fun balance(i: Int) {
            var j = i
            while (true) {
                val parent = (j - 1) / 2
                if (heap[parent] <= heap[j] || parent < 0)
                    break
                val t = heap[parent]
                heap[parent] = heap[j]
                heap[j] = t
                j = parent
            }
        }
        balance(size-1)
    }

    /*println(popMin())
    println(heap.sliceArray(0 until size).joinToString(","))
    println(q.poll())
    println(q.toArray().joinToString(","))
    println(popMin())
    println(heap.sliceArray(0 until size).joinToString(","))
    println(q.poll())
    println(q.toArray().joinToString(","))
    println(popMin())
    println(heap.sliceArray(0 until size).joinToString(","))
    println(q.poll())
    println(q.toArray().joinToString(","))

    insert(6)
    println(heap.sliceArray(0 until size).joinToString(","))
    insert(4)
    println(heap.sliceArray(0 until size).joinToString(","))
    insert(1)
    println(heap.sliceArray(0 until size).joinToString(","))*/

    val a = IntArray(10) { Random.nextInt(0, 100) }.toTypedArray()
    a.forEach { insert(it) }

    arrayOf(3, 5, 1, 3, 5, 9, 23)

    for(i in 0 until size) {
        println(popMin())
    }
}
