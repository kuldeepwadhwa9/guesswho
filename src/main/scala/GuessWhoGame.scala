import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.Random

object GuessWhoGame extends App {

  private val characters = GameData.characters
  private val chosenCharacter = GameLogic.chooseCharacter(characters)
  private val maximumHints = 2

  println("Welcome to Guess Who Game")
  println("Guess the character by asking questions based on 'hat, glasses, gender', or guess the name directly.")
  println(s"You can also use hints to remove a random incorrect character from the board. You have $maximumHints hints available.")
  println("Available characters:")
  characters.map(_.name).foreach(println)

   def useHint(remainingCharacters: List[Character]): List[Character] = {
    val incorrectCharacter = remainingCharacters.filterNot(_ == chosenCharacter)
    val characterToRemove = Random.shuffle(incorrectCharacter).head
    println(s"HINT: Removing ${characterToRemove.name} from the game board")
    remainingCharacters.filterNot(_ == characterToRemove)
  }

  @tailrec
  private def gameLoop(remainingCharacters: List[Character], remainingHints: Int): Unit = {
    if (remainingCharacters.size > 1) {

      println("\nRemaining characters: " + remainingCharacters.map(_.name).mkString(", "))
      println("Enter 'hat', 'glasses', 'gender' to ask about attributes, or type 'name' to guess the character's name, or 'hint' to use a hint:")

      val input = readLine().trim.toLowerCase

    input match {
        case "name" =>
          val guessedName = askForCharacterGuessByName()
          if (guessedName.equalsIgnoreCase(chosenCharacter.name)){
            println(s"Congratulations! You guessed correctly. The character is ${chosenCharacter.name}.")
          } else {
            println(s"Sorry :(, $guessedName is not the correct character. Keep playing!")
            gameLoop(GameLogic.filterOutIncorrectlyGuessedCharacterByName(remainingCharacters, guessedName), remainingHints)
          }
        case "hat" =>
          val answer = askYesNoQuestion("Does the character wear a hat?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "hat", answer), remainingHints)
        case "glasses" =>
          val answer = askYesNoQuestion("Does the character wear glasses?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "glasses", answer), remainingHints)
        case "gender" =>
          val gender = askGenderQuestion("Is the character male or female?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "gender", gender == "male"), remainingHints)
        case "hint" =>
          if (remainingHints > 0 && remainingCharacters.size > 2) {
            val updatedCharacters = useHint(remainingCharacters)
            println(s"Hint used! You have ${remainingHints - 1} hints left.")
            gameLoop(updatedCharacters, remainingHints - 1)
          } else if (remainingHints == 0) {
            println("Sorry, you have no hints left!")
            gameLoop(remainingCharacters, remainingHints)
          } else {
            println("There are not more characters left to use as a hint!")
            gameLoop(remainingCharacters, remainingHints)
          }
        case _ =>
          println("Unrecognized input. Please try again.")
          gameLoop(remainingCharacters, remainingHints)
      }
    } else {
      println(s"\nYour input: ${remainingCharacters.head.name}")
      if (remainingCharacters.head == chosenCharacter) {
        println("Congratulations! Your guess was correct.")
      } else {
        println(s"Sorry, the correct character was ${chosenCharacter.name}.")
      }
    }
  }

  private def askYesNoQuestion(question: String): Boolean = {
    println(question + " (yes/no)")
    readLine().trim.toLowerCase == "yes"
  }

  private def askGenderQuestion(question: String): String = {
    println(question + " (male/female)")
    readLine().trim.toLowerCase
  }

  private def askForCharacterGuessByName(): String = {
    println("Enter your guess for the character's name:")
    readLine().trim
  }

  gameLoop(characters, maximumHints)
}
