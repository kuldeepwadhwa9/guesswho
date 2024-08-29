import scala.annotation.tailrec
import scala.io.StdIn.readLine

object GuessWhoGame extends App {

  private val characters = GameData.characters
  private val chosenCharacter = GameLogic.chooseCharacter(characters)

  println("Welcome to Guess Who Game")
  println("Guess the character by asking questions based on 'hat, glasses, gender', or guess the name directly.")
  println("Available characters:")
  characters.map(_.name).foreach(println)

  @tailrec
  private def gameLoop(remainingCharacters: List[Character]): Unit = {
    if (remainingCharacters.size > 1) {

      println("\nRemaining characters: " + remainingCharacters.map(_.name).mkString(", "))
      println("Enter 'hat', 'glasses', 'gender' to ask about attributes, or type 'name' to guess the character's name:")

      val input = readLine().trim.toLowerCase

    input match {
        case "name" =>
          val guessedName = askForCharacterGuessByName()
          if (guessedName.equalsIgnoreCase(chosenCharacter.name)){
            println(s"Congratulations You guessed correctly. The character is ${chosenCharacter.name}.")
          } else {
            println(s"Sorry :(, $guessedName is not the correct character. Keep playing!")
            gameLoop(GameLogic.filterOutIncorrectlyGuessedCharacterByName(remainingCharacters, guessedName))
          }
        case "hat" =>
          val answer = askYesNoQuestion("Does the character wear a hat?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "hat", answer))
        case "glasses" =>
          val answer = askYesNoQuestion("Does the character wear glasses?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "glasses", answer))
        case "gender" =>
          val gender = askGenderQuestion("Is the character male or female?")
          gameLoop(GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "gender", gender == "male"))
        case _ =>
          println("Unrecognized input. Please try again.")
          gameLoop(remainingCharacters)
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

  gameLoop(characters)
}
