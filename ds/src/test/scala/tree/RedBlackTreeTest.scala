package tree

import org.junit.Test
import org.scalatest.junit.JUnitSuite

class RedBlackTreeTest extends JUnitSuite {

  @Test def canAdd {
    val sut = new RedBlackTree[Int]
    sut.add(3)
    sut.add(1)
    sut.add(5)

    val result = sut.height
    println(sut.makeTreeStructureAsString)

    assert(result == 2)
  }

  @Test def shouldRotateToLeft {
    val sut = new RedBlackTree[Int]
    sut.add(1)
    sut.add(2)
    sut.add(3)

    println(sut.makeTreeStructureAsString)

    assert(sut.getRoot.getLeft.value == 1)
    assert(sut.getRoot.getRight.value == 3)
  }

  @Test def canConstructTree {
    val sut = new RedBlackTree[Int]
    1.to(7).foreach(sut.add(_))

    println(sut.makeTreeStructureAsString)

    assert(sut.height == 4)
    assert(sut.getRoot.value == 2)
    assert(sut.getRoot.getLeft.value == 1)
    assert(sut.getRoot.getRight.value == 4)
  }


    @Test(expected = classOf[IllegalArgumentException])
  def shouldThrowExceptionWhenValueIsDuplicated {
    val sut = new RedBlackTree[Int]
    sut.add(1)
    sut.add(1)
  }
}