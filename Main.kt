package contacts

fun main() {
    val person = Contact()
    val phoneBook = PhoneBook()
    phoneBook.book.add(person)
    println("A Phone Book with a single record created!")
}