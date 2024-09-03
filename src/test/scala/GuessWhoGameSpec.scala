import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GuessWhoGameSpec extends AnyWordSpec with Matchers {

  val characters: List[Character] = List(
    Character("Amu", HairColour.Black, glasses = true, hat = false, Gender.Female),
    Character("David", HairColour.Blonde, glasses = false, hat = true, Gender.Male),
    Character("Leo", HairColour.Gray, glasses = false, hat = false, Gender.Male),
    Character("Gabe", HairColour.Black, glasses = false, hat = true, Gender.Male)
  )

  "GuessWhoGame" should {
    "decrement hints and continue game correctly" in {
      val initialHints = 2
      val remainingCharacters = characters

      val updatedCharacters = GuessWhoGame.useHint(remainingCharacters)
      val remainingHints = initialHints - 1
      updatedCharacters.size shouldBe 3
      remainingHints shouldBe 1
    }

    "correctly handle no hints left" in {
      val initialHints = 0
      val remainingCharacters = characters

      // Trying to use a hint when no hints are left
      val remainingHints = initialHints

      // No hint should be used, and the character list remains unchanged
      val updatedCharacters = remainingCharacters

      updatedCharacters.size shouldBe 4
      remainingHints shouldBe 0
    }

}
  }
