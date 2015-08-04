package tree

import org.junit.Test
import org.scalatest.junit.JUnitSuite

class NodeTest extends JUnitSuite {
  @Test def canKnowGrandparent: Unit = {
    val parents = Node(1)
    val childB = Node(3)
    val childA = Node(2)
    parents.addToLeft(childA)
    childA.addToLeft(childB)

    assert(childB.grandparent.isDefined)
    assert(childB.grandparent.get == parents)
  }

  @Test def canKnowWhenNoGrandparent {
    val parents = Node(1)

    assert(parents.grandparent.isEmpty)
  }

  @Test def canKnowWhetherRootOrNot {
    val root = Node(1)

    assert(root.isRoot)
  }

  @Test def canKnowUncle {
    val parents = Node(value = 1, id = "p")
    val l1 = Node(value = 1, id = "l1")
    val r1 = Node(value = 1, id = "r1")
    val ll2 = Node(value = 1, id = "ll2")
    val lr2 = Node(value = 1, id = "lr2")
    val rl2 = Node(value = 1, id = "rl2")
    val rr2 = Node(value = 1, id = "rr2")
    parents.addToLeft(l1)
    parents.addToRight(r1)
    l1.addToLeft(ll2)
    l1.addToRight(lr2)
    r1.addToLeft(rl2)
    r1.addToRight(rr2)

    assert(ll2.uncle.isDefined)
    assert(ll2.uncle.get == r1)
    assert(rr2.uncle.get == l1)
    assert(l1.uncle.isEmpty)
  }

  /**
   * Tree Image
   * http://cfile28.uf.tistory.com/image/225BC54B534D339C35753C
   */
  @Test def canRotateToLeft {
    val any = 1
    val g = Node(value = any, id = "grandparent")
    val x = Node(value = any, id = "x")
    val a = Node(value = any, id = "a")
    val y = Node(value = any, id = "y")
    val b = Node(value = any, id = "b")
    val yy = Node(value = any, id = "yy")
    g.addToLeft(x)
    x.addToLeft(a)
    x.addToRight(y)
    y.addToLeft(b)
    y.addToRight(yy)

    x.rotateToLeft

    assert(g.getLeft== y)
    assert(y.getLeft== x)
    assert(y.getRight == yy)
    assert(x.getLeft == a)
    assert(x.getRight == b)
  }

  /**
   * Tree Image
   * http://cfile28.uf.tistory.com/image/225BC54B534D339C35753C
   */
  @Test def canRotateToRight {
    val any = 1
    val g = Node(value = any, id = "grandparent")
    val y = Node(value = any, id = "y")
    val yy = Node(value = any, id = "yy")
    val x = Node(value = any, id = "x")
    val a = Node(value = any, id = "a")
    val b = Node(value = any, id = "b")
    g.addToLeft(y)
    y.addToLeft(x)
    x.addToLeft(a)
    x.addToRight(b)
    y.addToRight(yy)

    y.rotateToRight

    assert(g.getLeft == x)
    assert(x.getLeft == a)
    assert(x.getRight == y)
    assert(y.getLeft == b)
    assert(y.getRight == yy)
  }

  @Test def canKnowWhetherLeftOrRight {
    val any = 1
    val p = Node(value = any, id = "parent")
    val l = Node(value = any, id = "l")
    val r = Node(value = any, id = "r")
    p.addToLeft(l)
    p.addToRight(r)

    assert(r.isRightChild)
    assert(l.isRightChild == false)
  }
}