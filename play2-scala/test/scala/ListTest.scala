package scala

import org.scalatest.FlatSpec

/**
 * Date : 2014. 9. 18.
 * Author : Vayne
 */
class ListTest extends FlatSpec {
  "In List user" should "use exists" in {
    val list = List("min","cha", "chang")

    list.count(_ == "min")
}
}
