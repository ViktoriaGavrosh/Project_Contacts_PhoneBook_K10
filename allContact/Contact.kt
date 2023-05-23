package contacts.allContact

import java.time.LocalDateTime

abstract class Contact {
    internal var name: String = ""
    internal var phoneNumber: String = ""
        set(value) {
            field = checkNumber(value)
        }

    internal var timeCreated = LocalDateTime.now().toString().substring(0, 16)

    internal var timeEdit = LocalDateTime.now().toString().substring(0, 16)
        set(value) {
            field = value.substring(0, 16)
        }

    abstract fun printInfo()

    abstract fun showFieldsToChange(): String

    abstract fun checkAllFields(regex: Regex): Boolean

    internal open fun serializeString(): String {
        val res = "{\"name\": \"$name\", \"phoneNumber\": \"$phoneNumber\", "
        return "$res\"timeCreated\": \"$timeCreated\", \"timeEdit\": \"$timeEdit\", "
    }

    internal fun deserialize(listFields: List<String>) {
        for (i in listFields) {
            val field = i.substring(1,i.lastIndex - 1).split("\": \"")
            changeField(field[0], field[1])
        }
    }

    internal open fun changeField(field: String, value: String) {
        when (field) {
            "name" -> name = value
            "number" -> phoneNumber = value
            "timeCreated" -> timeCreated = value
            "timeEdit" -> timeEdit = value
        }
    }

    abstract fun editContact()

    private fun checkNumber(number: String): String {
        val listNum = number.split(Regex("[ \\-]"))
        val regex1 = Regex("\\+?\\(.+\\)")     //it works, but +(23) - true!
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

}