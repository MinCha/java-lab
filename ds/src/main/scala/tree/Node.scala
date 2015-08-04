package tree

case class Node[T <% Ordered[T]](
                                  value: T,
                                  var color: Color = Red,
                                  var parent: Option[Node[T]] = None,
                                  var left: Option[Node[T]] = None,
                                  var right: Option[Node[T]] = None,
                                  var id: String = "") {
  if (id.isEmpty) id = value.toString

  def markAsBlack() = color = Black

  def markAsRoot() = parent = None

  def markAsRed() = color = Red

  def isRightChild = {
    if (parent.isEmpty) false
    else if (parent.get.hasRight && parent.get.getRight == this) true
    else false
  }

  def isLeftChild = {
    if (parent.isEmpty) false
    else if (parent.get.hasLeft && parent.get.getLeft == this) true
    else false
  }

  def isRoot = parent.isEmpty

  def addToLeft(node: Node[T]) = {
    node.parent = Some(this)
    left = Some(node)
  }

  def addToRight(node: Node[T]) = {
    node.parent = Some(this)
    right = Some(node)
  }

  def grandparent: Option[Node[T]] = {
    if (parent.isDefined && parent.get.parent.isDefined) parent.get.parent
    else None
  }

  def uncle: Option[Node[T]] = {
    if (grandparent.isEmpty) None
    else {
      val g = grandparent.get
      if (g.hasLeft && g.getLeft == this.getParent) g.right
      else g.left
    }
  }

  def rotateToLeft {
    val n = this
    val wasLeftChild = n.isLeftChild
    val c = n.getRight
    val p = n.parent

    if (n.isRoot) c.markAsRoot()
    if (c.hasLeft) this.addToRight(c.getLeft)

    n.right = c.left
    n.parent = Some(c)
    c.left = Some(n)

    if (p.isDefined) {
      if (wasLeftChild) p.get.addToLeft(c)
      else p.get.addToRight(c)
    }
  }

  def rotateToRight {
    val n = this
    val wasRightChild = n.isRightChild
    val c = n.getLeft
    val p = n.parent

    if (n.isRoot) c.markAsRoot()
    if (c.hasRight) this.addToLeft(c.getRight)

    n.left = c.right
    n.parent = Some(c)
    c.right = Some(n)

    if (p.isDefined) {
      if (wasRightChild) p.get.addToRight(c)
      else p.get.addToLeft(c)
    }
  }

  def hasLeft = left.isDefined

  def hasRight = right.isDefined

  def getLeft = left.get

  def getRight = right.get

  def getParent = parent.get

  def isRed = color == Red

  def isBlack = !isRed

  override def equals(obj: scala.Any) = {
    obj match {
      case x: Node[T] => x.id == this.id
      case _ => false
    }
  }

  override def hashCode() = id.hashCode

  override def toString = id
}