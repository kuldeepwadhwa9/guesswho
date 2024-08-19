import scala.annotation.tailrec
import scala.io.StdIn.readLine

object GuessWhoGame extends App {

  private val characters = GameData.characters
  private val chosenCharacter = GameLogic.chooseCharacter(characters)

  println("Welcome to Guess Who Game")
  println("Guess the character by asking questions based on 'hat, glasses, gender'")
  println("Available characters:")
  characters.map(_.name).foreach(println)


  @tailrec
  private def gameLoop(remainingCharacters: List[Character]): Unit = {
    if (remainingCharacters.size > 1) {
      println("\nRemaining characters: " + remainingCharacters.map(_.name).mkString(", "))
      println("Enter an attribute to guess the character (options: 'hat', 'glasses', 'gender'):")

      val question = readLine().trim.toLowerCase
      val updatedCharacters = question match {
        case "hat" =>
          val answer = askYesNoQuestion("Does the character wear a hat?")
          GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "hat", answer)
        case "glasses" =>
          val answer = askYesNoQuestion("Does the character wear glasses?")
          GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "glasses", answer)
        case "gender" =>
          val gender = askGenderQuestion("Is the character male or female?")
          GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "gender", gender == "male")
        case _ =>
          println("Unrecognized attribute. Please try again.")
          remainingCharacters
      }

      gameLoop(updatedCharacters)
    } else {
      println(s"\nYour guess: ${remainingCharacters.head.name}")
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


  gameLoop(characters)
}
