package contacts

class Contact {
    internal var name: String = ""

    internal var surname: String = ""

    internal var phoneNumber: String = ""
        set(value) {
            field = checkNumber(value)
        }

    init {
        println("Enter the name:")
        name = readln()
        println("Enter the surname:")
        surname = readln()
        println("Enter the number:")
        phoneNumber = readln()
    }

    private fun checkNumber(number: String): String {
        val listNum = number.split(Regex("[ \\-]"))
        val regex1 = Regex("\\+?\\(.+\\)")
        var countBrackets = 0
        try {
            for (i in listNum.indices) {
                val regex2 = if (i == 0) Regex("\\+?\\(\\w+\\)|\\+?\\w+") else Regex("\\(\\w{2,}\\)|\\w{2,}")
                if (regex1.matches(listNum[i])) {
                    countBrackets++
                    if (i > 1 || countBrackets > 1) throw Exception()
                }
                if (!regex2.matches(listNum[i])) throw Exception()
            }
        } catch (e: Exception) {
            println("Wrong number format!")
            return "[no number]"
        }
        return number
    }

    override fun toString() = "$name $surname, $phoneNumber"
}