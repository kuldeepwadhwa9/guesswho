
sealed trait HairColour
sealed trait Gender

case class Character (
    name: String,
    hairColour: HairColour,
    glasses: Boolean,
    hat: Boolean,
    gender: Gender)

object HairColour extends Enumeration {
case object Black extends HairColour
case object Red extends HairColour
case object Gray extends HairColour
case object Blonde extends HairColour
}

object Gender extends Enumeration {
  case object Male extends Gender
  case object Female extends Gender
}

