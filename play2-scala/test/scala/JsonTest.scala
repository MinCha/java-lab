package scala

import model.Champion
import net.liftweb.json.{NoTypeHints, Serialization}
import net.liftweb.json.Serialization._
import org.junit.Test

/**
 * Created by Vayne on 2014. 8. 5..
 */
class JsonTest {
  @Test
  def convertToJson() {
    implicit val formats = Serialization.formats(NoTypeHints)
    def ashe = new Champion("ashe", "icearrow")
    println(write(ashe))
  }
}
