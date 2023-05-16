package contacts.AllContacts

class ContactOrg : Contact() {
    internal var address: String = ""

    init {
        println("Enter the organization name:")
        name = readln()
        println("Enter the address:")
        address = readln()
        println("Enter the number:")
        phoneNumber = readln()
    }

    override fun printInfo() {
        println("Organization name: $name\nAddress: $address")
        println("Number: $phoneNumber\nTime created: $timeCreated\nTime last edit: $timeEdit")
    }

    override fun toString() = name

}