package fpis

import org.junit.Test
import org.scalatest.junit.JUnitSuite

class Chapter3 extends JUnitSuite {

  sealed trait EList[+A]

  case object Nil extends EList[Nothing]

  case class Cons[A](head: A, tails: EList[A]) extends EList[A]

  object EList {
    def apply[A](as: A*): EList[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def tail[A](l: EList[A]): EList[A] = {
      l match {
        case Nil => Nil
        case Cons(_, Nil) => Nil
        case Cons(_, t) => t
      }
    }

    def setHead[A](l: EList[A], h: A): EList[A] = {
      l match {
        case Nil => Nil
        case Cons(_, Nil) => Cons(h, Nil)
        case Cons(_, t) => Cons(h, t)
      }
    }

    def drop[A](l: EList[A], n: Int): EList[A] = {
      if (n == 0) l
      else {
        l match {
          case Nil => Nil
          case Cons(_, Nil) => Nil
          case Cons(_, t) => drop(t, n - 1)
        }
      }
    }

    def dropWhile[A](l: EList[A], f: A => Boolean): EList[A] = {
      l match {
        case Cons(h, t) => {
          if (f(h)) t
          else Cons(h, dropWhile(t, f))
        }
      }
    }

    def init[A](l: EList[A]): EList[A] = {
      l match {
        case Cons(e, Nil) => Nil
        case Cons(h, t) => Cons(h, init(t))
      }
    }
  }

  @Test def tail_3_2() {
    assert(EList.tail(EList()) == Nil)
    assert(EList.tail(EList(1)) == Nil)
    assert(EList.tail(EList(1, 2, 3)) == EList(2, 3))
  }

  @Test def setHead_3_3() {
    assert(EList.setHead(EList(1), 5) == EList(5))
    assert(EList.setHead(EList(1, 2, 3), 5) == EList(5, 2, 3))
  }

  @Test def drop_3_4() {
    assert(EList.drop(EList(1, 2, 3, 4), 2) == EList(3, 4))
  }

  @Test def dropWhile_3_5() {
    assert(EList.dropWhile(EList(1,2,3,4), (e: Int) => e == 1) == EList(2,3,4))
  }

  @Test def init_3_6() {
    assert(EList.init(EList(1,2,3,4)) == EList(1,2,3))
  }
}