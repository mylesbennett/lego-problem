import java.math.BigDecimal
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

class LegoTest {

    @Test
    fun `Test case 1`() {
        var failCount = 0

        inputs.forEachIndexed { i, pair ->
                val exp = expected[i]
//                val actual = legoBlocksBigDecimal(pair[0], pair[1])
                val actual = legoBlocks(pair[0], pair[1])
                val difference = exp - actual
                if (difference != 0) {
                    println("${pair[0]} ${pair[1]}\nExpected : $exp\nActual    :$actual\nDifference: ${Math.abs(difference)}\n")
                    failCount++
                }
            }
        assertEquals(0, failCount)
    }
}

fun legoBlocks(height: Int, width: Int): Int {
    val modulo = 1000000007

    val allCombos = mutableListOf(0.0, 1.0, 2.0, 4.0, 8.0)
    while (allCombos.size <= width) {
        allCombos.add(allCombos.takeLast(4).sum())
    }
    allCombos.forEachIndexed { i, v ->
        // HackerRank doesn't like Kotlin Double.pow
        allCombos[i] = Math.pow(v, height.toDouble())
    }

    val solidCombos = mutableListOf(0.0, 1.0)
    for (w in 2..width) {
        var sum = 0.0
        for (i in 1 until w) {
            sum += (solidCombos[i] * allCombos[w - i]) % modulo
        }
        solidCombos.add((allCombos[w] - sum) % modulo)
    }

    return (solidCombos.last() % modulo).toInt()
}

fun legoBlocksBigDecimal(height: Int, width: Int): Int {

    val allCombos = mutableListOf(BigDecimal(0), BigDecimal(1), BigDecimal(2), BigDecimal(4), BigDecimal(8))
    while (allCombos.size <= width) {
        allCombos.add(
            allCombos[allCombos.size - 1] +
                    allCombos[allCombos.size - 2] +
                    allCombos[allCombos.size - 3] +
                    allCombos[allCombos.size - 4]
        )

    }
    allCombos.forEachIndexed { i, v ->
        allCombos[i] = v.pow(height)
    }

    val solidCombos = mutableListOf(BigDecimal(0), BigDecimal(1))
    for (w in 2..width) {
        var sum = BigDecimal(0)
        for (i in 1 until w) {
            sum += (solidCombos[i] * allCombos[w - i])
        }
        solidCombos.add((allCombos[w] - sum))
    }

    return (solidCombos.last() % BigDecimal(1000000007)).toInt()
}
