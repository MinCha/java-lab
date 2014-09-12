package scala

import org.junit.Test

/**
 * Date : 2014. 9. 11
 * Author : Vayne
 */
class BasicTest {

  @Test def iterateWithMap() {
    val item = List("1","2")

    val result = item.map(each => each)

    assert(result == item)
  }

  @Test def iterateWithForeachSimple() {
    val item = List(1,2)
    var sum = 0

    item.foreach(sum += _)

    assert(sum == 3)
  }

  @Test def iterateWithForeachSymbol() {
    val item = List(1,2)
    var sum = 0

    for (each <- item) sum += each

    assert(sum == 3)
  }

  @Test def iterateWithForeachIf() {
    val item = List(1,2)
    var sum = 0

    for (each <- item if each.equals(1))
      sum += each

    assert(sum == 1)
  }

  @Test def filtering() {
    val list = List(2,3,4,5,6,7)

    assert(list.filter(_ % 2 == 0) == List(2,4,6))
  }

  @Test def partiallyApplied() {
    def sum(a: Int, b: Int, c:Int) = a + b + c
    def modifiedSum = sum _

    assert(sum(1,2,3) == modifiedSum(1,2,3))
  }

  @Test def repeatedParameters() {
    def sum(numbers: Int*) = {
      var sum = 0
      numbers.foreach(sum += _)
      sum
    }

    assert(sum(1,2,3,4) == 10)
  }

  @Test def equals(): Unit = {
    val a = new String("String")
    val b = new String("String")
    val c = "String"
    val d = "String"

    assert(a == b)
    assert(!(a eq b))
    assert(a ne b)
    assert(c == d)
    assert(c eq d)
  }
}
