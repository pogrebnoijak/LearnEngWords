fun main(args: Array<String>) {
    if (args.size < 2)
        throw Exception("Wrong count arguments")
    val angl = when (args[0]) {
        "words" -> Translation()
        "irregular" -> Irregular()
        else -> throw Exception("Wrong arguments")
    }
    angl.work(args.drop(1))
}