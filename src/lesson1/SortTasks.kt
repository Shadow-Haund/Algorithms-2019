@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File
import java.io.IOException

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
 * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
 *
 * Пример:
 *
 * 01:15:19 PM
 * 07:26:57 AM
 * 10:00:03 AM
 * 07:56:14 PM
 * 01:15:19 PM
 * 12:40:31 AM
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 12:40:31 AM
 * 07:26:57 AM
 * 10:00:03 AM
 * 01:15:19 PM
 * 01:15:19 PM
 * 07:56:14 PM
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
//Сложность алгоритма O(n)
//Память O(n)
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    result = if ("AM" in str)
        if ("12" == str.substring(0..1))
            parts[1].toInt() * 60 + parts[2].removeRange(2..4).toInt()
        else
            parts[0].toInt() * 3600 + parts[1].toInt() * 60 + parts[2].removeRange(2..4).toInt()
    else
        if ("12" == str.substring(0..1))
            parts[0].toInt() * 3600 + parts[1].toInt() * 60 + parts[2].removeRange(2..4).toInt()
        else
            parts[0].toInt() * 3600 + 12 * 3600 + parts[1].toInt() * 60 + parts[2].removeRange(2..4).toInt()
    return result
}

fun sortTimes(inputName: String, outputName: String) {
    val readList = File(inputName).bufferedReader().readLines()
    val writeList = File(outputName).bufferedWriter()
    val rezList = mutableListOf<Pair<String, Int>>()
    for (i in readList.indices) {
        if (!readList[i].contains(Regex("""\s?\d{2}:\d{2}:\d{2}\s?[AMP]\s?""")))
            throw IOException()
    }
    for (i in readList.indices) {
        rezList.add(Pair(readList[i], timeStrToSeconds(readList[i])))

    }
    val s = rezList.sortedBy { it.second }
    for (element in s) {
        writeList.write(element.first + "\n")
    }
    writeList.close()
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortAddresses(inputName: String, outputName: String) {
    TODO()
}

//{
//    val readText = File(inputName).bufferedReader().readLines().sorted()
//    val rezL = mutableListOf<Pair<String, String>>()
//    val tryL = mutableListOf<String>()
//    val writeText = File(outputName).bufferedWriter()
//    var j = 1
//    var g = 0
//    for (i in readText.indices) {
//        if (readText[i].matches(Regex("""([А-ЯЁA-Z][а-яёa-z]+-?([А-ЯЁA-Z][а-яёa-z]+)? ){2}- ([А-ЯЁA-Z][а-яёa-z]+-?([А-ЯЁA-Z][а-яёa-z]+)? )\d+"""))) {
//            tryL.add(Pair(readText[i].split(" - ")[1], readText[i].split(" - ")[0]).toList().joinToString(", "))
//        } else throw IOException("Строка не соответствует требуемой")
//    }
//    tryL.sortedDescending()
//    for (i in tryL.indices)
//        rezL.add(Pair(tryL[i].split(", ")[0], tryL[i].split(", ")[1]))
//    while (j <= rezL.size - 1) {
//        if (rezL[g].first == rezL[j].first) {
//            rezL[g] = Pair(rezL[g].first, rezL[g].second + ", " + rezL[j].second)
//            rezL.remove(rezL[j])
//        } else {
//            g = j
//            j++
//        }
//    }
//    for (i in 0 until rezL.size) {
//        writeText.write(rezL[i].first + " - " + rezL[i].second + "\n")
//    }
//    writeText.close()
//

//}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */
//Сложность алгоритма O(n)
//Память O(n)
fun sortTemperatures(inputName: String, outputName: String) {
    val readFile = File(inputName).bufferedReader().readLines().toMutableList()
    val outFile = File(outputName).bufferedWriter()
    val sortedNums = mutableListOf<Double>()

    for (i in readFile.indices) {
        sortedNums.add(readFile[i].toDouble())
    }
    val rezFile = sortedNums.toMutableList().sorted().joinToString("\n")
    outFile.write(rezFile)
    outFile.close()
}
//File(outputName).writeText(File(inputName).bufferedReader().readLines().toDouble())
/*
val readFile = File(inputName).bufferedReader().readLines().toMutableList()
    val minus = mutableListOf<Double>()
    val plus = mutableListOf<Double>()

    for (i in readFile.indices) {
        if (readFile[i].contains('-'))
            minus.add(readFile[i].toDouble())
        else plus.add(readFile[i].toDouble())
    }
    plus.sortDescending()
    minus.sortDescending()
    val rezList = plus + minus
    (rezList.forEachIndexed { i, _ -> File(outputName).writeText(rezList[i].toString()) })
 */
/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */
fun sortSequence(inputName: String, outputName: String) {
TODO()
}
//    val readText = File(inputName).readBytes().toMutableList()
//    val writeText = File(outputName).bufferedWriter()
//    val sortBites = readText.sorted()
//    var maxInner = Pair(0, 0)
//    val ch = 0
//    var globalMaxInner = 0
//    for (i in sortBites.indices)
//        maxInner = (Pair(i, 0))



//    writeText.close()
/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    TODO()
}

