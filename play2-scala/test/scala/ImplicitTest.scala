package scala

import model.BaseModel
import org.junit.Test

/**
 * Date : 2014. 9. 12.
 * Author : Vayne
 */
class ImplicitTest {
  /**
   * Whenever the compiler sees an X, but needs a Y,
   * It will look for an implicit function that converts X to Y.
   */
  @Test def expectedType(): Unit = {
    implicit def intToString(x: Int) = x.toString
    // 만약 위에 implicit가 없다면 컴파일 오류가 난다.
    def string: String = 1
  }

  @Test def expectedTypeByPredefClass(): Unit = {
    def i: Int = 10
    // Int가 자동으로 Dobule로 변경 됨 Predef 참고
    // Predef는 모든 scala에서 기본 import
    def d: Double = i
  }

  @Test def receiver(): Unit = {
    class AppendStringWrapper(x: String) {
      def ++(append: String) = x + append + append
    }
    implicit def appendString(x: String): AppendStringWrapper = new AppendStringWrapper(x)
    def string: String = "hello"

    // ++는 String에 존재하지 않는 메서드이다.
    def result = string ++ "world"
    assert(result == "helloworldworld")
  }

  @Test def parameter(): Unit = {
    def quicksort[T](elements: List[T])(implicit orderer: T => Ordered[T]): List[T] = elements match {
      case List() => elements
      case List(onlyOne) => elements
      case x :: rest =>
        val pivot = elements(elements.size / 2)
        quicksort(elements.filter(pivot>))(orderer) ++ elements.filter(pivot==) ++ quicksort(elements.filter(pivot<))(orderer)
    }

    class I(x: Int) extends BaseModel {
      val value = x
    }
    object I {
      def apply(x: Int) = new I(x)
    }
    class IOrdered(x : I) extends Ordered[I] {
      override def compare(that: I): Int =
        if (x.value < that.value) -1
        else if (x.value == that.value) 0
        else 1

    }
    implicit def iToIOrdered(x: I) : IOrdered = new IOrdered(x)

    assert(quicksort(List(I(5))) == List(I(5)))
    assert(quicksort(List(9,2,31,87,12)) == List(2,9,12,31,87))
  }
}

