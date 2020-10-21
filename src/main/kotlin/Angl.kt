import java.io.FileReader
import java.util.regex.Pattern
import kotlin.random.Random

abstract class Angl(seed: Int = 0) {
    open var dict = mutableListOf<Words>()
    private var working = true
    private val rand = Random(seed)

    open fun work (files: List<String>) {
        files.forEach { file ->
            FileReader("temp/$file").use { it ->
                it.readLines().filter{ str -> !str.isEmpty() }.
                forEach { str -> dict.add(Words(str.split(Pattern.compile(" +")).toMutableList())) }
        } }
        if (this is Irregular) {
            dict = dict.take(takeWords) as MutableList<Words>
        }
    }

    private fun removeWord(wordNum: Int) {
        dict.removeAt(wordNum)
        if (dict.isEmpty())
            working = false
    }

    private fun teach() {
        val answer = task()
        when(readStrings()) {
            answer.first -> println("Yes")
            answer.first + "!" ->  {
                println("Yes")
                removeWord(answer.second)
            }
            "" -> println("No - ${answer.first}")
            "-exit" -> {
                working = false
            }
            "-rev" -> {
                if (this is Translation) {
                    reverse()
                }
            }
            "-del" -> {
                removeWord(answer.second)
            }
            "-дел" -> {
                removeWord(answer.second)
            }
            else -> println("No - ${answer.first}")
        }
    }

    private fun task(): Pair<String, Int> {
        val index = rand.nextInt(0, dict.size)
        print("${dict[index].question()}: ")
        return Pair(dict[index].answer(), index)
    }

    private fun checkWords() {
        val kol = when(this) {
            is Translation -> 2
            is Irregular -> 3
            else -> 0
        }
        val twoPart = dict.partition { it.size != kol }
        twoPart.first.forEach {
            println("remove   ${it.question()}: ${it.answer()}")
        }
        dict = twoPart.second.toMutableList()
    }

    private fun readStrings() = readLine()!!.split(Pattern.compile(" +")).filter { it != "" }.joinToString( " ")
    fun cycle() {
        checkWords()
        while (working) {
            teach()
        }
    }
}