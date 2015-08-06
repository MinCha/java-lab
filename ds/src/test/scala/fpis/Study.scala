package fpis

import org.junit.Test
import org.scalatest.junit.JUnitSuite

import scala.annotation.tailrec

class Study extends JUnitSuite {
  @Test def fibonacci_2_1() {
      def fib(found: Int) = {
        @tailrec
        def fibNext(n: Int, m: Int, s: Int): Int = {
          if (s == found) n
          else fibNext(m, n + m, s + 1)
        }

        if (found == 0) found
        else fibNext(0, 1, 1)
      }

    assert(fib(1) == 0)
    assert(fib(2) == 1)
    assert(fib(3) == 1)
    assert(fib(4) == 2)
    assert(fib(5) == 3)
    assert(fib(6) == 5)
    assert(fib(7) == 8)
    assert(fib(8) == 13)
  }

  @Test def isSorted_2_2() {
    def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
      @tailrec
      def sorted(l: List[A]): Boolean = {
        l match {
          case List() => true
          case List(x) => true
          case x :: xs =>
            if (ordered(x, xs.head)) sorted(xs)
            else false
        }
      }
      sorted(as.toList)
    }

    assert(isSorted(Array(), (a: Int, b: Int) => a < b))
    assert(isSorted(Array(9), (a: Int, b: Int) => a < b))
    assert(isSorted(Array(1, 3, 5), (a: Int, b: Int) => a < b))
    assert(isSorted(Array(5, 3, 1), (a: Int, b: Int) => a < b) == false)
  }

  @Test def curry_2_3() {
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
      (a: A) => (b: B) => f(a,b)
    }

    val result = curry((a: Int, b: Int) => a + b)
    assert(result(2)(3) == 5)
  }

  @Test def uncurry_2_4() {
    def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
      (a: A, b: B) => {
        f(a)(b)
      }
    }

    val result = uncurry((a: Int) => (b: Int) => a + b)
    assert(result(2, 5) == 7)
  }

  @Test def compose_2_5() {
    def compose[A, B, C](f: B => C, g: A => B): A => C = {
      (a: A) => f(g(a))
    }

    val double = (a: Int) => a * 2
    val minusOne = (a: Int) => a -1
    val result = compose(double, minusOne)
    assert(result(5) == 8)
    assert((double compose minusOne)(5) == 8)
    assert((double andThen minusOne)(5) == 9)
  }
}
