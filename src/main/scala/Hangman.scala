/**
 * Hangman Game
 *
 * This Scala program implements a simple Hangman game. The game randomly selects a word from a provided
 * text file containing a list of words, and the player must guess the letters in the word to complete it
 * before running out of attempts.
 *
 * The program defines a Hangman object with the following main functionalities:
 *
 * - `randomWord(fileName: String): String`: Reads words from the given file and returns a random word that
 *   meets certain criteria, excluding words containing special characters and being shorter than 4 characters.
 *
 * - `main(args: Array[String]): Unit`: The main entry point of the game. It reads a list of words from a file,
 *   selects a random word, and initiates the game loop where the player guesses letters and the state of the word
 *   is updated accordingly. The game loop continues until the player guesses the entire word or runs out of attempts.
 *   At the end of the game, the program provides feedback to the player on whether they guessed the word correctly
 *   or not.
 *
 * The game maintains variables to keep track of the player's progress and status, such as the word to be guessed,
 * the player's guessed letters, the current state of the word, the remaining attempts, and flags indicating whether
 * the word has been guessed or not.
 *
 * Note: This code assumes that the 'words.txt' file containing the list of words is located at the given path.
 * The program utilizes Scala's standard input/output for user interaction.
 *
 * @author Diwash Mainali
 * @version 1.0
 * @since 12/08/2023
 */
import scala.io.Source
import scala.util.Random

object Hangman {
  val random = new Random()

  /**
   * Generates a random word from the words listed in the provided file.
   *
   * @param fileName The path to the file containing the list of words.
   * @return A valid word that meets certain criteria (no special characters, longer than 3 characters).
   */
  def randomWord(fileName: String): String = {
    val source = Source.fromFile(fileName)
    val words = source.getLines().flatMap(line => line.split("\\s+")).toList
    val word = words(random.nextInt(words.length))
    source.close()

    val regexPattern = """.*[0-9@#$%^&*()\-''].*""".r
    if (regexPattern.findAllMatchIn(word).isEmpty && word.length > 3) word else randomWord(fileName)
  }

  def main(args: Array[String]): Unit = {
    val filename = "E:\\Scala Projects\\Hangman\\src\\main\\resources\\words.txt"
    val word = randomWord(filename).toLowerCase()
    val wordLength = word.length
    println(word)
    var answer = Array.fill(wordLength)("_")
    println(answer.mkString("Array(", ", ", ")"))

    var score = 5
    var guessedLetters = Array[Char]()
    var isWordGuessed = false
    var isWordPresent = false

    // Game loop: Continue until the word is guessed or attempts run out
    while (score > 0 && !isWordGuessed) {
      println("Guess a letter: ")
      val guessedLetter = scala.io.StdIn.readChar()
      if (guessedLetters.contains(guessedLetter)) {
        println(s"You already guessed '$guessedLetter'.")
      } else {
        guessedLetters = guessedLetters :+ guessedLetter
        if (word.contains(guessedLetter)) {
          isWordPresent = true
          for (i <- word.indices) {
            if (word(i) == guessedLetter) {
              answer(i) = guessedLetter.toString
            }
          }
          if (!answer.contains("_")) {
            isWordGuessed = true
          }
        } else {
          score -= 1
          println(s"Wrong guess! You have $score attempts left.")
          if (score == 0) {
            println(s"Sorry, you've run out of attempts. The word was: $word")
          }
        }
      }
      println(answer.mkString("Array(", ", ", ")"))
    }

    if (isWordGuessed) {
      println(s"Congratulations! You guessed the word: $word")
    }
  }
}
