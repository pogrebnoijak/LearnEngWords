class Irregular : Angl() {
    val takeWords = 40 // you may change that
    override fun work (files: List<String>) {
        super.work(files)
        cycle()
    }
}