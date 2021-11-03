fun main() {
    val arr = intArrayOf(11,9,4,7,3,4,5,9,1,12)
    val arr1 = intArrayOf(1,9,10,1,20)
    println(calcIncome(arr1))
}

fun calcIncome(arr: IntArray): Int {
    var income = 0
    var i = 0;
    while (true) {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE

        while (i < arr.size) {
            if (min < arr[i]) {
                break
            }
            min = arr[i]
            i++
        };
        var j = i;
        while (j < arr.size) {
            if (max > arr[j]) {
                break
            }
            max = arr[j]
            j++
        };

        if (j >= arr.size) {
            if (max > min) {
                income += max - min;
            }
            break
        }

        income += max - min;
        i = j
    }
    return income;
}