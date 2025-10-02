import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()
    var cnt = 0
    var arr = IntArray(n)

    fun check(col: Int): Boolean {
        for (i in 0 until col) {
            if (arr[col] == arr[i]) {
                return false
            }
            else if (abs(col - i) == abs(arr[col] - arr[i])) {
                return false
            }
        }
        return true
    }

    fun nQueen(col: Int) {
        if (col == n) {
            cnt ++
            return;
        }

        for (i in 0 until n) {
            arr[col] = i
            if (check(col)) {
                nQueen(col + 1)
            }
        }
    }

    nQueen(0)
    println(cnt)
}