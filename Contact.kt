package contacts

class Contact {
    private val name: String
    private val surname: String
    private val phoneNumber: String
    init {
        println("Enter the name of the person:")
        name = readln()
        println("Enter the surname of the person:")
        surname = readln()
        println("Enter the number:")
        phoneNumber = readln()
        println("A record created!")
    }
}