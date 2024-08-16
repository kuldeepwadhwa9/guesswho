import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameLogicSpec extends AnyWordSpec with Matchers {

  val characters: List[Character] = List(
    Character("Amu", HairColour.Black, true, false, "Female"),
    Character("David", HairColour.Blonde, false, true, "Male"),
    Character("Leo", HairColour.Gray, false, false, "Male"),
    Character("Gabe", HairColour.Black, false, true, "Male")
  )

"GameLogic" should {
  "select the characters based on yes/no question" in {
    val remainingCharacters = GameLogic.filterCharacterBasedOnAttributes(characters, "hat", true)
    remainingCharacters should have size 2
    remainingCharacters.head.name shouldBe "David"
    remainingCharacters(1).name shouldBe "Gabe"
  }


  "select random characters from the list" in {

    val selectedCharacters = GameLogic.chooseCharacter(characters)
    characters should contain (selectedCharacters)
  }

  "select characters based on attributes" in {

    val selectCharacterByAttribute = GameLogic.filterCharacterBasedOnAttributes(characters, "hat", true)

    selectCharacterByAttribute should equal(List(Character("David", HairColour.Blonde, false, true, "Male"), Character("Gabe", HairColour.Black, false, true, "Male")))
  }
}
}
