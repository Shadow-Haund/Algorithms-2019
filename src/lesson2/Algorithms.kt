@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.io.File
import kotlin.math.max
import kotlin.math.sqrt


/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */

//Сложность алгоритма O(n)
//Память O(n)
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    val readF = File(inputName).bufferedReader().readLines()
    var maxCurSum = 0
    var maxGlSum = maxGlobalSum(0, 0, 0)
    var firstI = 0
    var secondI = 1
    val listOfPAndM = mutableListOf<Int>()
    for (i in 1 until readF.size) {
        listOfPAndM.add(i - 1, readF[i].toInt() - readF[i - 1].toInt())
    }
    for (i in listOfPAndM.indices) {
        maxCurSum = max(listOfPAndM[i], maxCurSum + listOfPAndM[i])

        if (maxCurSum > maxGlSum.sum) {
            maxGlSum = maxGlobalSum(maxCurSum, firstI, secondI)
        }
        if (maxCurSum > 0) secondI++
        if (maxCurSum < 0) {
            maxCurSum = 0
            firstI = i + 1
            secondI = firstI + 1
        }
    }

    return Pair(maxGlSum.fInd + 1, maxGlSum.sInd + 1)
}

class maxGlobalSum(sum: Int, fInd: Int, sInd: Int) {
    var sum = sum
    var fInd = fInd
    var sInd = sInd
}
/*
for (i in readF.indices) {
        for (j in i + 1 until readF.size) {
            var subS = readF.subList(readF[i].toInt(), readF[j].toInt())
            for (k in subS.indices) {
                curSum += subS[k].toInt
            }

            maxGlSum = max(maxCurSum, maxCurSum + subS)

        }
    }

*/
/*
val readF = File(inputName).bufferedReader().readLines()
    readF.forEachIndexed { i, _ ->  readF[i].toInt()}
    var maxCurSum = 0
    var maxGlSum = 0
    var firstI = 0
    var lastI = 0
    for (i in readF.indices){

        maxCurSum = max(readF[i], maxCurSum + readF[i])
    }



*/
/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 *
 * Общий комментарий: решение из Википедии для этой задачи принимается,
 * но приветствуется попытка решить её самостоятельно.
 */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    TODO()
}
/*

    val men = mutableListOf<Int>()
    var step = choiceInterval - 1
    for (i in 0..menNumber)
        men.add(i)
    for (i in menNumber..0)
        for (menNumber ) {
            men.remove(men[step])
            step = (step + choiceInterval) % men[i]
        }
    return men.sum()

if (menNumber == 1)
        /return 0
    return if (menNumber > 1) (josephTask(menNumber - 1, choiceInterval) + choiceInterval - 1) % menNumber + 1 else -1

    StackOverflowError
    Падает когда доходит до for(i in 0..20) на каком-то из шагов
*/

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
//Сложность алгоритма O(n)
//Память O(n)
fun longestCommonSubstring(first: String, second: String): String {
    val rezL = List(first.length + 1) { IntArray(second.length + 1) }
    var max = 0
    var k = 0
    for (i in 0..first.length)
        for (j in 0..second.length) {
            if (i != 0 && j != 0 && first[i - 1] == second[j - 1])
                rezL[i][j] = rezL[i - 1][j - 1] + 1
            else
                rezL[i][j] = 0
            if (rezL[i][j] > max) {
                max = rezL[i][j]
                k = i
            }
        }
    return if (max == 0)
        ""
    else
        first.substring(k - max, k)
}

//var lenght = 0
//if (first.length > second.length)
//while (lenght < second.length - 1) {
//    for (i in first.indices)
//        if (second[lenght] in first) {
//            rezString += second[i]
//            lenght++
//        } else lenght++
//    if (second[i - 1] in first && second[i] !in first) {
//        lenght = 0
//    }
//}
//else while (lenght < first.length - 1) {
//    for (i in second.indices)
//        if (first[lenght] in second) {
//            rezString += first[i]
//            lenght ++
//        } else lenght++
//}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
//Сложность алгоритма O(n)
//Память O(1)
fun calcPrimesNumber(limit: Int): Int {
    var num = 0
    if (limit <= 1)
        return 0
    for (i in 2..limit)
        if (ifIsPrime(i))
            num++
    return num
}

fun ifIsPrime(n: Int): Boolean {
    val s = sqrt(n.toDouble()).toInt()
    for (i in 2..s) {
        if (n % i == 0)
            return false
    }
    return true
}
/*

val rezL = mutableListOf<Int>()
    if (limit <= 1)
        return 0
    for (i in 0..limit)
        if (i == 2 || i != 1 && i % 2 != 0)
            rezL.add(i)
    for (i in 3 until rezL.size) {
        if (i == rezL.size) break
        for (j in 1..i / 2 + 1)
            while (2 * rezL[j] <= rezL[i])
                if (rezL[i] % rezL[j] == 0)
                    rezL.remove(rezL[i])
                else break
    }

    return rezL.size
}

*/
/*
while ((2 * i + 1) * (2 * i + 1) <= (2 * limit + 1) * 2) {
        if (rezL[i] == 1) {
            while (j <= limit) {
                rezL[j] = 0
                j += 2 * i + 1
            }
        }
        i++

         var dev = 3
        var numOfDev = 0
        while (dev <= rezL[i]) {
            dev += if (rezL[i] % dev == 0) {
                numOfDev++
                2
            } else {
                2
            }
        }
        if (numOfDev != 1)
            rezL.remove(rezL[i])
    }
    }
 */


/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    TODO()
}