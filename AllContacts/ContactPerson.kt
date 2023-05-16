package contacts.AllContacts

class ContactPerson : Contact() {
    internal var surname: String = ""

    internal var birthDate = "[no data]"   //check

    internal var gender = "[no data]"      //check

    init {
        println("Enter the name:")
        name = readln()
        println("Enter the surname:")
        surname = readln()
        println("Enter the number:")
        phoneNumber = readln()
    }

    override fun printInfo() {
        println("Name: $name\nSurname: $surname\nBirth date: $birthDate\nGender: $gender")
        println("Number: $phoneNumber\nTime created: $timeCreated\nTime last edit: $timeEdit")
    }

    override fun toString() = "$name $surname"
}