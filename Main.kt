import kotlin.math.PI
import kotlin.random.Random

/**
 * Complete the code to make the program print "Mary is 20 years old" to standard output:
 */
fun nameAge() {
    val name = "Mary"
    val age = 20
    println("$name is $age years old")
}

/**
 * Explicitly declare the correct type for each variable:
 */
fun types() {
    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'

    println("$a $b $c $d $e $f")
}

/**
 * You have a list of “green” numbers and a list of “red” numbers. Complete the code to print how many numbers there are in total.
 */
fun countNumbers() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalCount = greenNumbers.count() + redNumbers.count()
    println(totalCount)
}

/**
 * You have a set of protocols supported by your server. A user requests to use a particular protocol. Complete the program to check whether the requested protocol
 * is supported or not (isSupported must be a Boolean value).
 */
fun protocols() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * Define a map that relates integer numbers from 1 to 3 to their corresponding spelling. Use this map to spell the given number.
 */
fun translator() {
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
}

/**
 * se the equality operator (==) to compare the dice results.
 */
fun comparison() {
    val firstResult = Random.nextInt(6)
    val secondResult = Random.nextInt(6)
    if (firstResult == secondResult)
        println("You win :)")
    else
        println("You lose :(")
}

/**
 * Using a when expression, update the following program so that it prints the corresponding actions when you input the names of game console buttons.
 */
fun buttonAction() {
    val button = "A"
    println(
        when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    )
}

/**
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally, replacing any number divisible by three with the word
 * "fizz", and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
 */
fun fizzBuzz() {
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }
}

/**
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 */
fun firstLetter() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (w in words) {
        if (w.startsWith("l"))
            println(w)
    }
}

/**
 * helper function for function function
 */
fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

/**
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 */
fun function() {
    println(circleArea(2))
}

/**
 * You have a list of actions supported by a web service, a common prefix for all requests, and an ID of a particular resource. To request an action title over the
 * resource with ID: 5, you need to create the following URL: https://example.com/book-info/5/title. Use a lambda expression to create a list of URLs from the list of
 * actions.
 */
fun webService() {
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println(urls)
}


data class Employee(val name: String, var salary: Int)

/**
 * Define a data class Employee with two properties: one for a name, and another for a salary. Make sure that the property for salary is mutable, otherwise you won’t
 * get a salary boost at the end of the year! The main function demonstrates how you can use this data class.
 */
fun salaryKick() {
    val emp = Employee("Mary", 20)
    println(emp)
    emp.salary += 10
    println(emp)
}


/**
 * main function – executes all functions.
 */
fun main() {
    nameAge()
    types()
    countNumbers()
    protocols()
    translator()
    comparison()
    buttonAction()
    fizzBuzz()
    firstLetter()
    function()
    webService()
    salaryKick()
}