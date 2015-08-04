package tree

import scala.collection.mutable.ListBuffer

class RedBlackTree[T <% Ordered[T]] {
  private var root: Option[Node[T]] = None

  def getRoot = root.get

  def updateRoot() = {
    def findRoot(node: Node[T]): Node[T] = {
      if (node.parent.isEmpty) node
      else findRoot(node.parent.get)
    }
    root = Some(findRoot(root.get))
  }

  def add(value: T) = {
    if (root.isEmpty) {
      root = Option(Node(value))
      rootMustBeBlack(root.get)
    } else {
      tryAddingTo(root, value)
      updateRoot()
    }
  }

  def height = {
    if (root.isEmpty) 0
    else
      traversal(root.get, 0) {
        (node: Node[T], level: Int) => level
      }.sortWith(_ > _).head + 1
  }

  def traversal[R](node: Node[T], level: Int)(f: (Node[T], Int) => R): List[R] = {
    val result = ListBuffer.empty[R]
    result += f(node, level)
    if (node.hasLeft)
      result ++= traversal(node.getLeft, level + 1)(f)

    if (node.hasRight)
      result ++= traversal(node.getRight, level + 1)(f)

    result.toList
  }

  def makeTreeStructureAsString = {
    if (root.isEmpty) ""
    else traversal(root.get, 0) {
      (node: Node[T], level: Int) => {
        val space = List.fill(level)("  ").mkString
        space + """|_""" + node.value.toString + "(" + node.color + ")" +
          sys.props("line.separator")
      }
    }.mkString(" ")
  }

  private def tryAddingTo(node: Option[Node[T]], value: T): Node[T] = {
    if (node.isEmpty) throw new IllegalArgumentException
    val n = node.get
    val c = Node(value = value, parent = node)
    if (n.value < value) {
      if (n.hasRight)
        tryAddingTo(n.right, value)
      else {
        n.addToRight(c)
        rootMustBeBlack(n.getRight)
        c
      }
    } else if (n.value > value) {
      if (n.hasLeft)
        tryAddingTo(n.left, value)
      else {
        n.addToLeft(c)
        rootMustBeBlack(n.getLeft)
        c
      }
    } else {
      throw new IllegalArgumentException
    }
  }

  def rootMustBeBlack(n: Node[T]) {
    if (n.isRoot) n.markAsBlack()
    else parentMustBeBlack(n)
  }

  def parentMustBeBlack(n: Node[T]) {
    if (n.getParent.isBlack == false) redBlackRuleMustBeValid(n)
  }

  def redBlackRuleMustBeValid(n: Node[T]) {
    if (n.uncle.isDefined && n.uncle.get.isRed) {
      n.parent.get.markAsBlack()
      n.uncle.get.markAsBlack()
      n.grandparent.get.markAsRed()
      rootMustBeBlack(n.grandparent.get)
    } else {
      mustBeRotated(n)
    }
  }

  def mustBeRotated(n: Node[T]) {
    var next = n
    if (n.isRightChild && n.getParent.isLeftChild) {
      n.getParent.rotateToLeft
      next = n.getLeft
    } else if (n.isLeftChild && n.getParent.isRightChild) {
      n.getParent.rotateToRight
      next = n.getRight
    }
    mustBeSameBlackCountFromNode(next)
  }

  def mustBeSameBlackCountFromNode(n: Node[T]) {
    n.getParent.markAsBlack()
    n.grandparent.get.markAsRed()
    if (n.grandparent.get.isLeftChild) n.rotateToRight
    else n.grandparent.get.rotateToLeft
  }
}