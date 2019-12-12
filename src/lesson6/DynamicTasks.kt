@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import java.util.ArrayList
import kotlin.math.max
import kotlin.math.min


/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
// Алгоритм: O(n*m) где m - длина первой строки длина, n - длина второй строки
// Память: O(n*m) где m - длина первой строки длина, n - длина второй строки
fun longestCommonSubSequence(first: String, second: String): String {
    val listChar = mutableListOf<Char>()
    val matrix = List(first.length + 1) { IntArray(second.length + 1) }
    var i = first.length - 1
    var j = second.length - 1
    for (i in first.indices) {
        for (j in second.indices) {
            if (first[i] == second[j]) {
                matrix[i + 1][j + 1] = 1 + matrix[i][j]
            } else {
                matrix[i + 1][j + 1] = max(matrix[i + 1][j], matrix[i][j + 1])
            }
        }
    }
    while (i >= 0 && j >= 0) {
        when {
            first[i] == second[j] -> {
                listChar.add(0, first[i])
                i--
                j--
            }
            matrix[i + 1][j] > matrix[i][j + 1] -> j--
            else -> i--
        }
    }
    return listChar.joinToString("")
}

/* другой вариант матицы
val fir = " $first"
    val sec = " $second"
    val list = List(fir.length) { CharArray(sec.length) }
    for (i in fir.indices) {
        for (j in sec.indices) {
            if (i == 0 && j == 0 || i != 0 && j != 0)
                list[i][j] = '0'
            if (i == 0 && j != 0)
                list[i][j] = sec[j]
            if (i != 0 && j == 0)
                list[i][j] = fir[i]

        }
    }
 */

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    TODO()
}
/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
// Алгоритм: O(n*m) где m - кол-во строк, n - кол-во столбцов
// Память: O(n*m) где m - кол-во строк, n - кол-во столбцов
fun shortestPathOnField(inputName: String): Int {
    val readF = File(inputName).bufferedReader().readLines()
    val list = List(readF.size) { IntArray(readF[0].length) }
    list[0][0] = readF[0][0].toString().toInt()
    for (j in 2..readF[0].length)
        if (readF[0][j - 1].toString().equals(" "))
            list[0][j] = readF[0][j].toString().toInt() + list[0][j - 2].toString().toInt()
    for (i in 1 until readF.size)
        list[i][0] = readF[i][0].toString().toInt() + list[i - 1][0].toString().toInt()
    for (i in 1 until readF.size)
        for (j in 2..readF[0].length)
            if (readF[i - 1][j - 1].toString().equals(" ")) {

                list[i][j] = readF[i][j].toString().toInt() + min(
                    min(list[i][j - 2].toString().toInt(), list[i - 1][j].toString().toInt()),
                    min(list[i][j - 2].toString().toInt(), list[i - 1][j - 2].toString().toInt())
                )
            }
    return list[list.lastIndex][list[list.lastIndex].lastIndex]
}
