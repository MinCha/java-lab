package scala

import org.junit.Test

/**
 * Created by Vayne on 2014. 7. 31..
 */
class FirstStepInScalaTest {

  @Test def iterateWithMap() {
    val item = List("1","2")

    val result = item.map(each => each)

    assert(result == item)
  }

  @Test def iterateWithForeachSimple() {
    val item = List("1","2")

    item.foreach(each => {
    })
  }

  @Test def iterateWithForeachSymbol() {
    val item = List("1","2")

    for (each <- item)
      println(each)
  }

  @Test def iterateWithForeachIf() {
    val item = List("1","2")

    for (each <- item if each.equals("1"))
      println(each)
  }
}
