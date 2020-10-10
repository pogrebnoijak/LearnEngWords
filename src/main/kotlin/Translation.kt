
class Translation : Angl() {
    override fun work (files: List<String>) {
        super.work(files)
        cycle()
    }

    fun reverse() {
        dict.forEach{ it.allLine = mutableListOf(it.answer(), it.question()) }
    }
}