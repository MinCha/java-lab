package scala

import model.Champion
import org.junit.Test

class ScalaStudyTest {

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

  @Test def closure() {
    var outboundValue = 99
    def closureFunction = (x: Int) => x + outboundValue

    assert(closureFunction(2) == 101)
   }

  @Test def closureWithChangingOutsideValue() {
    var outboundValue = 99
    def closureFunction = (x: Int) => outboundValue = outboundValue + x

    closureFunction(3)

    assert(outboundValue == 102)
  }

  @Test def repeatedParameters() {
    def sum(numbers: Int*) = {
      var sum = 0
      numbers.foreach(sum += _)
      sum
    }

    assert(sum(1,2,3,4) == 10)
  }

  @Test def higherOrderFunction(): Unit = {
    val item = List(1,2,3,4,5)
    def filtering(matcher: Int=>Boolean) = {
      for (each <- item; if matcher(each)) yield each
    }

    val result = filtering(_ > 3)

    assert(result == List(4,5))
  }

  @Test def higherOrderFunctionMoreFormally(): Unit = {
    val item = List(1,2,3,4,5)
    def filtering(matcher: Int=>Boolean) = {
      for (each <- item; if matcher(each)) yield each
    }

    val result = filtering(_ > 3)

    assert(result == List(4,5))
  }

  @Test def curryingWithCurry(): Unit = {
    def mix(a: Int)(func: Int=>Int) = func(a)

    val resultX = mix(10) {
      (x: Int) => x * 10
    }

    val resultY = mix(10)((x: Int) => x * 10)

    assert(resultX == 100)
    assert(resultX == resultY)
  }

  @Test def anyEquals(): Unit = {
    val tester = new Champion("tester", "testing")
    val testerClone = new Champion("tester", "testing")

    println(tester.toString)
    println(testerClone.toString)

    println(tester.equals(testerClone))
    println(tester == testerClone)
    //assert(tester == testerClone)
  }
}
