package scala

import org.junit.Test

import scala.collection.mutable

/**
 * Date : 2014. 9. 4
 * Author : Vayne
 */
class PatternMatchTest {
  @Test
  def constantMatch(): Unit = {
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
  def constructorMatch(): Unit = {
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
  def sequenceMatch(): Unit = {
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
  def typedMatch(): Unit = {
    def thisMatch(x: Any) = x match {
      case s: String => "string"
      case i: Int => "integer"
    }

    assert(thisMatch("someString") ==  "string")
    assert(thisMatch(1) ==  "integer")
  }

  @Test
  def mapTypedMatch(): Unit = {
    def thisMatch(x: Any) = x match {
      case m: Map[Int, _] => "IntMap"
      case m: Map[String, Int] => "StringIntMap"
      case m: Map[_,_] => "OtherMap"
      case _ => "unknown"
    }

    assert(thisMatch(mutable.Map.empty[Int, String]) == "IntMap")
    assert(thisMatch(mutable.Map.empty[String, Int]) == "StringIntMap")
    assert(thisMatch(mutable.Map.empty[String, String]) == "OtherMap")
  }

  @Test
  def testInstance(): Unit = {
    assert("string".isInstanceOf[String])
    assert(!"string".isInstanceOf[Int])
  }

  @Test
  def castInstance(): Unit = {
    def any: Any = "string"
    def string: String = any.asInstanceOf[String]

    Int

    assert(string == "string")
  }
}
