import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val st = StringTokenizer(readln(), " ")
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val map: Array<Array<String>> = Array(r) { Array(c) { "" } }
    val alphabet = IntArray(26)
    var result = 0

    val dr = intArrayOf(-1, 1, 0, 0)
    val dc = intArrayOf(0, 0, -1, 1)

    // A의 아스키 코드: 65
    // 방문 시 alpha[ASCII-65] = 1로 지정
    for (i in 0 until r) {
        val input = readln()
        for (j in 0 until c) {
            map[i][j] = input[j].toString()
        }
    }

    fun move(col : Int, row : Int, alpha : IntArray, cnt : Int) {
        // 방문 처리
        alpha[(map[row][col].first() - 65).code] = 1
        // 최댓값 갱신
        result = max(cnt, result)

        // 상하좌우 이동 가능 여부 체크
        for (i in 0 until 4) {
            val nc = col + dc[i]
            val nr = row + dr[i]

            // 보드를 벗어날 수 없음
            if (nc < 0 || nr < 0 || nc >= c || nr >= r ) {
                continue
            }
            // 이미 만난 적이 있는 알파벳인 경우 패스
            if (alpha[(map[nr][nc].first() - 65).code] == 1) {
                continue
            }
            // 다음 칸으로 이동
            move(nc, nr, alpha, cnt + 1)
        }
        // 재귀에서 나오면서 방문 표시 해제
        alpha[(map[row][col].first() - 65).code] = 0

    }

    move(0, 0, alphabet, 1)
    println(result)
}