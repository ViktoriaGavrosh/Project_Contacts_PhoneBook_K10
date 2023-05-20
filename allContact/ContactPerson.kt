package contacts.allContact

import java.time.LocalDate

class ContactPerson : Contact() {
    internal var surname: String = ""

    internal var birthDate = "[no data]"
        set(value) {
            field = checkBirthday(value)
        }

    internal var gender = "[no data]"
        set(value) {
            field = checkGender(value)
        }

    init {
        println("Enter the name:")
        name = readln()
        println("Enter the surname:")
        surname = readln()
        println("Enter the birth date:")
        birthDate = readln()
        println("Enter the gender (M, F):")
        gender = readln()
        println("Enter the number:")
        phoneNumber = readln()
    }

    override fun printInfo() {
        println("Name: $name\nSurname: $surname\nBirth date: $birthDate\nGender: $gender")
        println("Number: $phoneNumber\nTime created: $timeCreated\nTime last edit: $timeEdit")
    }

    private fun checkBirthday(stringDate: String): String {
        return try {
            LocalDate.parse(stringDate)
            stringDate
        } catch (e: Exception){
            println("Bad birth date!")
            "[no data]"
        }
    }

    private fun checkGender(gender: String): String {
        return if (gender != "M" && gender != "F") {
            println("Bad gender!")
            "[no data]"
        } else gender
    }

    override fun toString() = "$name $surname"
}