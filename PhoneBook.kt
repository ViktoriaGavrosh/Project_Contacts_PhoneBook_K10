package contacts

import contacts.allContact.Contact
import contacts.allContact.ContactOrg
import contacts.allContact.ContactPerson
import java.time.LocalDateTime

class PhoneBook {
    private val book = mutableListOf<Contact>()

    internal fun showMenu() {
        while (true) {
            println("Enter action (add, remove, edit, count, info, exit):")
            when(readln()) {
                "add" -> addContact()
                "remove" -> removeContact()
                "edit" -> editContact()
                "count" -> println("The Phone Book has ${book.size} records.")
                "info" -> showInfo()
                "exit" -> break
            }
            println("")
        }
    }

    private fun addContact() {
        println("Enter the type (person, organization):")
        if (readln() == "person") book.add(ContactPerson()) else book.add(ContactOrg())
        println("The record added.")
    }

    private fun showInfo() {
        showBook()
        println("Enter index to show info:")
        book[readln().toInt() - 1].printInfo()
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
        if (book[numberContact] is ContactPerson) editContactPerson(numberContact) else editContactOrg(numberContact)
        println("The record updated!")
        book[numberContact].timeEdit = LocalDateTime.now().toString()
    }

    private fun editContactPerson(numberContact: Int) {
        val person = book[numberContact] as ContactPerson
        println("Select a field (name, surname, birth, gender, number):")
        val field = readln()
        println("Enter $field:")
        when (field) {
            "name" -> person.name = readln()
            "surname" -> person.surname = readln()
            "birth" -> person.birthDate = readln()
            "gender" -> person.gender = readln()
            "number" -> person.phoneNumber = readln()
        }
        book[numberContact] = person
    }

    private fun editContactOrg(numberContact: Int) {
        val org = book[numberContact] as ContactOrg
        println("Select a field (address, number):")
        val field = readln()
        println("Enter $field:")
        if (field == "address") org.address = readln() else org.phoneNumber = readln()
        book[numberContact] = org
    }

    private fun showBook() {
        for (i in book.indices) println("${i + 1}. ${book[i]}")
    }
}