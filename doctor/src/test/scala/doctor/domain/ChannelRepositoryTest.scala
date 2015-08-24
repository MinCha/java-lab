package doctor.domain

import doctor.IntegrationTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import scala.collection.JavaConversions._

class ChannelRepositoryTest extends IntegrationTest {
  @Autowired
  val sut: ChannelRepository = null

  @Test
  def canSave() {
    val channel = new Channel("p1c2")

    sut.save(channel)
    flushAndClear()

    val result = sut.findOne(channel.id)
    assert(result.id == channel.id)
  }

  @Test
  def canAppendMessageToChannel() {
    val c = new Channel("p1c2")
    c.addMessage(1, "hello", aSomeLastMessage(c))
    c.addMessage(2, "what", aSomeLastMessage(c))

    sut.save(c)
    flushAndClear()

    val result = sut.findOne(c.id)
    assert(result.messages.size == 2)
    assert(result.messages.last.text == "what")
  }
}
