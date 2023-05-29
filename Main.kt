package contacts

import contacts.allContact.Contact
import contacts.allContact.ContactOrg
import contacts.allContact.ContactPerson
import java.io.File

fun main(args: Array<String>) {
    val wayToFile = try {
        args.first()
    } catch (e: Exception) {
        "C:\\a\\phonebook.txt"
    }
    val file = File(wayToFile)
    println("open ${file.name}\n")
    val phoneBook = if (file.exists()) read(file) else PhoneBook(file)
    phoneBook.showMenu()
}

internal fun save(file: File, book: MutableList<Contact>) {
    val bookString = bookToString(book)
    file.writeText(bookString)
}

internal fun read(file: File): PhoneBook {
    val bookString = file.readText()
    return toPhoneBook(bookString, file)
}

private fun bookToString(book: MutableList<Contact>): String {
    var bookString= "{\"book\": ["
    for (i in book.indices) {
        bookString += book[i].serializeString()
        if (i != book.lastIndex) bookString += ", "
    }
    return "$bookString]}"
}

private fun toPhoneBook(text: String, file: File): PhoneBook {
    val book = mutableListOf<Contact>()
    val listText = text.split(Regex(" \\["))
    if (listText[1].length < 3) return PhoneBook(file)
    val contacts = listText[1].substring(0, listText[1].lastIndex - 1).split("}, {").toMutableList()
    for (i in contacts) {
        val cont = i.replace(Regex("[{}]"), "").split(", ")
        val j = if (cont.size > 5) ContactPerson() else ContactOrg()
        j.deserialize(cont)
        book.add(j)
    }
    val phoneBook = PhoneBook(file)
    phoneBook.book = book
    return phoneBook
}