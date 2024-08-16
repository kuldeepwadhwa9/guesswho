import scala.util.Random

object GameLogic {
  def filterCharacterBasedOnAttributes(character: List[Character], attributes: String, answer: Boolean): List[Character] = {
    attributes match {
      case "glasses" => character.filter(_.glasses == answer)
      case "hat" => character.filter(_.hat == answer)
      case "gender" => character.filter(_.gender == (if (answer) "Male" else "Female"))
      case _ => character
    }
  }

  def chooseCharacter(character: List[Character]): Character =
    Random.shuffle(character).head

}
