import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameLogicSpec extends AnyWordSpec with Matchers {

  val characters: List[Character] = List(
    Character("Amu", HairColour.Black, glasses = true, hat = false, Gender.Female),
    Character("David", HairColour.Blonde, glasses = false, hat = true, Gender.Male),
    Character("Leo", HairColour.Gray, glasses = false, hat = false, Gender.Male),
    Character("Gabe", HairColour.Black, glasses = false, hat = true, Gender.Male)
  )

"GameLogic" should {
  "select the characters based on yes/no question" in {
    val remainingCharacters = GameLogic.filterCharacterBasedOnAttributes(characters, "hat", answer = true)
    remainingCharacters should have size 2
    remainingCharacters.head.name shouldBe "David"
    remainingCharacters(1).name shouldBe "Gabe"
  }


  "select random characters from the list" in {

    val selectedCharacters = GameLogic.chooseCharacter(characters)
    characters should contain (selectedCharacters)
  }

  "select characters based on attributes" in {

    val selectCharacterByAttribute = GameLogic.filterCharacterBasedOnAttributes(characters, "hat", answer = true)

    selectCharacterByAttribute should equal(List(
      Character("David", HairColour.Blonde, glasses = false, hat = true, Gender.Male),
      Character("Gabe", HairColour.Black, glasses = false, hat = true, Gender.Male)))
  }

  "filterOut the name of the character" in {
    val filterOutName = GameLogic.filterOutIncorrectlyGuessedCharacterByName(characters, "Leo")

    filterOutName should equal(List(
      Character("Amu", HairColour.Black, glasses = true, hat = false, Gender.Female),
      Character("David", HairColour.Blonde, glasses = false, hat = true, Gender.Male),
      Character("Gabe", HairColour.Black, glasses = false, hat = true, Gender.Male)
    ))
  }
}
}
