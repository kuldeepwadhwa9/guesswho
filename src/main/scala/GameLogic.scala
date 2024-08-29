import scala.util.Random

object GameLogic {
  def filterCharacterBasedOnAttributes(character: List[Character], attributes: String, answer: Boolean): List[Character] = {
    attributes match {
      case "glasses" => character.filter(_.glasses == answer)
      case "hat" => character.filter(_.hat == answer)
      case "gender" => character.filter(_.gender == (if (answer) Gender.Male else Gender.Female))
      case _ => character
    }
  }

  def filterOutIncorrectlyGuessedCharacterByName(character: List[Character], name: String): List[Character] = {
    character.filterNot(_.name.equalsIgnoreCase(name))
  }

  def chooseCharacter(character: List[Character]): Character =
    Random.shuffle(character).head

}
