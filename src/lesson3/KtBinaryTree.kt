package lesson3

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException
import kotlin.math.max

// Attention: comparable supported but comparator is not
open class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0

    private class Node<T>(var value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

    override fun checkInvariant(): Boolean =
        root?.let { checkInvariant(it) } ?: true

    override fun height(): Int = height(root)

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    private fun height(node: Node<T>?): Int {
        if (node == null) return 0
        return 1 + max(height(node.left), height(node.right))
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    override fun remove(element: T): Boolean {

        if (find(element) == null || root == null) return false

        removeNode(root, element)
        size--

        return true
    }

    private fun removeNode(node: Node<T>?, value: T): Node<T>? {
        //TODO
        throw NotImplementedException()
    }


    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
        root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator internal constructor() : MutableIterator<T> {

        private var currentNode: Node<T>? = null
        private val stack = ArrayDeque<Node<T>>()

        init {
            if (root != null) fillingStack(root!!)
        }


        private fun fillingStack(node: Node<T>) {
            if (node == null) throw NoSuchElementException()
            if (node.left != null) fillingStack(node.left!!)
            stack.add(node)
            if (node.right != null) fillingStack(node.right!!)
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         *
         */

//      Сложность алгоритма O(log n) где n - число элементов в дереве
//      Память O(1)
        override fun hasNext(): Boolean {
            return stack.peek() != null
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
//      Сложность алгоритма O(log n) где n - число элементов в дереве
//      Память O(1)

        override fun next(): T {
            currentNode = stack.pop()
            return currentNode!!.value
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        override fun remove() {
            // TODO
            throw NotImplementedError()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    inner class SubTree<T : Comparable<T>>(
        private val tree: KtBinaryTree<T>,
        private val from: T?,
        private val to: T?
    ) : KtBinaryTree<T>() {

        override fun add(element: T): Boolean {
            if (!inRange(element)) throw IllegalArgumentException()
            else return tree.add(element)
        }

        override fun contains(element: T): Boolean {
            return inRange(element) && tree.contains(element)
        }

        override var size = 0
            get() = size(tree)

        private fun findSize(node: Node<T>?): Int {
            var size = 0
            if (node == null) return size
            for (i in tree)
                if (inRange(node.value)) size++
            if (inRange(node.left!!.value)) size++
            if (inRange(node.right!!.value)) size
            return size
        }

        fun size(tree: KtBinaryTree<T>): Int {
            var size = 0
            for (value in tree) {
                if (inRange(value)) size++
            }
            return size
        }

        private fun inRange(element: T): Boolean =
            ((from == null || element >= from) && (to == null || element < to))
    }

    /**
     * Найти множество всех элементов в диапазоне [fromElement, toElement)
     * Очень сложная
     */
//      Сложность алгоритма O(n) где n - элементы главного дерева
//      Память O(n) где n - элементы под дерева дерева
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        return SubTree(this, fromElement, toElement)
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
//      Сложность алгоритма O(n) где n - элементы главного дерева
//      Память O(n) где n - элементы под дерева дерева
    override fun headSet(toElement: T): SortedSet<T> {
        return SubTree(this, null, toElement)
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
//      Сложность алгоритма O(n) где n - элементы главного дерева
//      Память O(n) где n - элементы под дерева дерева
    override fun tailSet(fromElement: T): SortedSet<T> {
        return SubTree(this, fromElement, null)
    }

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
