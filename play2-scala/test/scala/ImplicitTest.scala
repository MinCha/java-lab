package scala

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
    implicit def intToString(x: Int) = x.toString;
    // 만약 위에 implicit가 없다면 컴파일 오류가 난다.
    def string: String = 1
  }

  @Test def expectedTypeByPredefClass(): Unit = {
    def i: Int = 10
    // Int가 자동으로 Dobule로 변경 됨 Predef 참고
    def d: Double = i
  }

  @Test def receiver(): Unit = {
    class AppendStringWrapper(x: String) {
      def ++(append: String) = x + append + append
    }
    implicit def appendString(x: String): AppendStringWrapper = new AppendStringWrapper(x)
    def string: String = "hello"

    def result = string ++ "world"
    assert(result == "helloworldworld")
  }
}
