
sealed trait HairColour

object HairColour extends Enumeration {
case object Black extends HairColour
case object Red extends HairColour
case object Gray extends HairColour
case object Blonde extends HairColour
}

case class Character (
    name: String,
    hairColour: HairColour,
    glasses: Boolean,
    hat: Boolean,
    gender: String)
