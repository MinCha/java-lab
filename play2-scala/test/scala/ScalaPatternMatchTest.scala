package scala

import org.junit.Test

/**
 * Created by Vayne on 2014. 9. 4..
 */
class ScalaPatternMatchTest {
  @Test
  def constantMatch: Unit = {
    def thisMatch(x: Any) = x match {
      case 1 => "1"
      case 2 => "2"
      case 3 => "3"
      case _ => "Unknown"
    }

    assert(thisMatch(1) == "1")
    assert(thisMatch(3) == "3")
    assert(thisMatch(new String()) == "Unknown")
  }

  @Test
  def constructorMatch: Unit = {
    case class Person(name: String, age: Int)

    def thisMatch(x: Any) = x match {
     case Person("vayne", _) => "vayne"
     case Person(_, 10) => "young"
     case _ => "Unknown"
    }

    assert(thisMatch(Person("vayne", 35)) == "vayne")
    assert(thisMatch(Person("ashe", 10)) == "young")
    assert(thisMatch(Person("troll", 999)) == "Unknown")
  }

  @Test
  def sequenceMatch: Unit = {
    def thisMatch(x: Any) = x match {
      case List(0, _*) => "zero"
      case List(1, 2) => "onetwo"
      case _ => "Unknown"
    }

    assert(thisMatch(List(0,2,3,4,5)) == "zero")
    assert(thisMatch(List(1,2)) == "onetwo")
    assert(thisMatch(List(1,2,3,4,5)) == "Unknown")
  }

  @Test
  def typedMatch: Unit = {
    def thisMatch(x: Any) = x match {
    }
  }
}
