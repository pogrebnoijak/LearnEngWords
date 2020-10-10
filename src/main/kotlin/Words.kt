data class Words(var allLine: MutableList<String>) {
    fun question() = allLine.first()
    fun answer() = allLine.drop(1).joinToString( " ")
    var size = allLine.size
}