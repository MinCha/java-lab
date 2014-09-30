package scala

import akka.actor.Actor
import org.scalatest.FlatSpec

/**
 * Date : 2014. 9. 4
 * Author : Vayne
 */
class PatternMatchTest extends FlatSpec {

  "Constant Match" should "be explained as following" in {
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

  "Constructor Match" should "be explained as following" in {
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

  "Sequence Match" should "be explained as following" in {
    def thisMatch(x: Any) = x match {
      case List(0, _*) => "zero"
      case List(1, 2) => "onetwo"
      case _ => "Unknown"
    }

    assert(thisMatch(List(0, 2, 3, 4, 5)) == "zero")
    assert(thisMatch(List(1, 2)) == "onetwo")
    assert(thisMatch(List(1, 2, 3, 4, 5)) == "Unknown")
  }

  "Type Match" should "be explained as following" in {
    def thisMatch(x: Any) = x match {
      case s: String => "string"
      case i: Int => "integer"
    }

    assert(thisMatch("someString") == "string")
    assert(thisMatch(1) == "integer")
  }

  "Map Type Information" should "be erased at runtime" in {
    def thisMatch(x: Any) = x match {
      case m: Map[Int, _] => "IntMap" // Map[_,_]
      case m: Map[String, Int] => "StringIntMap"
      case m: Map[_, _] => "OtherMap"
      case _ => "unknown"
    }

    assert(thisMatch(Map(1 -> 1)) == "IntMap")
    assert(thisMatch(Map("K" -> 1)) == "IntMap")
    assert(thisMatch(Map("K" -> "V")) == "IntMap")
  }

  "Testing instance" should "be explained as following" in {
    assert("string".isInstanceOf[String])
    assert(!"string".isInstanceOf[Int])
  }

  "Castring instance" should "be explained as following" in {
    def any: Any = "string"
    def string: String = any.asInstanceOf[String]

    assert(string == "string")
  }

  "Pattern Guides " should "provide an additional condition along with a pattern match" in {
    def thisMatch(x: Any) = x match {
      case i: Int if i > 10 => "LargeInt"
      case i: Int if i < 10 => "SmallInt"
      case _ => "Other"
    }

    assert(thisMatch(11) == "LargeInt")
    assert(thisMatch(8) == "SmallInt")
    assert(thisMatch("str") == "Other")
  }

  "Optional" should "be explained as following" in {
    val o: Option[String] = new Some[String]("Hello")

    val result = o match {
      case Some(s) => true
      case None => false
    }
    assert(result == true)
  }

  "Partial Function" should "be explained as following" in {
    val thisMatch : PartialFunction[List[Int], Int] = {
      case a :: b :: _ => b
    }

    assert(thisMatch(List(1,2,3)) == 2)
    assert(thisMatch.isDefinedAt(List(1,2,3)) == true)
    assert(thisMatch.isDefinedAt(List(1,2)) == true)
    assert(thisMatch.isDefinedAt(List(1)) == false)
  }

  "Pattern Match for FOR" should "be explained as following" in {
    /*
    def toKey(x: Map[String, Int]): List[String] = {
      for ((key, value) <- x)
      yield key
    }*/

  }
}