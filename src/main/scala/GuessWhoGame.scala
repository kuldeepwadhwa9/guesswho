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
  def gameLoop(remainingCharacters: List[Character]): Unit = {
    if (remainingCharacters.size > 1) {

      println(s"\nRemaining characters: ${remainingCharacters.map(_.name).mkString(", ")}")
      println("\nEnter an attribute for example, '(hat, glasses, gender)' to guess the character:")

      val question: String = readLine()
      println(s"Raw input: $question")
      val answer: Boolean = question.contains("yes")

      val updatedCharacters = if (question.contains("hat")) {
        GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "hat", answer)
      } else if (question.contains("glasses")) {
        GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "glasses", answer)
      }
      else if (question.contains("gender")) {
        println("Enter the gender Male/ Female")
        val genderQuestion: String = readLine()
        println(s"Raw input: $genderQuestion")
        val answer: Boolean = if (genderQuestion.contains("Male")) true else false
        GameLogic.filterCharacterBasedOnAttributes(remainingCharacters, "gender", answer)
      }
      else {
        println("Question not recognized. Please ask about 'hat', 'glasses', or 'gender'.")
        remainingCharacters
      }
      gameLoop(updatedCharacters)
    }
    else
    {
      println(s"\nYour guess: ${remainingCharacters.head.name}")
      if (remainingCharacters.head == chosenCharacter) {
        println("Congratulations! Your guess was correct.")
      } else {
        println(s"Sorry, the correct character was ${chosenCharacter.name}.")
      }
    }
  }

  gameLoop(characters)
}
