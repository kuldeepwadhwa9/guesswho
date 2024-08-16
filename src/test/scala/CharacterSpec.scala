import HairColour.Blonde
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class CharacterSpec extends AnyWordSpec with Matchers {
  "A Character" should {
    "have correct attributes" in {
      val character = Character("Alex", HairColour.Blonde, glasses = false, hat = false, "Male")

      character.name shouldBe "Alex"
      character.hairColour shouldBe Blonde
      character.glasses shouldBe false
      character.hat shouldBe false
      character.gender shouldBe "Male"
    }
  }
}
