package contacts

class PhoneBook {
    private val book = mutableListOf<Contact>()

    internal fun showMenu() {
        while (true) {
            println("Enter action (add, remove, edit, count, list, exit):")
            when(readln()) {
                "add" -> addContact()
                "remove" -> removeContact()
                "edit" -> editContact()
                "count" -> println("The Phone Book has ${book.size} records.")
                "list" -> showBook()
                "exit" -> break
            }
        }
    }

    private fun addContact() {
        book.add(Contact())
        println("The record added.")
    }

    private fun removeContact() {
        if (book.size == 0) {
            println("No records to remove!")
            return
        }
        showBook()
        println("Select a record:")
        book.removeAt(readln().toInt() - 1)
        println("The record removed!")
    }

    private fun editContact() {
        if (book.size == 0) {
            println("No records to edit!")
            return
        }
        showBook()
        println("Select a record:")
        val numberContact = readln().toInt() - 1
        println("Select a field (name, surname, number):")
        val field = readln()
        println("Enter $field:")
        when (field) {
            "name" -> book[numberContact].name = readln()
            "surname" -> book[numberContact].surname = readln()
            "number" -> book[numberContact].phoneNumber = readln()
        }
        println("The record updated!")
    }

    private fun showBook() {
        for (i in book.indices) println("${i + 1}. ${book[i]}")
    }
}