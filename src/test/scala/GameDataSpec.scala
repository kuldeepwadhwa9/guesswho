import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameDataSpec extends AnyWordSpec with Matchers {
  "GameData" should {
    "contain a list of characters" in {
      GameData.characters should have size 7
    }

      "contain names of characters" in {
        val characterNames = GameData.characters.map(_.name)
        characterNames should contain("Leo")
        characterNames should contain("Olivia")
        characterNames should contain("Jordan")
        characterNames should contain("Amu")
      }
    }
}
