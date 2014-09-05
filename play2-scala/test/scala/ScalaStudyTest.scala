package scala

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
    val outboundValue = 99
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

    // ( 대신 {를 써서 마치 함수를 넘기는 것처럼 할 수 있음 일종의 슈가 문법
    val resultX = mix(10) {
      (x: Int) => x * 10
    }
    // 위와 동일한 표현이지만 함수 같아 보이지는 않음
    val resultY = mix(10)((x: Int) => x * 10)

    assert(resultX == 100)
    assert(resultX == resultY)
    assert(mix(10)((x: Int) => x / 5) == 2)
    assert(mix(10)((x: Int) => x / 2) == 5)
    assert(mix(10)((x: Int) => x + 1) == 11)
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
